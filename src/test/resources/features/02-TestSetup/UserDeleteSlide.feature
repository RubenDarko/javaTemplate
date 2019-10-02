@Automation @TestSetup @UserDeleteSlide @UserStoryTSS-23 @TSS-2294

Feature: User Delete A Slide

  This feature provides the ability to delete a Slide from a Test Batch

  @TEST_TSS-2295 @Automation
  Scenario: User clicks on the Delete Slide Button
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    When set Patient ID in well
      | Patient_ID | well |
      | 123456     | 1    |
    And set Positive Control in well
      | well  |
      | 2     |
    When the User clicks the delete button
    Then a confirmation dialog is displayed warning the user that the Slide and any Samples will be deleted
    And Clean up Test Batches

  @TEST_TSS-2296 @Automation
  Scenario: User confirms the Delete Slide Action
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    When set Patient ID in well
      | Patient_ID | well |
      | 123456     | 1    |
    And set Positive Control in well
      | well  |
      | 2     |
    When the User confirms the delete action
    Then the Slide is removed from the Test Batch's list of Slides in the UI
    And no Slide is currently selected
    Then Clean up Test Batches

  @TEST_TSS-2297 @Automation
  Scenario: User cancels the Delete Action
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    When set Patient ID in well
      | Patient_ID | well |
      | 123456     | 1    |
    And set Positive Control in well
      | well  |
      | 2     |
    When the user cancels the delete action
    Then no changes are made to the Slide Setup after cancel
    And Clean up Test Batches