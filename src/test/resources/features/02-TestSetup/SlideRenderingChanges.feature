@Automation @TestSetup @SlideRenderingChanges @UserStoryTSS-482 @TSS-2272

Feature: Slide Rendering Changes

  This feature is used to verify the changes are correctly rendered in test setup

  @TEST_TSS-2273 @Automation
  Scenario: Positive Control icon is shown in the Work List
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    And set Patient ID in well
      | Patient_ID | well |
      | 123456A    | 1    |
    Then set Positive Control in well
      | well  |
      | 2     |
    And Verify Positive Control Icon In Well Within The Slide Virtual Representation
      | well  |
      | 2     |

  @TEST_TSS-2274 @Automation
  Scenario: Negative Control icon is shown in the Work List
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    And set Patient ID in well
      | Patient_ID | well |
      | 123456A    | 1    |
    Then set Negative Control in well
      | well  |
      | 2     |
    And Verify Negative Control Icon In Well Within The Slide Virtual Representation
      | well  |
      | 2     |