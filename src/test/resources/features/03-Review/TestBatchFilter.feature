@Automation @Review @TestBatchFilter @UserStoryTSS-227 @TSS-3229

Feature: Test Batch Filter

  User needs to filter Samples within a Test Batch

  @TEST_TSS-3210 @Automation
  Scenario: Test Batch Filter Button
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify that the Filter Icon is present
    When User place the mouse over the Filter Icon
    Then Verify that the Filter Tooltip has the message "Show filter dialog"
    When User clicks on the Filter Icon
    Then Verify that the Test Batch List Filter Window is present
    And Verify that the Test Batch List Filter Window has the default values
    When User uncheck all the checkboxes from the Test Batch List Filter Window
    And User Checks the Option "Sample" in Filter "Sample Type"
    And User Checks the Option "Positive" in Filter "Result"
    And User Checks the Option "Unconfirmed" in Filter "Status"
    And User Checks the Option "Off" in Filter "Manual Imaging Tag"
    And User clicks on the Reset Filters Button
    Then Verify that the Test Batch List Filter Window has the default values
    When User clicks on the Test Batch List Filter Cancel Button
    Then Verify that the Test Batch List Filter Window is Closed

  @TEST_TSS-3209 @Automation
  Scenario: Test Batch Filter Window Warning Icons
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify that the Filter Icon is present
    When User clicks on the Filter Icon
    Then Verify that the Test Batch List Filter Window is present
    When User uncheck all the checkboxes from the Test Batch List Filter Window
    Then Verify that the Warning Icon for "Sample Type" is present
    And Verify that the Warning Icon for "Result" is present
    And Verify that the Warning Icon for "Status" is present
    And Verify that the Warning Icon for "Manual Imaging Tag" is present
    And Verify that the Apply Filters Button is disabled
    When User clicks on the Reset Filters Button
    Then Verify that the Warning Icon for "Sample Type" is not present
    And Verify that the Warning Icon for "Result" is not present
    And Verify that the Warning Icon for "Status" is not present
    And Verify that the Warning Icon for "Manual Imaging Tag" is not present
    And User clicks on the Reset Filters Button

  @TEST_TSS-3208 @Automation
  Scenario: Test Batch Filter Persist
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify that the Filter Icon is present
    When User clicks on the Filter Icon
    Then Verify that the Test Batch List Filter Window is present
    When User uncheck all the checkboxes from the Test Batch List Filter Window
    And User Checks the Option "Sample" in Filter "Sample Type"
    And User Checks the Option "Positive" in Filter "Result"
    And User clicks on the Test Batch List Filter Cancel Button
    Then Verify that the Test Batch List Filter Window is Closed
    When User clicks on the Filter Icon
    Then Verify that the Test Batch List Filter Window is present
    And The Filter "Sample" for the Option "Sample Type" is checked
    And The Filter "Positive" for the Option "Result" is checked
    And User clicks on the Reset Filters Button

  @TEST_TSS-3207 @Automation
  Scenario: Test Batch Filter Message
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify that the Filter Icon is present
    When User clicks on the Filter Icon
    Then Verify that the Test Batch List Filter Window is present
    And User Checks the Option "Specific Pattern:" in Filter "Patterns"
    When User selects the Option "Select a pattern" In Patterns Dropdown
    And User clicks on the Apply Filters Button
    Then Verify that the Test Batch List Filter Window is Closed
    When User clicks on the Filter Icon
    Then Verify that the Test Batch List Filter Window is present
    And User clicks on the Reset Filters Button

  @TEST_TSS-3206 @Automation
  Scenario Outline: Test Batch Filter Combinations
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify that the Filter Icon is present
    When User clicks on the Filter Icon
    Then Verify that the Test Batch List Filter Window is present
    And User uncheck all the checkboxes from the Test Batch List Filter Window
    And User Checks the Option "<Sample Type>" in Filter "Sample Type"
    And User Checks the Option "<Status>" in Filter "Status"
    And User Checks the Option "<Result>" in Filter "Result"
    And User Checks the Option "<Manual Imaging Tag>" in Filter "Manual Imaging Tag"
    And User Checks the Option "<Patterns>" in Filter "Patterns"
    And User clicks on the Apply Filters Button
    Then Verify that the Test Batch List Filter Window is Closed

    Examples:
      | Sample Type      | Result    | Status      | Manual Imaging Tag | Patterns          |
      | Positive Control | Positive  | Validated   | On                 | All               |
      | Negative Control | Negative  | Confirmed   | Off                | None              |
      | Sample           | Uncertain | Unconfirmed | On                 | Specific Pattern: |
      | Positive Control | Rejected  | Validated   | Off                | All               |