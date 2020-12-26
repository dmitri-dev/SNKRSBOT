package com.example.snkrsbot_test;

import  org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Selector extends PageObject {
    private static final String ITEM_NAME = "Air Force";

    private static final String releaseDate = "YYYY-MM-DD HH:MM:SS";
    private static final String[] sizeChoices = {"M 10.5", "M 11", "M 12"};
    private static final String[] mensSizeChoices = {"10.5", "11", "12"};

    @FindBy(xpath = "//img[contains(@alt, '" + ITEM_NAME + "')]")
    public WebElement item;

    @FindAll({@FindBy(xpath = "//li[@data-qa='size-available']")})
    public List<WebElement> availableSizes;

    @FindBy(xpath = "//button[contains(text(), 'Buy')]")
    public WebElement buy;

    public Selector(WebDriver driver) {
        super(driver);
    }

    public void getItem() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int i = 0;
        while (i < 5) {
            try {
                Utils.wait.until(
                        ExpectedConditions.elementToBeClickable(
                                this.item
                        )
                );
                this.item.click();
                break;
            } catch (Exception e) {
                js.executeScript("window.scrollBy(0, 1000)");
                i++;
            }
        }
    }

    public void awaitRelease() {
        String dateTime = LocalDateTime.now().toString();
        int t = dateTime.indexOf("T");
        String date = dateTime.substring(0, t);
        String time = dateTime.substring(t + 1, dateTime.indexOf("."));
        String startDate = date + " " + time;
        System.out.println(startDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = format.parse(startDate);
            Date d2 = format.parse(releaseDate);
            System.out.println(releaseDate);
            long diff = d2.getTime() - d1.getTime();
            if (diff > 0) {
                Thread.sleep(diff);
                System.out.println("Slept for: " + diff / 1000 + " seconds");
                System.out.println(LocalDateTime.now());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void awaitSizes() {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.availableSizes.size() > 0
        );
    }

    public void setSize() {
        if (this.availableSizes.get(0).getText().contains("M")) {
            getSize(sizeChoices);
        } else {
            getSize(mensSizeChoices);
        }
    }

    public void getSize(String[] choices) {
        for (String choice : choices) {
            for (WebElement size : availableSizes) {
                System.out.println(size.getText());
                if (size.getText().contains(choice)) {
                    System.out.println(size.getText());
                    Utils.actions.moveToElement(size);
                    Utils.actions.sendKeys(Keys.TAB).build().perform();
                    Utils.actions.sendKeys(Keys.ENTER).build().perform();
                    return;
                }
            }
        }
    }

    public void buy() {
        try {
            Utils.wait.until(
                    (ExpectedCondition<Boolean>)
                            driver -> this.buy.isDisplayed()
                                    && this.buy.isEnabled()
            );
            Utils.wait.until(
                    ExpectedConditions.elementToBeClickable(
                            this.buy
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.buy.click();
        System.out.println("Selection Complete\n");
    }
}