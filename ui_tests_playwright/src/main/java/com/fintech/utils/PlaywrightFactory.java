package com.fintech.utils;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class PlaywrightFactory {
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static Page initDriver(String browserName, boolean headless) {
        playwright.set(Playwright.create());
        BrowserType type;
        switch (browserName.toLowerCase()) {
            case "firefox":
                type = playwright.get().firefox();
                break;
            case "webkit":
                type = playwright.get().webkit();
                break;
            default:
                type = playwright.get().chromium();
                break;
        }
        browser.set(type.launch(new BrowserType.LaunchOptions().setHeadless(headless)));
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
        return page.get();
    }

    public static Page getPage() {
        return page.get();
    }

    public static void takeScreenshotOnFailure(String testName) {
        if (getPage() != null) {
            getPage().screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("ui_reports/screenshots/" + testName + ".png"))
                    .setFullPage(true));
        }
    }

    public static void closeDriver() {
        if (page.get() != null) page.get().close();
        if (context.get() != null) context.get().close();
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();
    }
}