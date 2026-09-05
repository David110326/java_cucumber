package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",          // Feature文件路径
        glue = "com.example.stepdefinitions",               // Step Definitions包名
//        plugin = {                                          // 报告插件
//                "pretty",                                       // 控制台漂亮输出
//                "html:target/cucumber-reports/cucumber.html",   // HTML报告
//                "json:target/cucumber-reports/cucumber.json",   // JSON报告
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//        },
        monochrome = true,                                  // 控制台输出去颜色码
        tags = "@smoke",                                    // 只跑带@smoke标签的用例
        dryRun = false                                      // true=只检查步骤映射，不实际执行
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // 支持并行执行
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}