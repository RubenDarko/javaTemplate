@Automation @Review @ClearCurrentFilter @UserStoryTSS-356 @TSS-3108

Feature: Clear Current Filter

  User needs to view Well status and select Wells to review in graphical Slide view

  @TEST_TSS-3064 @Automation
  Scenario Outline: Test Batch Apply Filters
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then User clicks on Show Filter button
    And Verify the Filter Window is Present
    Then User uncheck all the checkboxes from the Test Batch List Filter Window
    When User Checks the Option "Sample" in Filter "Sample Type"
    Then User Checks the Option "<Result>" in Filter "Result"
    And User Checks the Option "Unconfirmed" in Filter "Status"
    Then User Checks the Option "Off" in Filter "Manual Imaging"
    When User clicks on "Apply Filters" button
    Then Verify the Filter Window is Closed
    And Verify Well Samples contain only "<Result>"
    When User clicks on Show Filter button
    Then Verify the Filter Window is Present
    And User clicks on the Reset Filters Button
    Then Verify that the Test Batch List Filter Window has the default values

    Examples:
      | Result    |
      | Positive  |
      | Negative  |
      | Uncertain |

  @TEST_TSS-3065 @Automation
  Scenario: Test Batch Reset Filter Icons
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    When User clicks on the Filter Icon
    And Verify that the Reset Filter Icon is not clickable
    When User clicks on the Apply Filters Button
    Then Verify the Filter Window is Closed
    And Verify that the Reset Filter Icon is clickable
    And Verify Tooltip in "filter-cancel" Icon has the message "Reset filter to default settings"

  @TEST_TSS-3066 @Automation
  Scenario: Test Batch Filter Icons
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify that the Filter Icon is clickable
    And Verify that the Reset Filter Icon is not clickable