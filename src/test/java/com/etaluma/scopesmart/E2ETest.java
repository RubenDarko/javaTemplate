package com.etaluma.scopesmart;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "com.etaluma.scopesmart.steps",
        plugin = {
            "pretty",
            "html:target/cucumber-reports",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
            "json:target/cucumber-report.json"},
        tags="@Automation")

public class E2ETest {

}