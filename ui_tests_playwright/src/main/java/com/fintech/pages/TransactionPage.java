package com.fintech.pages;

import com.microsoft.playwright.Page;

public class TransactionPage extends BasePage {
    private final String recipientInput = "#recipientId";
    private final String amountInput = "#amount";
    private final String typeSelect = "#transactionType";
    private final String submitBtn = "#submitTransactionBtn";
    private final String successMsg = ".transaction-success";
    private final String errorMsg = ".transaction-error";

    public TransactionPage(Page page) {
        super(page);
    }

    public void createTransaction(String recipientId, String amount, String type) {
        page.fill(recipientInput, recipientId);
        page.fill(amountInput, amount);
        page.selectOption(typeSelect, type);
        page.click(submitBtn);
    }

    public String getSuccessMessage() {
        return page.textContent(successMsg);
    }

    public String getErrorMessage() {
        return page.textContent(errorMsg);
    }
}