package com.example.snkrsbot_test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Checkout extends PageObject {
    private static final String CVV = "999";

    @FindBy(css = "button.ncss-btn-secondary-dark.mb4-sm.mb4-md.ml2-md.mr4-md")
    public WebElement newCardButton;

    @FindBy(xpath = "//button[contains(text(), 'Continue')]")
    private WebElement saveAndContinue;

    @FindBy(xpath = "//button[contains(text(), 'Order')]")
    public WebElement orderButton;

    @FindBy(xpath = "//div[contains(text(), 'Pending')]")
    private WebElement confirmation;

    public Checkout(WebDriver driver) {
        super(driver);
    }

    public void enterCVV() {
        Utils.actions.sendKeys(Keys.TAB).build().perform();
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.newCardButton.isDisplayed()
        );
        Utils.actions.moveToElement(this.newCardButton).build().perform();
        Utils.actions.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();
        Utils.actions.sendKeys(CVV).build().perform();
    }

    public void saveAndContinue() {
        Utils.wait.until(
                ExpectedConditions.elementToBeClickable(
                        this.saveAndContinue
                )
        );
        this.saveAndContinue.click();
    }

    public void placeOrder() {
        Utils.wait.until(
                ExpectedConditions.elementToBeClickable(
                        this.orderButton
                )
        );
        this.orderButton.click();
    }

    public void verifyCheckoutSuccess() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.confirmation.isDisplayed()
        );
        System.out.println("Checkout Complete");
    }
}