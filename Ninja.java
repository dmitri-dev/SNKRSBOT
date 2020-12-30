package com.example.snkrsbot_test;

import org.openqa.selenium.chrome.ChromeOptions;

public class Ninja {
    public static final ChromeOptions options = new ChromeOptions();

    public static void setOptions() {
        options.addArguments("--disable-blink-features=AutomationControlled");
    }
}
