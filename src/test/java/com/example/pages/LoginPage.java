package com.example.pages;

import com.example.utils.ConfigReader;
import com.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 页面元素（使用PageFactory注解）
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "title")
    private WebElement pageTitle;

    // 构造方法
    public LoginPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    // 打开登录页
    public void open() {
        driver.get(ConfigReader.getBaseUrl());
    }

    // 输入用户名
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    // 输入密码
    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    // 点击登录按钮
    public void clickLogin() {
        loginButton.click();
    }

    // 完整登录流程
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // 获取错误信息
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    // 获取页面标题（登录成功后）
    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    // 判断是否登录成功
    public boolean isLoginSuccessful() {
        try {
            return pageTitle.isDisplayed() && pageTitle.getText().equals("Products");
        } catch (Exception e) {
            return false;
        }
    }
}