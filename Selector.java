package com.example.snkrsbot_test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Selector {
    private static final String ITEM_NAME = "Kyrie 7";

    private static final String releaseDate = "2020-12-30 10:00:00";
    private static final String[] sizeChoices = {"M 10.5", "M 11", "M 12"};
    private static final String[] mensSizeChoices = {"10.5", "11", "12"};

    private static final SelenideElement item = $x("//img[contains(@alt, '" + ITEM_NAME + "')]");

    private static final ElementsCollection availableSizes = $$x("//li[@data-qa='size-available']");

    private static final SelenideElement buy = $x("//button[contains(text(), 'Buy')]");

    public static void getItem() {
        int i = 0;
        while (i < 5) {
            try {
                Wait().until(
                        ExpectedConditions.elementToBeClickable(
                                item
                        )
                );
                item.click();
                break;
            } catch (Exception e) {
                executeJavaScript("window.scrollBy(0, 1000)");
                i++;
            }
        }
    }

    public static void awaitRelease() {
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

    public static void awaitSizes() {
        Configuration.timeout = 60000;
        refresh();
        Wait().until(
                (ExpectedCondition<Boolean>)
                        driver -> availableSizes.size() > 0
        );
        Configuration.timeout = 10000;
    }

    public static void setSize() {
        if (availableSizes.get(0).getText().contains("M")) {
            getSize(sizeChoices);
        } else {
            getSize(mensSizeChoices);
        }
    }

    public static void getSize(String[] choices) {
        for (String choice : choices) {
            for (WebElement size : availableSizes) {
                System.out.println(size.getText());
                if (size.getText().contains(choice)) {
                    System.out.println(size.getText());
                    actions().moveToElement(size);
                    actions().sendKeys(Keys.TAB).build().perform();
                    actions().sendKeys(Keys.ENTER).build().perform();
                    return;
                }
            }
        }
    }

    public static void buy() {
        try {
            Wait().until(
                    (ExpectedCondition<Boolean>)
                            driver -> buy.isDisplayed()
                                    && buy.isEnabled()
            );
            Wait().until(
                    ExpectedConditions.elementToBeClickable(
                            buy
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        buy.click();
        System.out.println("Selection Complete\n");
    }
}