package com.example.snkrsbot_test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class Login extends PageObject {
    private static final String EMAIL = "test";
    private static final String PASSWORD = "test";

    private static final Navigate Navigate = Utils.Navigate;

    @FindBy(css = "span.test-name.text-color-secondary.ml2-sm.va-sm-m.d-sm-h.d-md-ib.fs-block")
    public WebElement username;

    public Login(WebDriver driver) {
        super(driver);
    }

    public void login() throws InterruptedException {
        Navigate.sendKeys(new String[] {"TAB", "TAB", "ENTER", "TAB"});
        Utils.actions.sendKeys(EMAIL).build().perform();
        Thread.sleep(1000);
        Utils.actions.sendKeys(Keys.TAB).build().perform();
        Thread.sleep(1000);
        Utils.actions.sendKeys(PASSWORD).build().perform();
        Thread.sleep(1000);
        Utils.actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void verifyLoginSuccess() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.username.isDisplayed()
        );
        System.out.println("Login Complete\n");
    }
}
