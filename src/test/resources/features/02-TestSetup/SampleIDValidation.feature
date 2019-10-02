@Automation @TestSetup @SampleIDValidation @UserStoryTSS-244 @TSS-2813

Feature: Sample ID Validation

  This feature is used to validate the Sample ID input field in the active Well

  @TEST_TSS-2651 @Automation
  Scenario Outline: Verify if the Sample ID is editable on Patient Data
    Given The Test Setup Page Is Active
    And Clean up Test Batches
    When User clicks on Add new Test Batches button
    Then The Test Batch form is displayed
    When User fill out create Test Batch form
      | input      | value       |
      | Slide Type | <SlideType> |
      | Kit Part   | <Kit Part>  |
      | Kit Lot    | <Kit Lot>   |
      | Exp. Date  | <Exp. Date> |
      | Cofactor   | <Cofactor>  |
      | Checksum   | <Checksum>  |
    And The Checksum data is correct
    And User clicks on the Create Test Batch button
    And Test Batch Is Displayed And Selected In UI
    And The Test Batch has a "<SlideID>"
    When User clicks in the "<firstWell>"
    Then Verify that the "<firstWell>" becomes highlighted
    When User types Patient for the "<firstWell>" in the Sample ID input
    Then Verify that the "<nextWell>" becomes highlighted
    When User clicks in the "<firstWell>" again
    And Verify that the Sample ID for the "Patient" is editable
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | SlideID  | firstWell | nextWell  |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 112233   |  1        | 2         |

  @TEST_TSS-2650 @Automation
  Scenario Outline: Sample ID not editable on Positive/Negative Control
    Given The Test Setup Page Is Active
    And Clean up Test Batches
    When User clicks on Add new Test Batches button
    Then The Test Batch form is displayed
    When User fill out create Test Batch form
      | input      | value       |
      | Slide Type | <SlideType> |
      | Kit Part   | <Kit Part>  |
      | Kit Lot    | <Kit Lot>   |
      | Exp. Date  | <Exp. Date> |
      | Cofactor   | <Cofactor>  |
      | Checksum   | <Checksum>  |
    And The Checksum data is correct
    And User clicks on the Create Test Batch button
    Then Test Batch Is Displayed And Selected In UI
    And The Test Batch has a "<SlideID>"
    When User converts the first Well to be a Positive Control
    And Verify that the first Well icon changes to Positive Control
    And Verify that the "<firstWell>" becomes highlighted
    When User converts the second Well to be a Negative Control
    And Verify that the second Well icon changes to Negative Control
    And Verify that the "<secondWell>" becomes highlighted
    And Verify that both Wells are not editable
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | SlideID  | firstWell  | secondWell  |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 937822   | 1          | 2           |