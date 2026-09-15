package com.fintech.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;

public class AllureUtils {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] captureScreenshot(Page page) {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }
}