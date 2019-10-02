@Automation @Review @ApprovePatientResults @UserStoryTSS-101 @TSS-1585

Feature: Approve Patient Results

  User needs to Approve the Patient Results for the selected Well

  @TEST_TSS-1642 @Automation
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

  @TEST_TSS-1643 @Automation
  Scenario: User Disapproves the first Well Sample Available
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to disapprove
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Click on the Disapprove Icon
    And Verify Positive, Negative or Rejected icon in result
    Then Verify Positive, Negative or Rejected icon in list
    And Verify Approve Icon on the bottom right side of the screen

  @TEST_TSS-1644 @Automation
  Scenario: User Approves the last Well Sample Available
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the last Well in list to approve
    And Verify Approve Icon on the bottom right side of the screen
    Then Click on the Approve Icon
    And Verify check mark at the top of the sample icon in result
    Then Verify check mark at the top of the sample icon in list
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Verify Edit Icon in result is displayed

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