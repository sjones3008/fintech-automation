package com.fintech.base;

import com.fintech.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected Page page;

    @BeforeMethod
    public void setUp() {
        page = PlaywrightFactory.initDriver("chromium", true);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            PlaywrightFactory.takeScreenshotOnFailure(result.getName());
        }
        PlaywrightFactory.closeDriver();
    }
}