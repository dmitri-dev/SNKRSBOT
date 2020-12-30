package com.example.snkrsbot_test;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.*;

public class Checkout {
    private static final String CVV = "330";

    private static final SelenideElement newCardButton = $("button.ncss-btn-secondary-dark.mb4-sm.mb4-md.ml2-md.mr4-md");

    private static final SelenideElement saveAndContinue = $x("//button[contains(text(), 'Continue')]");

    private static final SelenideElement orderButton = $x("//button[contains(text(), 'Order')]");

    private static final SelenideElement confirmation = $x("//div[contains(text(), 'Pending')]");

    public static void enterCVV() {
        actions().sendKeys(Keys.TAB).build().perform();
        Wait().until(
                (ExpectedCondition<Boolean>)
                        driver -> newCardButton.isDisplayed()
        );
        actions().moveToElement(newCardButton).build().perform();
        actions().keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();
        actions().sendKeys(CVV).build().perform();
    }

    public static void saveAndContinue() {
        Wait().until(
                ExpectedConditions.elementToBeClickable(
                        saveAndContinue
                )
        );
        saveAndContinue.click();
    }

    public static void placeOrder() {
        Wait().until(
                ExpectedConditions.elementToBeClickable(
                        orderButton
                )
        );
        orderButton.click();
    }

    public static void verifyCheckoutSuccess() {
        Wait().until(
                (ExpectedCondition<Boolean>)
                        driver -> confirmation.isDisplayed()
        );
        System.out.println("Checkout Complete");
    }
}