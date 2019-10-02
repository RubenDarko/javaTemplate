@Automation @TestSetup @TestBatchSlideValidation @UserStoryTSS-18 @TSS-2810

Feature: Test Batch Slide Validation

  This feature is used to validate the Test Batch Slides

  @TEST_TSS-2528 @Automation
  Scenario Outline: Verify the Test Batch Slides creation on correct data
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
    When User clicks on the Add a New Slide button
    Then Verify that the Create Slide form is displayed
    When User types "<data>" in the Slide ID input field
    Then The Create Slide button is "<status>"
    When User clicks on the Create Slide button
    Then Verify that the Create Slide form is "<displayed>"
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | data   |  status    | displayed  |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 55555  |  disabled  |  yes       |
      | nDNA        | 1108-12  | 2591227B | 250821    | 0.20     | 1        | 666666 |  enabled   |  no        |

  @TEST_TSS-2530 @Automation
  Scenario Outline: Previously Used Slide
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
    When User clicks on the Add a New Slide button
    Then Verify that the Create Slide form is displayed
    When User types the same "<SlideID>" than previous Test Batch
    Then The Create Slide button is "<status>"
    When User clicks on the Create Slide button
    Then Verify that the Create Slide form is "<displayed>"
    And Verify that a Notification is displayed indicating that the Slide is created
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | SlideID  | status   | displayed |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 112233   | enabled  | no        |

  @TEST_TSS-2529 @Automation
  Scenario Outline: Add a Slide when a Test Batch is Blocked
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
    And User converts the first Well to be a Positive Control
    And User converts the second Well to be a Negative Control
    And User types Patient for the "<PatientWell>" in the Sample ID input
    When User clicks on the Test Batch
    And User clicks on the Approve Check Icon
    Then Verify that the Test Batch is blocked
    And Verify that the Create Slide  button is disabled
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | SlideID  | PatientWell  |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 112233   | 3            |

  @TEST_TSS-2571 @Automation
  Scenario Outline: Slide View Clickable Wells
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
    When User validate all the wells in the Test Batch Slide
    Then Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | SlideID  |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 112233   |