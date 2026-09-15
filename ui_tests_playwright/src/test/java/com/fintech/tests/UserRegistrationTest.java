package com.fintech.tests;

import com.fintech.tests.BaseTest;
import com.fintech.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void testSuccessfulUserRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(page);
        registrationPage.navigateTo("http://localhost:3000/register");
        registrationPage.registerUser("Jane Doe", "jane@example.com", "premium");
        Assert.assertTrue(registrationPage.getSuccessMessage().contains("User created successfully"));
    }

    @Test
    public void testRegistrationValidationErrorMissingFields() {
        RegistrationPage registrationPage = new RegistrationPage(page);
        registrationPage.navigateTo("http://localhost:3000/register");
        registrationPage.registerUser("", "jane@example.com", "premium");
        Assert.assertEquals(registrationPage.getErrorMessage(), "Name is required");
    }

    @Test
    public void testRegistrationInvalidEmailFormat() {
        RegistrationPage registrationPage = new RegistrationPage(page);
        registrationPage.navigateTo("http://localhost:3000/register");
        registrationPage.registerUser("Jane Doe", "invalid-email", "premium");
        Assert.assertEquals(registrationPage.getErrorMessage(), "Invalid email address");
    }
}