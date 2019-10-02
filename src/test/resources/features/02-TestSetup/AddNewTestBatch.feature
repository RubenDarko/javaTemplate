@Automation @TestSetup @AddNewTestBatch @UserStoryTSS-14 @TSS-2134

Feature: Add New Test Batch

  This feature provides the ability to add a new Test Batch using the hand-held barcode scanner

  @TEST_TSS-2135 @Automation
  Scenario: Kit Lot Scan is valid to create a New Test Batch
    Given The Test Setup Page Is Active
    When Clean up Test Batches
    Then Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162563552B231012100968828" Scan
    And Kit Lot Is Not Associated With Another Test Batch In The Setup State
    Then Kit Lot Expiration Date Is Greater Than Or Equal To The Current Date
    And Test Batch Is Displayed And Selected In UI
    Then Notification Sent To UI Indicating The Successful Changes
    And Clean up Test Batches

  @TEST_TSS-2136 @Automation
  Scenario: Kit Lot already exists in Setup
    Given The Test Setup Page Is Active
    And Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162563552B231012100968828" Scan
    Then Test Batch Is Displayed And Selected In UI
    And Kit Lot Is Already Associated With "00011162563552B231012100968827" In Setup State That Has Capacity For More Slides
    Then User Confirms The Creation Of A New Test Batch Anyway
    And Test Batch Is Displayed And Selected In UI
    Then Notification Sent To UI Indicating The Successful Changes
    And Clean up Test Batches

  @TEST_TSS-2137 @Automation
  Scenario: Scan is not for a Kit Lot
    Given The Test Setup Page Is Active
    And Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "9872348592345987" Scan
    Then Scan Is Not Determined To Be A Kit Lot

  @TEST_TSS-2138 @Automation
  Scenario: Expired Kit Lot
    Given The Test Setup Page Is Active
    And Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B140505095968756" Scan
    And Kit Lot Expiration Date Is Less Than The Current Date
    Then Error Notification Is Displayed

  @TEST_TSS-2139 @Automation
  Scenario: Invalid Kit
    Given The Test Setup Page Is Active
    And Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "invalidCode" Scan
    Then Error Notification Is Displayed