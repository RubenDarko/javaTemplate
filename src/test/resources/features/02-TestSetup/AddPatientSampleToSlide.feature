@Automation @TestSetup @AddPatientSampleToSlideKeyboard @UserStoryTSS-21 @TSS-2186

Feature: Add Patient Sample to Slide with Keyboard

  This feature provides the ability to add a new Test Batch using the Keyboard

  @TEST_TSS-2187 @Automation
  Scenario: No Match in LIS
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "567890" is selected
    And Slide Is Unlocked And Available For Editing
    Then Well "1" Within The Slide Is Selected/Active
    And Sample ID "1" Is Editable
    When User Enters Sample ID "123456" Using Keyboard
    And Clean up Test Batches

  @TEST_TSS-2188 @Automation
  Scenario: User confirms the Add Action
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "567890" is selected
    And Slide Is Unlocked And Available For Editing
    And Well "2" Within The Slide Is Selected/Active
    And Sample ID "2" Is Editable
    When User Confirms Adding Sample ID "345678" Without Matching In LIS
    Then Sample ID "345678" Is Added To Slide
    And Notification Sent To UI Indicating The Successful Changes
    And Clean up Test Batches

  @TEST_TSS-2189 @Automation
  Scenario: User Cancels the Add Action
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "567890" is selected
    And Slide Is Unlocked And Available For Editing
    And Well "2" Within The Slide Is Selected/Active
    And Sample ID "2" Is Editable
    When User Cancels The Add Action Sample ID "345678"
    And Verify Previously Entered Text Is Visible Editable
    And Clean up Test Batches

  @TEST_TSS-2190 @Automation
  Scenario: Match in LIS
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "567890" is selected
    And Slide Is Unlocked And Available For Editing
    And Well "2" Within The Slide Is Selected/Active
    And Sample ID "2" Is Editable
    When User Enters Sample ID "345678" Using Keyboard
    Then Sample ID "345678" Is Added To Slide
    And Notification Sent To UI Indicating The Successful Changes
    And Clean up Test Batches