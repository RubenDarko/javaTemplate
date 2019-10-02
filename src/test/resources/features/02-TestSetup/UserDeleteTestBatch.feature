@Automation @TestSetup @UserDeleteTestBatch @UserStoryTSS-24 @TSS-2298

Feature: User Delete A Test Batch

  This feature provides the ability to delete a Test Batch

  @TEST_TSS-2299 @Automation
  Scenario: User clicks on the Delete Test Batch Button
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then the User clicks the delete Test Batch button
    And a confirmation dialog is displayed warning the user that the Test Batch will be deleted
    Then Clean up Test Batches

  @TEST_TSS-2300 @Automation
  Scenario: User confirms the Delete Test Batch Action
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then the User confirms the delete Test Batch action
    And the Test Batch is removed from the UI List
    Then the first Test Batch in the list is selected, if any
    And Clean up Test Batches

  @TEST_TSS-2301 @Automation
  Scenario: User cancels the Delete Test Batch Action
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then the user cancels the delete Test Batch action
    And no changes are made to the Test Batches Setup
    Then Clean up Test Batches