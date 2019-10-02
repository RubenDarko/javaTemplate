@Automation @Review @ThumbnailImage @UserStoryTSS-464 @TSS-2063

Feature: Thumbnail Images

  User needs to be prevented from interacting with Patient Samples when QC fails

  @TEST_TSS-2067 @Automation
  Scenario Outline: Sample Image Buttons
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User clicks on the button "<button1>"
    Then Verify button "<button1>" is selected
    Then Verify button "<button2>" is not selected
    Then Verify button "<button3>" is not selected

    Examples:
    | button1   | button2 | button3   |
    | FITC      | DAPI    | FITC/DAPI |
    | DAPI      | FITC    | FITC/DAPI |
    | FITC/DAPI | FITC    | DAPI      |

  @TEST_TSS-2068 @Automation
  Scenario Outline: Unselected Report Image Button
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User clicks on the button "<Button>"
    Then Verify Report Image for all Slides

    Examples:
    | Button    |
    | FITC      |
    | DAPI      |
    | FITC/DAPI |