package com.fintech.pages;

import com.microsoft.playwright.Page;

public class RegistrationPage extends BasePage {
    private final String nameInput = "#name";
    private final String emailInput = "#email";
    private final String accountTypeSelect = "#accountType";
    private final String submitBtn = "#registerBtn";
    private final String successMsg = ".alert-success";
    private final String errorMsg = ".alert-danger";

    public RegistrationPage(Page page) {
        super(page);
    }

    public void registerUser(String name, String email, String accountType) {
        page.fill(nameInput, name);
        page.fill(emailInput, email);
        page.selectOption(accountTypeSelect, accountType);
        page.click(submitBtn);
    }

    public String getSuccessMessage() {
        return page.textContent(successMsg);
    }

    public String getErrorMessage() {
        return page.textContent(errorMsg);
    }
}