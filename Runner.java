package com.example.snkrsbot_test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Runner {
    private static final WebDriver driver = Utils.driver;
    private static final Navigate Navigate = Utils.Navigate;

    @BeforeSuite
    public static void main(String[] args) {
    }

    @Test(testName = "SNKRSBOT")
    public static void runTest() throws InterruptedException {
        driver.get(Utils.BASE_URL);
        driver.manage().window().maximize();

        try {

            login();

            select();

            checkout();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Thread.sleep(60000);
    }

    public static void login() throws InterruptedException {
        Login login = new Login(driver);
        login.login();
        login.verifyLoginSuccess();
    }

    public static void select() {
        Navigate.toUpcoming();
        Selector selector = new Selector(driver);
        selector.getItem();
        selector.awaitRelease();
        selector.awaitSizes();
        selector.setSize();
        selector.buy();
    }

    public static void checkout() throws InterruptedException {
        Checkout checkout = new Checkout(driver);
        checkout.enterCVV();
        checkout.saveAndContinue();
        checkout.placeOrder();
        checkout.verifyCheckoutSuccess();
    }

    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}