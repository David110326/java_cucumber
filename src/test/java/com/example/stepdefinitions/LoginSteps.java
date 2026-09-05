package com.example.stepdefinitions;

import com.example.pages.LoginPage;
import com.example.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class LoginSteps {
    private LoginPage loginPage;

    // 每个Scenario执行前运行
    @Before
    public void setUp() {
        loginPage = new LoginPage();
        System.out.println("=== 测试开始 ===");
    }

    // 每个Scenario执行后运行
    @After
    public void tearDown() {
        DriverManager.quitDriver();
        System.out.println("=== 测试结束 ===");
    }

    // Given步骤：打开登录页
    @Given("open login page")
    public void userOpensLoginPage() {
        loginPage.open();
    }

    // When步骤：输入用户名密码
    @When("input user_name {string} password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    // And步骤：点击登录
    @And("user click login")
    public void userClicksLoginButton() {
        loginPage.clickLogin();
    }

    // Then步骤：验证登录成功
    @Then("usr should login success, title display {string}")
    public void userShouldLoginSuccessfully(String expectedTitle) {
        String actualTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "页面标题不匹配");
        System.out.println("✅ 登录成功，标题: " + actualTitle);
    }

    // Then步骤：验证错误信息
    @Then("user should see the error message {string}")
    public void userShouldSeeErrorMessage(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        // 错误信息可能包含换行，用contains判断
        Assert.assertTrue(actualError.contains(expectedError),
                "错误信息不匹配！期望包含: " + expectedError + "，实际: " + actualError);
        System.out.println("✅ 错误信息正确: " + actualError);
    }

    // Then步骤：验证商品数量
    @Then("page at least have six product")
    public void pageShouldShowAtLeastProducts() {
        List<WebElement> products = DriverManager.getDriver()
                .findElements(By.className("inventory_item"));
        Assert.assertTrue(products.size() >= 6,
                "商品数量不足6个，实际: " + products.size());
        System.out.println("✅ 商品数量: " + products.size());
    }
}