@Automation @TestSetup @TestBatchWorklistValidation @UserStoryTSS-16 @TSS-2809

Feature: Test Batch Worklist Validation

  This feature is used to validate the Test Batch Worklist

  @TEST_TSS-2490 @Automation
  Scenario Outline: Verify the Max Characters Allowed in a Worklist
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
    And User types 21 characters in the "<Worklist>" input field
    Then Verify that the Worklist input field only accepts "20" characters
    And The Checksum data is correct
    When User clicks on the Create Test Batch button
    Then Test Batch Is Displayed And Selected In UI
    When User clicks on the Worklist input field
    And User delete the Worklist name
    Then Verify that the "<Worklist>" name is not displayed in the Test Batch Summary page
    When User types a "<Worklist>" name of 21 characters in the Worklist input field
    Then Verify that the Test Batch Summary Worklist input field only accepts "20" characters
    And Clean up Test Batches

  Examples:
    | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | Worklist               |
    | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 112233445566778899112  |
    | nDNA        | 1108-12  | 2591227B | 250821    | 0.20     | 1        | 112233445566778899112  |
    | HEp-2       | 0001106  | 33221121 | 250821    | 0.30     | 1        | 112233445566778899112  |

  @TEST_TSS-2492 @Automation
  Scenario Outline: Verify that the Test Batch has an Open Worklist
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
    And User fill out the worklist inside the Test Batch form
      | input      | value       |
      | Worklist   | <Worklist>  |
    And The Checksum data is correct
    And User clicks on the Create Test Batch button
    Then Test Batch Is Displayed And Selected In UI
    And The "<Worklist>" name is displayed in the Test Batch Summary page
    When User clicks on the Worklist input field
    Then User delete the Worklist name
    And User enters a new "<Worklist2>" name
    Then The "<Worklist2>" name is displayed in the Test Batch Summary page
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | Worklist              | Worklist2            |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 11223344556677889911  | 11223344556677889922 |
      | nDNA        | 1108-12  | 2591227B | 250821    | 0.20     | 1        | 11223344556677889911  | 11223344556677889922 |
      | HEp-2       | 0001106  | 33221121 | 250821    | 0.30     | 1        | 11223344556677889911  | 11223344556677889922 |

  @TEST_TSS-2493 @Automation
  Scenario Outline: Associate a Worklist Name With Multiple Test Batches
    Given The Test Setup Page Is Active
    And Clean up Test Batches
    When User clicks on Add new Test Batches button
    Then The Test Batch form is displayed
    Given User fill out create Test Batch form
      | input      | value       |
      | Slide Type | <SlideType> |
      | Kit Part   | <Kit Part>  |
      | Kit Lot    | <Kit Lot>   |
      | Exp. Date  | <Exp. Date> |
      | Cofactor   | <Cofactor>  |
      | Checksum   | <Checksum>  |
    Then The Checksum data is correct
    When User clicks on the Create Test Batch button
    Then Test Batch Is Displayed And Selected In UI
    When User clicks on Add new Test Batches button
    Then The Test Batch form is displayed
    Given User fill out create Test Batch form
      | input      | value       |
      | Slide Type | <SlideType> |
      | Kit Part   | <Kit Part>  |
      | Kit Lot    | <Kit Lot>   |
      | Exp. Date  | <Exp. Date> |
      | Cofactor   | <Cofactor>  |
      | Checksum   | <Checksum>  |
    Then The Checksum data is correct
    When User clicks on the Create Test Batch button
    Then Test Batch Is Displayed And Selected In UI
    When User selects the last unselected Test Batch created
    And User enters a "<Worklist>" name
    And The "<Worklist>" name is displayed in the Test Batch Summary page
    When User selects the last unselected Test Batch created
    And User clicks on the Worklist input field
    And User types the first character in the "<Worklist>"
    Then Verify that the dropdown is populated with the "<Worklist>" name from the first Test Batch
    When User selects the "<Worklist>" name from the first Test Batch
    Then The "<Worklist>" name is displayed in the Test Batch Summary page
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | Worklist              |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 11223344556677889911  |

  @TEST_TSS-2491 @Automation
  Scenario Outline: Associate a Worklist for a Test Batch
    Given The Test Setup Page Is Active
    And Clean up Test Batches
    When User clicks on Add new Test Batches button
    Then The Test Batch form is displayed
    Given User fill out create Test Batch form
      | input      | value       |
      | Slide Type | <SlideType> |
      | Kit Part   | <Kit Part>  |
      | Kit Lot    | <Kit Lot>   |
      | Exp. Date  | <Exp. Date> |
      | Cofactor   | <Cofactor>  |
      | Checksum   | <Checksum>  |
    Then The Checksum data is correct
    When User clicks on the Create Test Batch button
    Then Test Batch Is Displayed And Selected In UI
    And The Test Batch Worklist is empty
    When User enters a "<Worklist>" name
    Then The "<Worklist>" name is displayed in the Test Batch Summary page
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | Worklist              |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 11223344556677889911  |