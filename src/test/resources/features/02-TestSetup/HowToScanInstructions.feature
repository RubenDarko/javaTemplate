@Automation @TestSetup @HowToScanInstructions @UserStoryTSS-446 @TSS-2242

Feature: How To Scan Instructions

  This feature is used to verify that the "How To Scan" instructions are displayed in different screens

  @TEST_TSS-2243 @Automation
  Scenario: Instructions are displayed to create a Test Batch
    Given The Test Setup Page Is Active
    And Hand-Held Scanner Is Connected To Client PC
    When Clean up Test Batches
    Then User Verifies How To Scan In Test Batch
    And Clean up Test Batches

  @TEST_TSS-2244 @Automation
  Scenario: Instructions are displayed to create a Slide
    Given The Test Setup Page Is Active
    Then Hand-Held Scanner Is Connected To Client PC
    And Clean up Test Batches
    When User Triggers A "00011162563552B231012100968828" Scan
    Then User Verifies How To Scan In Slide
    And Clean up Test Batches

  @TEST_TSS-2245 @Automation
  Scenario: Instructions are displayed to create a Sample
    Given The Test Setup Page Is Active
    Then Hand-Held Scanner Is Connected To Client PC
    And Clean up Test Batches
    When User Triggers A "00011162563552B231012100968828" Scan
    Then a Slide "123456" is selected
    And User Verifies How To Scan In Samples
    Then Clean up Test Batches

  @TEST_TSS-2246 @Automation
  Scenario: Instructions disappear after creating a Test Batch
    Given The Test Setup Page Is Active
    Then Hand-Held Scanner Is Connected To Client PC
    And Clean up Test Batches
    When User Triggers A "00011162563552B231012100968828" Scan
    Then User Verifies How To Scan Disappear In Test Batch
    And Clean up Test Batches

  @TEST_TSS-2247 @Automation
  Scenario: Instructions disappear after creating a Slide
    Given The Test Setup Page Is Active
    Then Hand-Held Scanner Is Connected To Client PC
    And Clean up Test Batches
    When User Triggers A "00011162563552B231012100968828" Scan
    Then a Slide "123456" is selected
    And User Verifies How To Scan Disappear In Slide
    Then Clean up Test Batches

  @TEST_TSS-2248 @Automation
  Scenario: Instructions disappear after creating A Sample
    Given The Test Setup Page Is Active
    Then Hand-Held Scanner Is Connected To Client PC
    And Clean up Test Batches
    When User Triggers A "00011162563552B231012100968828" Scan
    Then a Slide "123456" is selected
    When set Patient ID in well
      | Patient_ID | well |
      | 123456     | 1    |
    Then User Verifies How To Scan Disappear In Samples
    And Clean up Test Batches