@Automation @TestSetup @TestBatchValidation @UserStoryTSS-15 @TSS-2802

Feature: Test Batch Validation

  This feature is used to validate the Test Batch name and the checksum icons

  @TEST_TSS-2461 @Automation
  Scenario Outline: Verify the Test Batch name
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
    Then The Checksum data is correct
    And User clicks on the Create Test Batch button
    Then Test Batch Is Displayed And Selected In UI
    And The Test Batch name is "<SlideType>"-"<Exp. Date>"-01M
    Then Clean up Test Batches

  Examples:
    | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum |
    | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        |
    | nDNA        | 1108-12  | 2591227B | 250821    | 0.20     | 1        |
    | HEp-2       | 0001106  | 33221121 | 250821    | 0.30     | 1        |

  @TEST_TSS-2460 @Automation
    Scenario Outline: Verify the Checksum Icons
      Given The Test Setup Page Is Active
      And Clean up Test Batches
      When User clicks on Add new Test Batches button
      Then The Test Batch form is displayed
      And The Create Test Batch button is disabled
      When User fill out create Test Batch form
        | input      | value       |
        | Slide Type | <SlideType> |
        | Kit Part   | <Kit Part>  |
        | Kit Lot    | <Kit Lot>   |
        | Exp. Date  | <Exp. Date> |
        | Cofactor   | <Cofactor>  |
        | Checksum   | <Checksum>  |
      Then The Checksum data is correct
      And The Create Test Batch button is enabled
      When User change the Kit Part value
        | input      | value       |
        | Kit Part   | 0001107     |
      Then Verify that the Green icon next to the Verify button is not present
      And Verify that a Red icon next to the Verify button is present
      Then Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        |
      | nDNA        | 1108-12  | 2591227B | 250821    | 0.20     | 1        |
      | HEp-2       | 0001106  | 33221121 | 250821    | 0.30     | 1        |