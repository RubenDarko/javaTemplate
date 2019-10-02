package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;

public class ApplicationsUserGuide extends BaseStep {

    public ApplicationsUserGuide(ScenarioContext scenarioContext){

        this.scenarioContext = scenarioContext;

        After(new String[]{"@ApplicationUserGuide"},(Scenario scenario) ->
            super.teardown(scenario)
        );

        Given("^user clicks on the settings icon$", () ->
            scenarioContext.getTestSetupPage().openSettingsIcon()
        );

        When("^user selects the \"([^\"]*)\"$", (String language) ->
            scenarioContext.getTestSetupPage().selectLanguage(language)
        );

        Then("^user chooses the Help Icon$", () ->
            scenarioContext.getTestSetupPage().openHelpIcon()
        );

        And("^clicks on \"([^\"]*)\"$", (String fileName) ->
            scenarioContext.getTestSetupPage().openUserGuideLink(fileName)
        );

        Then("^the manual should be open in a new tab$", () ->
            scenarioContext.getTestSetupPage().switchToUserGuideTab()
        );

        And("^verify the user guide \"([^\"]*)\" is displayed in the selected language$", (String fileName) ->
            scenarioContext.getTestSetupPage().verifyUserGuide(fileName)
        );

        When("^user access an invalid language file$", () -> {
            String invalidResource = scenarioContext.getDriver().getCurrentUrl().replace(".pdf","invalid.pdf");
            scenarioContext.getDriver().get(invalidResource);
        });

        Then("^verify error message displayed$", () ->
            scenarioContext.getTestSetupPage().verifyInvalidResource()
        );
    }
}