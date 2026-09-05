package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;

    // 获取driver（单例模式）
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    // 初始化driver
    private static void initDriver() {
        String browser = ConfigReader.getBrowser();

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (ConfigReader.isHeadless()) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("不支持的浏览器: " + browser);
        }

        // 设置隐式等待
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().window().maximize();
    }

    // 关闭driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}