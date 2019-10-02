@Automation @TestSetup @AddQcSamplesToSlide @UserStoryTSS-22 @TSS-2211

Feature: Add QC Samples to Slide

  This feature allows to identify the location of any positive/negative Control Samples on a Slide

  @TEST_TSS-2212 @Automation
  Scenario: Toggle from an empty Well to control sample type
    Given The Test Setup Page Is Active
    When Clean up Test Batches
    Then Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    Then set Positive Control in well
      | well  |
      | 1     |
    And Verify "Positive Control" Is Displayed In The Patient ID Field For Well "1"
    Then Verify The Dilution Ratio Field Is Disabled In Well "1"
    And Clean up Test Batches

  @TEST_TSS-2213 @Automation
  Scenario: Toggle from a non-empty Well to control sample type
    Given The Test Setup Page Is Active
    When Clean up Test Batches
    Then Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    Given set Patient ID in well
      | Patient_ID | well |
      | 123456     | 1    |
      | 654321     | 2    |
      | 321321     | 3    |
    When set Positive Control in well
      | well  |
      | 1     |
    Then User Clicks "Yes" In Overwrite Existing Sample Prompt Message
    And Verify "Positive Control" Is Displayed In The Patient ID Field For Well "1"
    And Clean up Test Batches

  @TEST_TSS-2214 @Automation
  Scenario: Toggle from a control type to patient type
    Given The Test Setup Page Is Active
    When Clean up Test Batches
    Then Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    Given set Positive Control in well
      | well  |
      | 1     |
    When Change To Patient ID In Well
      | Patient_ID | well |
      | 123123     | 1    |
    Then Verify "123123" Is Displayed In The Patient ID Field For Well "1"
    And Verify The Dilution Ratio Field Is Enabled In Well "1"
    And Clean up Test Batches

  @TEST_TSS-2215 @Automation
  Scenario: User confirms overwrite of a non-empty Well
    Given The Test Setup Page Is Active
    When Clean up Test Batches
    Then Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    Given set Patient ID in well
      | Patient_ID | well |
      | 123456     | 1    |
    When set Positive Control in well
      | well  |
      | 1     |
    Then User Clicks "Yes" In Overwrite Existing Sample Prompt Message
    And Verify "Positive Control" Is Displayed In The Patient ID Field For Well "1"
    Then Verify The Dilution Ratio Field Is Disabled In Well "1"
    And Clean up Test Batches

  @TEST_TSS-2216 @Automation
  Scenario: User cancels overwrite of a non-empty Well
    Given The Test Setup Page Is Active
    When Clean up Test Batches
    Then Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    Given set Patient ID in well
      | Patient_ID | well |
      | 123456     | 1    |
    Then Verify "123456" Is Displayed In The Patient ID Field For Well "1"
    When set Positive Control in well
      | well  |
      | 1     |
    Then User Clicks "No" In Overwrite Existing Sample Prompt Message
    And Verify Sample Type Icon Remains A Patient In Well "1"
    Then Clean up Test Batches