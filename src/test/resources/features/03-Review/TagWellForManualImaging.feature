@Automation @Review @TagWellForManualImaging @UserStoryTSS-230 @TSS-1789

Feature: Tag Well For Manual Imaging

  User may need to indicated that they want to take additional manual images when the slide is back on the deck.

  @TEST_TSS-1790 @Automation
  Scenario Outline: Re-Image for Negative and Positive Samples and Controls
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first well by icon "<initialState>" in list
    And Verify Re-Image Icon on the bottom right side of the screen
    When User clicks on Re-Image Icon
    Then Verify "<targetState>" Icon in Sample List
    And Verify "<targetState>" Icon in Slide Preview
    When User clicks on Un Tag Re-Image Icon
    Then Verify "<initialState>" Icon in Sample List
    And Verify "<initialState>" Icon in Slide Preview

    Examples:
    | initialState     | targetState |
    | positive-control | reimage     |
    | negative-control | reimage     |
    | positive-sample  | reimage     |
    | negative-sample  | reimage     |

  @Automation
  Scenario: User Approves the first Well Sample Available
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

  @TEST_TSS-1791 @Automation
  Scenario: Re-Image for Approved Samples
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to disapprove
    And Verify Re-Image Icon is disabled

  @Automation
  Scenario: User Disapproves the first Well Sample Available
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to disapprove
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Click on the Disapprove Icon
    And Verify Positive, Negative or Rejected icon in result
    Then Verify Positive, Negative or Rejected icon in list
    And Verify Approve Icon on the bottom right side of the screen