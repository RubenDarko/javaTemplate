@Automation @Review @ViewWellStatus @UserStoryTSS-234 @TSS-3104

Feature: View Well Status

  User needs to view Well status and select Wells to review in graphical Slide view

  @TEST_TSS-3006 @Automation
  Scenario: Well Samples Status
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Verify that Well Samples has a "Positive" Result
    And Verify that Well Samples has a "Negative" Result
    Then Verify that Well Samples has a "Uncertain" Result

  @TEST_TSS-3007 @Automation
  Scenario: Empty Wells
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the last Well in list to approve
    And Verify all the "Empty" Wells in Slide Preview are not clickable

  @TEST_TSS-3008 @Automation
  Scenario: Clickable Wells
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the last Well in list to approve
    And Verify all the "Positive" Wells in Slide Preview are clickable
    Then Verify all the "Negative" Wells in Slide Preview are clickable
    And Verify all the "Uncertain" Wells in Slide Preview are clickable

  @TEST_TSS-3009 @Automation
  Scenario Outline: Well Icons
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the last Well in list to approve
    When User selects "<Result>" in Sample Result
    Then Verify Icon in Well List is "<Result>"
    And Verify Icon in Slide Preview is "<Result>"

    Examples:
    | Result   |
    | Reject   |
    | Negative |
    | Positive |

  @TEST_TSS-3010 @Automation
  Scenario Outline: Test Batch Filters
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then User clicks on Show Filter button
    And Verify the Filter Window is Present
    When User Unchecks the Option "<Option>" in Filter "Result"
    Then User clicks on "Apply Filters" button
    And Verify Well Samples do not contain "<Option>"

    Examples:
      | Option    |
      | Positive  |
      | Negative  |
      | Uncertain |
      | Rejected  |

  @TEST_TSS-3011 @Automation
  Scenario: Selected Well
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    And Verify Approve Icon on the bottom right side of the screen
    Then Click on the Approve Icon
    And Verify the next sample in list is selected
    Then Select the first Well to disapprove
    And Verify check mark at the top of the sample icon in result
    Then Verify check mark at the top of the sample icon in list
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Verify Edit Icon in result is displayed

  @TEST_TSS-3012 @Automation
  Scenario Outline: Navigate Through Sample Images With The Arrow Buttons
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User clicks on the button "<Button>"
    Then Navigate through all Images in the Pattern clicking in the next arrow
    And Navigate through all Images in the Pattern clicking in the previous arrow

    Examples:
      | Button    |
      | FITC      |
      | DAPI      |
      | FITC/DAPI |