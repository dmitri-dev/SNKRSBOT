package com.example.snkrsbot_test;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.codeborne.selenide.Selenide.*;

public class Login {
    private static final String EMAIL = "test";
    private static final String PASSWORD = "test";

    private static final SelenideElement username = $("span.test-name.text-color-secondary.ml2-sm.va-sm-m.d-sm-h.d-md-ib.fs-block");

    public static void login() {
        Navigate.sendKeys(new String[] {"TAB", "TAB", "ENTER", "TAB"});
        actions().sendKeys(EMAIL).build().perform();
        sleep(1000);
        actions().sendKeys(Keys.TAB).build().perform();
        sleep(1000);
        actions().sendKeys(PASSWORD).build().perform();
        sleep(1000);
        actions().sendKeys(Keys.ENTER).build().perform();
    }

    public static void verifyLoginSuccess() {
        Wait().until(
                (ExpectedCondition<Boolean>)
                        driver -> username.isDisplayed()
        );
        System.out.println("Login Complete\n");
    }
}
