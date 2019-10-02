@Automation @TestSetup @IncrementDilutionRatio @UserStoryTSS-19 @TSS-2253

Feature: Incrementing Dilution Ratio

  This feature provides the ability to  to copy a Patient Sample into multiple Wells with incrementing dilution ratios

  @TEST_TSS-2254 @Automation
  Scenario: Fill Down - All dilutions fit on the current Slide
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    And Slide Is Unlocked And Available For Editing
    When the well "1" has "123456"
    Then the User clicks the 'fill down' action
    And the User has selected the desired "80" for the Patient
    Then there are enough empty wells on the Slide to hold all of the dilutions
    And A message appears warning the User that the Autofill was successfully completed
    Then the patient ID is copied into the required unoccupied Wells
    And the dilution ratio for each Patient Sample is set as 2x the previous dilution

  @TEST_TSS-2255 @Automation
  Scenario: Fill Down - All dilutions do not fit on the current Slide
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    And Slide Is Unlocked And Available For Editing
    When the well "10" has "123456"
    Then the User clicks the 'fill down' action
    And the User has selected the desired "80" for the Patient
    Then there are NOT enough empty wells on the Slide to hold all of the dilutions
    And the User clicks the 'Fill/Go' action after reading the warning that additional Slides will need to be setup for the remaining dilutions
    Then the patient ID is copied into the required unoccupied Wells
    And the dilution ratio for each Patient Sample is set as 2x the previous dilution

  @TEST_TSS-2256 @Automation
  Scenario: Fill Down - Cancel
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    And Slide Is Unlocked And Available For Editing
    When the well "1" has "123456"
    Then the User clicks the 'fill down' action
    And the User clicks 'cancel'
    Then no changes are made to the Slide Setup