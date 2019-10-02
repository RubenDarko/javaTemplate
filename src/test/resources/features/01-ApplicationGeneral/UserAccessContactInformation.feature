@Automation @ApplicationGeneral @UserAccessContactInformation @UserStoryTSS-193 @TSS-2119

Feature: User Access Contact Information

  User needs to access contact information for external support

  @TEST_TSS-2120 @Automation
  Scenario: User Access Contact Information
    Given The Test Setup Page Is Active
    When user chooses the Help Icon
    Then clicks on Contact Info
    And Verify the following fields have information
    | field                   |
    | General Service/Support |
    | Billing                 |
    | Distribution            |
    | Product Support         |