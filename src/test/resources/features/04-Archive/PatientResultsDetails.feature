@Automation @Archive @PatientResultsDetails @UserStoryTSS-127 @TSS-2964

Feature: Patient Results Details

  User needs to view a selected Patient's Result details

  @TEST_TSS-2945 @Automation
  Scenario Outline: Detailed Component Results
    Given The Archive Page Is Active
    When Select "<DropdownPattern>" in "Patterns" Combo Box
    And Click on "Search" icon in Archive Page
    Then Validate all results contain "<DropdownPattern>" in column "Patterns"
    When User selects the "1" row in column Sample ID
    Then Verify that the "icon-file-open" Button is "enabled"
    When User clicks on the "icon-file-open" Button
    Then Verify that the Samples window is open
    Then Verify that the "1" SampleID match with the one in the Samples Window
    When User selects a "<Pattern>" in the Samples Window
    Then Verify that an image is shown in the Reference Image section
    And User moves between the different images
    When User clicks on the X Icon on the Samples Window
    Then Verify that the Samples Window is close

    Examples:
    | DropdownPattern  | Pattern     |
    | Nucleardots      | Nucleardots |
    | Homogeneous      | Homogeneous |
    | Cytoplasmic      | Cytoplasmic |
    | any              | Homogeneous |

  @TEST_TSS-2946 @Automation
  Scenario Outline: Forward and Backward Buttons
    Given The Archive Page Is Active
    When Select "<DropdownPattern>" in "Patterns" Combo Box
    And Click on "Search" icon in Archive Page
    Then Validate all results contain "<DropdownPattern>" in column "Patterns"
    When User selects the "1" row in column Sample ID
    And User selects the "2" row in column Sample ID
    Then Verify that the "icon-file-open" Button is "enabled"
    When User clicks on the "icon-file-open" Button
    Then Verify that the Samples window is open
    Then Verify that the "1" SampleID match with the one in the Samples Window
    And Verify that the "2" SampleID match with the one in the Samples Window
    When User clicks on the Forward Button on the Samples Window
    Then Verify that the "2" Sample is selected
    When User clicks on the Backward Button on the Samples Window
    Then Verify that the "1" Sample is selected
    When User clicks on the X Icon on the Samples Window
    Then Verify that the Samples Window is close

    Examples:
      | DropdownPattern  |
      | Nucleardots      |
      | Homogeneous      |
      | Cytoplasmic      |
      | any              |