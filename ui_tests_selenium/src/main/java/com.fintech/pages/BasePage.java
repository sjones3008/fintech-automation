package com.fintech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
     this.driver = driver;
     this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    protected List<WebElement> waitForElementsPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
}