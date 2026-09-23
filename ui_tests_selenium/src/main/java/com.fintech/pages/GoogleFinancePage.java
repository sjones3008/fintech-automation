package com.fintech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;

public class GoogleFinancePage extends BasePage {

    // Locators specific to Google Finance UI
    private final By stockLinkElements = By.xpath(
            "//table[@aria-label='Equity sectors']//a[contains(@href, '/quote/')]" +
                    " | //section[contains(@aria-label, 'Equity sectors')]//a[contains(@href, '/quote/')]" +
                    " | //a[contains(@href, '/quote/') and contains(@aria-label, '')]"
    );
    public GoogleFinancePage(WebDriver driver) {
        super(driver);
    }

    public void openGoogleFinance() {
        navigateTo("https://www.google.com/finance");
    }

    public List<String> getEquitySectorSymbols() {
        List<WebElement> linkElements = waitForElementsPresent(stockLinkElements);
        List<String> actualSymbols = new ArrayList<>();

        for (WebElement element : linkElements) {
            String href = element.getAttribute("href");
            if (href != null && href.contains("/quote/")) {
                String symbol = parseSymbolFromHref(href);
                if (!symbol.isEmpty() && !actualSymbols.contains(symbol)) {
                    actualSymbols.add(symbol);
                }
            }
        }
        return actualSymbols;
    }

    private String parseSymbolFromHref(String href) {
        try {
            // Converts ".../quote/SIXB:INDEXCBOE" -> "SIXB"
            String quotePath = href.split("/quote/")[1];
            return quotePath.split(":")[0];
        } catch (Exception e) {
            return "";
        }
    }
}