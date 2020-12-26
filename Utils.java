package com.example.snkrsbot_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
    final static String BASE_URL = "https://www.nike.com/launch?cp=99736393091_search_%7Cnike%2BSNKRS%7CGOOGLE%7C71700000038391618%7CGN_X_X_X_X-Device_X_SNKRS_Phrase%7Cp%7Cc&gclsrc=aw.ds&=true&gclid=Cj0KCQiAzZL-BRDnARIsAPCJs70Fs35n8IYbHqOXhuyj4GrBS36vThMrl0-hTpNSqkRqcXsAxiwMdvEaAnmvEALw_wcB";

    private static WebDriver setDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    public static WebDriver driver = setDriver();
    public static Actions actions = new Actions(driver);
    public static WebDriverWait wait = new WebDriverWait(driver, 5);
    public static Navigate Navigate = new Navigate(driver);
}
