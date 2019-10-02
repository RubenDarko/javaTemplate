package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.LoginPage;
import cucumber.api.Scenario;
import static junit.framework.TestCase.*;

public class UserSignOn extends BaseStep {

    LoginPage loginPage;

    public UserSignOn(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@UserSignOn"},(Scenario scenario) ->
                super.teardown(scenario)
        );

        Given("the Sign In screen is visible", () -> {
            super.setup();
            loginPage = new LoginPage(scenarioContext.getDriver());
        });

        When("^a \"([^\"]*)\" has been selected$", (String username) ->
            loginPage.selectUser(username)
        );

        And("^the user enters a \"([^\"]*)\"$", (String invalidPassword) -> {
            loginPage.setPassword(invalidPassword);
            loginPage.login();
        });

        Then("the user is not logged in", () ->
            assertFalse(loginPage.isLoggedIn())
        );

        And("the password field is cleared", () ->
            assertTrue(loginPage.isPasswordCleared())
        );

        And("an error message is displayed", () ->
            assertTrue(loginPage.isAlertDisplayed())
        );

        Then("the user is logged in", () ->
            assertTrue(loginPage.isLoggedIn())
        );

        And("the user is redirected to the Home page", () ->
            assertTrue(loginPage.isRedirected())
        );

        Then("the User will be prompted to create and confirm a new password", () ->
            assertTrue(loginPage.hasNewPasswordPrompt())
        );

        Then("set \"([^\"]*)\" as new password if required", (String pass) -> {
            Thread.sleep(3000);
            if(!loginPage.getNewPasswordMessage().isEmpty()) {
                loginPage.getNewPasswdField().sendKeys(pass);
                loginPage.getConfirmPasswdField().sendKeys(pass);
                loginPage.getSaveButton().click();
                System.out.println("Password change was required");
            } else {
                System.out.println("Password change not required");
            }
            assertTrue(loginPage.isLoggedIn());
        });

        When("^the user enters a \"([^\"]*)\" that is not in the database$", (String username) ->
            loginPage.setUserName(username)
        );
    }
}