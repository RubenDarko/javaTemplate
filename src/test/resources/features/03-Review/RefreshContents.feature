@Automation @Review @RefreshContents @UserStoryTSS-357 @TSS-3107

Feature: Refresh Contents

  User needs to refresh the list contents using the current filter, if any

  @TEST_TSS-3062 @Automation
  Scenario: Test Batch Refresh Icon
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify "clear" Icon is displayed
    And Verify Tooltip in "clear" Icon has the message "Refresh the list using the current filter"