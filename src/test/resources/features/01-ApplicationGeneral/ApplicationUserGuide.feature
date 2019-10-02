@Automation @ApplicationGeneral @ApplicationUserGuide @UserStoryTSS-9 @TSS-2116

Feature: Application User Guide

  This feature allows the user to access the applications's user guide

  @TEST_TSS-2117 @Automation
  Scenario Outline: Access the User Guide in "<language>"
    Given The Test Setup Page Is Active
    When user clicks on the settings icon
    Then user selects the "<language>"
    And user chooses the Help Icon
    Then clicks on "<userGuide>"
    And the manual should be open in a new tab
    And verify the user guide "<userGuide>" is displayed in the selected language
    Then user clicks on the settings icon
    And user selects the "en"

    Examples:
      | language | userGuide           |
      | en       | UserGuide_EN.pdf    |
      | fr       | UserGuide_FR.pdf    |
      | de       | UserGuide_DE.pdf    |
      | it       | UserGuide_IT.pdf    |
      | pt-br    | UserGuide_PT-BR.pdf |
      | zh       | UserGuide_ZH.pdf    |
      | es       | UserGuide_ES.pdf    |

  @TEST_TSS-2118 @Automation
  Scenario Outline: Access an Invalid User Guide resource
    Given The Test Setup Page Is Active
    When user clicks on the settings icon
    Then user selects the "<language>"
    And user chooses the Help Icon
    And clicks on "<userGuide>"
    Then the manual should be open in a new tab
    When user access an invalid language file
    Then verify error message displayed

    Examples:
      | language | userGuide        |
      | en       | UserGuide_EN.pdf |