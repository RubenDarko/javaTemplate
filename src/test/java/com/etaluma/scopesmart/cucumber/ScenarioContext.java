package com.etaluma.scopesmart.cucumber;

import com.etaluma.scopesmart.pages.HomePage;
import com.etaluma.scopesmart.pages.TestSetupPage;
import com.etaluma.scopesmart.pages.ArchivePage;
import com.etaluma.scopesmart.pages.ReviewPage;
import com.etaluma.scopesmart.pages.SystemConfigurationPage;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScenarioContext {

    private TestSetupPage testSetupPage;
    private ReviewPage reviewPage;
    private ArchivePage archivePage;
    private ChromeDriver driver;
    private HomePage homePage;
    private SystemConfigurationPage systemConfigurationPage;

    public TestSetupPage getTestSetupPage() {
        return testSetupPage;
    }

    public ReviewPage getReviewPage() {
        return reviewPage;
    }

    public ArchivePage getArchivePage() {
        return archivePage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public SystemConfigurationPage getSystemConfigurationPage() {
        return systemConfigurationPage;
    }

    public ChromeDriver getDriver() {
        return driver;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public void setSystemConfigurationPage(SystemConfigurationPage systemConfigurationPage) {
        this.systemConfigurationPage = systemConfigurationPage;
    }

    public void setTestSetupPage(TestSetupPage testSetupPage) {
        this.testSetupPage = testSetupPage;
    }

    public void setReviewPage(ReviewPage reviewPage) {
        this.reviewPage = reviewPage;
    }

    public void setArchivePage(ArchivePage archivePage) {
        this.archivePage = archivePage;
    }

    public void setDriver(ChromeDriver driver) {
        this.driver = driver;
    }
}