@Automation @TestSetup @TestBatchLockUnlockValidation @UserStoryTSS-305 @TSS-2814

Feature: Test Batch Lock Unlock Validation

  This feature is used to validate the Test Batch lock/unlock status

  @TEST_TSS-2662 @Automation
  Scenario Outline: Verify the Test Batch lock status
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
    And The Test Batch has a "<SlideID>"
    And User converts the first Well to be a Positive Control
    And User converts the second Well to be a Negative Control
    And User types Patient for the "<PatientWell>" in the Sample ID input
    When User clicks on the Test Batch
    And User clicks on the Approve Check Icon
    Then Verify that the Test Batch is blocked
    And Verify that a Notification is displayed indicating that the Test Batch has been updated
    And Verify that the User is not able to add a New Slide
    And Verify that the Test Batch Worklist is not editable
    When User clicks on the Slide
    Then Verify that the Wells cannot be cleared
    And Verify that the Delete Slide button is disabled
    When User clicks on the Test Batch
    And User clicks on the Test Batch Edit Icon
    Then Verify that a Notification is displayed indicating that the Test Batch has been updated
    And Verify that the Test Batch is unlocked
    And Verify that the Create Slide button is enabled
    And User close the Create Slide window
    And Clean up Test Batches

    Examples:
      | SlideType   | Kit Part | Kit Lot  | Exp. Date | Cofactor | Checksum | SlideID  | PatientWell  |
      | Double ANCA | 0001116  | 2695227B | 250821    | 0.10     | 1        | 112233   | 3            |