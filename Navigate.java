package com.example.snkrsbot_test;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.*;

public class Navigate {
    private static final SelenideElement upcoming = $x("//div[contains(text(), 'Upcoming')]");

    public static void sendKeys(String[] keys) {
        for (String key : keys) {
            actions().sendKeys(Keys.valueOf(key)).build().perform();
        }
    }

    public static void toUpcoming() {
        Wait().until(
                ExpectedConditions.elementToBeClickable(
                        upcoming
                )
        );
        upcoming.click();
    }
}
