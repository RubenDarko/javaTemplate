@Automation @TestSetup @SampleIDScanValidation @UserStoryTSS-69 @TSS-2812

Feature: Sample ID Scan Validation

  This feature is used to validate the a scanned Sample ID

  @TEST_TSS-2600 @Automation
    Scenario Outline: Scan a Sample ID
      Given The Test Setup Page Is Active
      And Clean up Test Batches
      And Hand-Held Scanner Is Connected To Client PC
      When User Triggers A "00011162695227B220505095968756" Scan
      And The Test Batch has a "<SlideID>"
      When User clicks in the "<firstWell>" number
      Then Verify that the "<firstWell>" becomes highlighted
      And The Sample ID for the Well "<firstWell>" Is editable
      When User Triggers a "<SampleID>" scan in the "<firstWell>"
      And Verify that the "<firstWell>" icon is "<PatientIcon>"
      And Verify that the "<secondWell>" becomes highlighted
      When User clicks in the "<firstWell>" again
      Then Verify that the "<SampleID>" is "not editable"
      And Verify that the Dilution is enabled
      And Clean up Test Batches

  Examples:
    | SlideID | firstWell  | secondWell | SampleID | PatientIcon  |
    | 937822  | 1          | 2          | 100001   | icon-patient |

  @TEST_TSS-2601 @Automation
    Scenario Outline: Clear a Simple ID
      Given The Test Setup Page Is Active
      And Clean up Test Batches
      And Hand-Held Scanner Is Connected To Client PC
      When User Triggers A "00011162695227B220505095968756" Scan
      And The Test Batch has a "<SlideID>"
      When User clicks in the "<firstWell>" number
      Then Verify that the "<firstWell>" becomes highlighted
      And The Sample ID for the Well "<firstWell>" Is editable
      When User Triggers a "<SampleID>" scan in the "<firstWell>"
      And Verify that the "<firstWell>" icon is "<PatientIcon>"
      And Verify that the "<secondWell>" becomes highlighted
      When User clicks in the "<firstWell>" again
      Then Verify that the "<SampleID>" is "not editable"
      When User clicks on the Well reset button
      Then Verify that the SampleID is clear for the "<firstWell>"
      And Verify that the Dilution value is set to "<DilutionValue>"
      And Clean up Test Batches

      Examples:
        | SlideID | firstWell  | secondWell | SampleID | PatientIcon  | DilutionValue  |
        | 726382  | 1          | 2          | 100001   | icon-patient | 20             |

  @TEST_TSS-2603 @Automation
  Scenario Outline: Sample ID Already Exists In The System
    Given The Test Setup Page Is Active
    And Clean up Test Batches
    And Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B220505095968756" Scan
    And The Test Batch has a "<SlideID>"
    When User clicks in the "<firstWell>" number
    Then Verify that the "<firstWell>" becomes highlighted
    And The Sample ID for the Well "<firstWell>" Is editable
    When User Triggers a "<SampleID>" scan in the "<firstWell>"
    And Verify that the "<firstWell>" icon is "<PatientIcon>"
    And Verify that the "<secondWell>" becomes highlighted
    And The Sample ID for the Well "<secondWell>" Is editable
    When User Triggers a "<SampleID>" scan in the "<secondWell>"
    And Verify that the "<secondWell>" icon is "<PatientIcon>"
    And Verify that the "<thirdWell>" becomes highlighted
    And Clean up Test Batches

    Examples:
      | SlideID | firstWell  | secondWell | thirdWell | SampleID | PatientIcon  |
      | 726382  | 1          | 2          | 3         | 100001   | icon-patient |

  @TEST_TSS-2604 @Automation
  Scenario Outline: Scan Data Exceeds The Maximum Sample ID Length
    Given The Test Setup Page Is Active
    And Clean up Test Batches
    And Hand-Held Scanner Is Connected To Client PC
    When User Triggers A "00011162695227B220505095968756" Scan
    And The Test Batch has a "<SlideID>"
    When User clicks in the "<firstWell>" number
    Then Verify that the "<firstWell>" becomes highlighted
    And The Sample ID for the Well "<firstWell>" Is editable
    When User Triggers a "<SampleID>" scan in the "<firstWell>"
    And Verify that the "<firstWell>" icon is "<PatientIcon>"
    #And Verify that the "<SampleID>" accepted only 20 characters
    And Verify that the "<secondWell>" becomes highlighted
    And Clean up Test Batches

    Examples:
      | SlideID | firstWell  | secondWell | SampleID | PatientIcon  |
      | 726382  | 1          | 2          | 100001   | icon-patient |