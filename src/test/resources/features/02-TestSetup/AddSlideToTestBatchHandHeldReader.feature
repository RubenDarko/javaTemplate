@Automation @TestSetup @AddSlideToTestBatchHandHeldReader @UserStoryTSS-13 @TSS-2218

Feature: Add Slide to Test Batch - Hand Held Reader

  The User needs to add a Slide to an existing Test Batch using using hand-held scanner to read the 2D code

  @TEST_TSS-2219 @Automation
  Scenario Outline: Slide is Valid for the Test Batch and there is NO import data available
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When Hand-Held Scanner Is Connected To Client PC
    Then User Triggers A "<testBatch>" Scan
    Given User Triggers A "<slide>" Slide Scan
    And the Slide is displayed in the Test Batch's Slide List
    Then the Slide is selected to display Slide details with no data yet
    And Notification Sent To UI Indicating The Successful Changes
    When set Patient ID in well
      | Patient_ID | well |
      | 112233     | 1    |
      | 445566     | 2    |
    Then set Positive Control in well
      | well  |
      | 3     |
      | 5     |
    And set Negative Control in well
      | well  |
      | 4     |
      | 6     |
    When User clicks on the selected Test Batch
    Then User Approves the Test Batch
    And User Unlocks the Test Batch
    Then Clean up Test Batches

    Examples:
    | testBatch                      | slide                           |
    | 00011162563552B231012100968828 | Hep2_12xro_LB_9688_A_000001     |
    | 00011162563552B231012100968828 | Hep2_12X5ro_LB_9688_A_000001    |
    | 00011162563552B231012100968828 | Hep2DFS_12X5ro_BL_9688_A_000001 |
    | 00011162563552B231012100968828 | HEp2DFS_t2X5ro_BL_9688_A_000001 |
    | 00011062563227B221003145968401 | ethANCA_6X5ro_TE_9684_B_000001  |
    | 00011062563227B221003145968401 | formANCA_6X5ro_WH_9684_B_000001 |
    | 1108-122591227B230805100968791 | ANCA_12X5ro_BL_9687_B_000001    |
    | 1108-122591227B230805100968791 | nDNA_6Xro_TN_9687_B_000001      |

  @TEST_TSS-2220 @Automation
  Scenario: Slide is Valid for the Test Batch and there import data IS available
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When Hand-Held Scanner Is Connected To Client PC
    Then User Triggers A "00011162563552B231012100968828" Scan
    Given User Triggers A "Hep2_12xro_LB_9688_A_000001" Slide Scan
    And the Slide is displayed in the Test Batch's Slide List
    Then the Slide is selected to display Slide details with no data yet
    And Notification Sent To UI Indicating The Successful Changes

  @TEST_TSS-2221 @Automation
  Scenario: Scan is not for a Slide
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When Hand-Held Scanner Is Connected To Client PC
    Then User Triggers A "00011162563552B231012100968828" Scan
    Given User Triggers A "scanNotForSlide" Slide Scan
    When the scan data is determined to not be for a Slide
    Then the scan is not processed

  @TEST_TSS-2222 @Automation
  Scenario: Invalid Slide Type for Test Batch
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When Hand-Held Scanner Is Connected To Client PC
    Then User Triggers A "00011162563552B231012100968828" Scan
    Given User Triggers A "ethANCA_6x5ro_TE_9684_B_000001" Slide Scan
    Then the Slide Type does not match the Slide Type for the Test Batch
    And the scan is not processed

  @TEST_TSS-2223 @Automation
  Scenario: Slide is already associated with prior Test Batch
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When Hand-Held Scanner Is Connected To Client PC
    Then User Triggers A "00011162563552B231012100968828" Scan
    Given User Triggers A "Hep2_12xro_LB_9688_B_000001" Slide Scan
    When click on Test Batch list
    Then User Triggers A "00011162563552B230112100968882" Slide Scan
    Given User Triggers A "Hep2_12xro_LB_9688_B_000001" Slide Scan
    Then the Slide ID is already associated with another Test Batch
    And the scan data is discarded

  @TEST_TSS-2224 @Automation
  Scenario: Test Batch is locked
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When Hand-Held Scanner Is Connected To Client PC
    Then User Triggers A "00011162563552B231012100968828" Scan
    Given User Triggers A "Hep3_12xro_LB_9688_B_000001" Slide Scan
    Then the scan data is discarded

  @TEST_TSS-2225 @Automation
  Scenario: Slide count exceeds limit
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When Hand-Held Scanner Is Connected To Client PC
    Then User Triggers A "00011162563552B231012100968828" Scan
    Given User Triggers A "Hep3_0xro_LB_9688_B_000010" Slide Scan
    Then the scan is not processed
    And Clean up Test Batches