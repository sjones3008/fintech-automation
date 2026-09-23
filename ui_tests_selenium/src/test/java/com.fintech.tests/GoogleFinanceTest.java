package com.fintech.tests;

import com.fintech.pages.GoogleFinancePage;
import com.fintech.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

public class GoogleFinanceTest {

    private WebDriver driver;
    private GoogleFinancePage financePage;

    private final List<String> expectedStockSymbols = Arrays.asList("NFLX", "MSFT", "TSLA", "SIXB", "SIXC", "SIXE", "SIXI", "SIXM", "SIXR"
    );

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        // Driver creation by DriverFactory
        driver = DriverFactory.createDriver(browser);
        financePage = new GoogleFinancePage(driver);
    }

    @Test
    public void verifyGoogleFinanceStockSymbols() {
        // Step 1: Open Google Finance web page
        financePage.openGoogleFinance();

        // Step 2: Verify page loaded by asserting page title
        String actualTitle = financePage.getTitle();
        Assert.assertTrue(actualTitle.toLowerCase().contains("google finance"),
                "Page title verification failed. Actual title: " + actualTitle);

        // Step 3: Retrieve actual stock symbols from the UI
        List<String> uiStockSymbols = financePage.getEquitySectorSymbols();
        System.out.println("Retrieved UI Stock Symbols: " + uiStockSymbols);

        // Step 4 and 5: Find symbols in (3) but NOT in expectedStockSymbols
        List<String> inUiNotInExpected = new ArrayList<>(uiStockSymbols);
        inUiNotInExpected.removeAll(expectedStockSymbols);
        System.out.println("----------------------------------------");
        System.out.println("Stock symbols in UI but NOT in expected list:");
        System.out.println(inUiNotInExpected);

        // Step 4 and 6: Find symbols in expectedStockSymbols but NOT in (3)
        List<String> inExpectedNotInUi = new ArrayList<>(expectedStockSymbols);
        inExpectedNotInUi.removeAll(uiStockSymbols);
        System.out.println("----------------------------------------");
        System.out.println("Stock symbols in expected list but NOT in UI:");
        System.out.println(inExpectedNotInUi);
        System.out.println("----------------------------------------");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}