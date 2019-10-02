@Automation @Review @ConfirmPatientResults @UserStoryTSS-232 @TSS-1684

Feature: Confirm Patient Results

  User needs to Confirm the Patient Results for the selected Well

  @TEST_TSS-1712 @Automation
  Scenario: User Confirms the first Well Sample Available
    Given User Reviewer Access the Review Page
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to confirm
    And Verify Approve Icon on the bottom right side of the screen
    Then Click on the Approve Icon
    And Verify the next sample to confirm in list is selected
    Then Select the first Well to unconfirm
    And Verify confirm icon at the top of the sample icon in result
    Then Verify confirm icon at the top of the sample icon in list
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Verify Edit Icon in result is displayed

  @TEST_TSS-1713 @Automation
  Scenario: User Unconfirms the first Well Sample Available
    Given User Reviewer Access the Review Page
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to unconfirm
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Click on the Disapprove Icon
    And Verify Positive, Negative or Rejected icon in result
    Then Verify Positive, Negative or Rejected icon in list
    And Verify Approve Icon on the bottom right side of the screen

  @TEST_TSS-1714 @Automation
  Scenario: User Confirms the last Well Sample Available
    Given User Reviewer Access the Review Page
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the last Well in list to confirm
    And Verify Approve Icon on the bottom right side of the screen
    Then Click on the Approve Icon
    And Verify confirm icon at the top of the sample icon in result
    Then Verify confirm icon at the top of the sample icon in list
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Verify Edit Icon in result is displayed

  Scenario: User Unconfirms the first Well Sample Available
    Given User Reviewer Access the Review Page
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to unconfirm
    And Verify Disapprove Icon is displayed on the bottom right side of the screen
    Then Click on the Disapprove Icon
    And Verify Positive, Negative or Rejected icon in result
    Then Verify Positive, Negative or Rejected icon in list
    And Verify Approve Icon on the bottom right side of the screen