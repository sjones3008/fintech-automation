package com.fintech.tests;

import com.fintech.tests.BaseTest;
import com.fintech.pages.TransactionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransactionFlowTest extends BaseTest {

    @Test
    public void testSuccessfulTransactionCreation() {
        TransactionPage transactionPage = new TransactionPage(page);
        transactionPage.navigateTo("http://localhost:3000/transaction");
        transactionPage.createTransaction("456", "100.50", "transfer");
        Assert.assertTrue(transactionPage.getSuccessMessage().contains("Transaction Processed"));
    }

    @Test
    public void testTransactionErrorInvalidAmount() {
        TransactionPage transactionPage = new TransactionPage(page);
        transactionPage.navigateTo("http://localhost:3000/transaction");
        transactionPage.createTransaction("456", "-50.00", "transfer");
        Assert.assertEquals(transactionPage.getErrorMessage(), "Amount must be greater than zero");
    }

    @Test
    public void testTransactionErrorNonExistentRecipient() {
        TransactionPage transactionPage = new TransactionPage(page);
        transactionPage.navigateTo("http://localhost:3000/transaction");
        transactionPage.createTransaction("0000", "100.50", "transfer");
        Assert.assertEquals(transactionPage.getErrorMessage(), "Recipient account not found");
    }
}