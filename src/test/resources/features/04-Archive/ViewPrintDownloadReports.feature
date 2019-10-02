@Automation @ViewPrintDownloadReports @UserStoryTSS-196 @TSS-2965

Feature: View Print Download Reports

  User needs to view / print / download Test Batch Report(s) for the selected samples

  @TEST_TSS-2947 @Automation
  Scenario Outline: Archive Section PDF Components
    Given The Archive Page Is Active
    When Select "<DropdownPattern>" in "Patterns" Combo Box
    And Click on "Search" icon in Archive Page
    Then Validate all results contain "<DropdownPattern>" in column "Patterns"
    When User selects the "1" row in column Sample ID
    Then User clicks on the "file-pdf" Button
    And Verify that the Samples window is open
    When User clicks on the "<Tile>" tile
    Then Verify that Preview PDF Window is opened in Archive Page
    And Verify the title is "<Tile>" in PDF Preview
    Then Verify Close Panel Icon in PDF Preview
    And Verify following icons in PDF Preview
      | printer  |
      | download |
      | minus    |
      | plus     |
    Then Verify Page Number footer is present

    Examples:
      | DropdownPattern | Tile              |
      | Nucleardots     | Test Batch Report |
      | Homogeneous     | Slide Report      |
      | Cytoplasmic     | Test Batch Report |
      | any             | Slide Report      |

  @TEST_TSS-2948 @Automation
  Scenario: Archive Section CSV Button
    Given The Archive Page Is Active
    When Select "any" in "Patterns" Combo Box
    Then Click on "Search" icon in Archive Page
    When User selects the "1" row in column Sample ID
    Then Verify that the "file-csv" Button is enabled

  @TEST_TSS-2949 @Automation
  Scenario Outline: Archive Section PDF Components and Report
    Given The Archive Page Is Active
    When Select "<DropdownPattern>" in "Patterns" Combo Box
    And Click on "Search" icon in Archive Page
    Then Validate all results contain "<DropdownPattern>" in column "Patterns"
    When User selects the "1" row in column Sample ID
    Then Verify that the "icon-file-pdf" Button is enabled
    When User clicks on the "icon-file-pdf" Button
    Then Verify that the Samples window is open
    When User clicks on the "Test Batch Report" tile
    Then Verify that Preview PDF Window is opened in Archive Page
    When User clicks on "download" icon in Archive Page
    Then Verify PDF File is downloaded
    And Clean up Downloads folder

    Examples:
      | DropdownPattern |
      | Nucleardots     |
      | Homogeneous     |
      | Cytoplasmic     |
      | any             |