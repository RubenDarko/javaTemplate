@Automation @TestSetup @ViewTestBatchSummary @UserStoryTSS-77 @TSS-2286

Feature: View Test Batch Summary

  This feature allows to verify the view the Test Batch Summary

  @TEST_TSS-2287 @Automation
  Scenario: Invalid Test Batch
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then There Are No Slides In The Test Batch List
    And The Slides Count Field Will Display 0 In Test Batch List
    Then The Slides Count Warning Icon Will Be Visible In Test Batch List
    And The Check Mark Is Disabled In Test Batch List
    Then Clean up Test Batches

  @TEST_TSS-2288 @Automation
  Scenario Outline: Valid Test Batch - "<summaryItem>" in Test Batch Summary count = "<value>"
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    And User Triggers A "00011162695227B220505095968756" Scan
    When The "<summaryItem>" Count Field Will Display "<value>"
    Then The "<summaryItem>" Count Warning Icon Will Be Visible
    And The Check Mark Is Disabled In Test Batch List
    Then Clean up Test Batches

    Examples:
      | summaryItem       | value |
      | Slides            | 0     |
      | Samples           | 0     |
      | Positive Control  | 0     |

  @TEST_TSS-2289 @Automation
  Scenario: Test Batch - Multiple Positive Controls
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    Then set Positive Control in well
      | well  |
      | 1     |
      | 2     |
      | 3     |
      | 4     |
    And The Positive Control Line Will Expand To Show A List Of All Positive Controls
    Then Clean up Test Batches

  @TEST_TSS-2290 @Automation
  Scenario: Test Batch is valid
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    When set Patient ID in well
      | Patient_ID | well |
      | 654321     | 1    |
    Then set Positive Control in well
      | well  |
      | 2     |
    And set Negative Control in well
      | well  |
      | 3     |
    Then The Positive Control Line Will Expand To Show A List Of All Positive Controls
    And The Positive Control Line Will Expand To Show A List Of All Negative Controls
    Then The "Slides" Count Field Will Display "1"
    And The "Samples" Count Field Will Display "1"
    Then The Check Mark For Validation Will Be Enabled
    And Clean up Test Batches