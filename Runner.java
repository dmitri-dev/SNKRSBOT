package com.example.snkrsbot_test;

import com.codeborne.selenide.Configuration;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.*;
import static com.example.snkrsbot_test.Utils.*;

public class Runner {
    @BeforeSuite
    public static void main(String[] args) {
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
        Ninja.setOptions();
    }

    @Test(testName = "SNKRSBOT")
    public static void runTest() {
        open(BASE_URL);

        try {

            login();

            select();

            checkout();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sleep(60000);
    }

    public static void login() {
        Login.login();
        Login.verifyLoginSuccess();
    }

    public static void select() {
        Navigate.toUpcoming();
        Selector.getItem();
        Selector.awaitRelease();
        Selector.awaitSizes();
        Selector.setSize();
        Selector.buy();
    }

    public static void checkout() {
        Checkout.enterCVV();
        Checkout.saveAndContinue();
        Checkout.placeOrder();
        Checkout.verifyCheckoutSuccess();
    }

    @AfterSuite
    public static void cleanUp() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWebDriver();
    }
}