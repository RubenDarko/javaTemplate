package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.util.Database;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseStep implements En {

    public Path currentRelativePath = Paths.get("");
    public String currentWorkingDirectory = currentRelativePath.toAbsolutePath().toString();
    public String downloadFilePath = currentWorkingDirectory + "/downloads/";
    protected ScenarioContext scenarioContext;
    Database database = new Database();

    public void setup() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();

        chromePrefs.put("download.default_directory", downloadFilePath);
        options.addArguments("--incognito");
        options.setExperimentalOption("prefs", chromePrefs);
        scenarioContext.setDriver(new ChromeDriver(options));
        scenarioContext.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        scenarioContext.getDriver().manage().window().maximize();
        Set<Cookie> allCookies = scenarioContext.getDriver().manage().getCookies();
        for (Cookie cookie : allCookies)
            scenarioContext.getDriver().manage().deleteCookieNamed(cookie.getName());
    }

    public void teardown(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) scenarioContext.getDriver()).getScreenshotAs(OutputType.BYTES);
        if (scenario.isFailed())
            scenario.embed(screenshot, "image/png");
        scenarioContext.getDriver().quit();
    }
}