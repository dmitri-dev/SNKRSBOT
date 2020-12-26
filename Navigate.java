package com.example.snkrsbot_test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Navigate extends PageObject {
    @FindBy(xpath = "//div[contains(text(), 'Upcoming')]")
    public WebElement upcoming;

    public Navigate(WebDriver driver) {
        super(driver);
    }

    public void sendKeys(String[] keys) {
        for (String key : keys) {
            Utils.actions.sendKeys(Keys.valueOf(key)).build().perform();
        }
    }

    public void toUpcoming() {
        Utils.wait.until(
                ExpectedConditions.elementToBeClickable(
                        this.upcoming
                )
        );
        this.upcoming.click();
    }
}
