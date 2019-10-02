@Automation @Review @PrintTestBatchReport @UserStoryTSS-363 @TSS-2578

Feature: Print Test Batch Report

  User needs to print the Test Batch report as part of the Archive process

  @TEST_TSS-2280 @Automation
  Scenario: Archive button elements
    Given The Review Page Is Active
    When User expands the last Batch from the list in the left side
    Then User clicks on Archive
    And Verify that Archive Window is opened
    Then Verify Close Panel Icon
    And Verify Archive icon is present and disabled
    Then Verify "Print PDF" checkbox is not selected
    And Verify "Download PDF" checkbox is not selected
    Then Verify "Export to LIS" checkbox is not selected

  @TEST_TSS-2281 @Automation
  Scenario: Print PDF Checkbox Selected
    Given The Review Page Is Active
    When User expands the last Batch from the list in the left side
    Then Approve all wells in selected test batch
    Then User clicks on Archive
    And Verify that Archive Window is opened
    And Verify Archive icon is present and enabled
    When User clicks on the "Print PDF" button checkbox
    Then Verify "Print PDF" checkbox is selected
    And Verify Close Panel Icon
    And Clean up Downloads folder

  @TEST_TSS-2283 @Automation
  Scenario: Print PDF and Download PDF CheckBoxes Selected
    Given The Review Page Is Active
    When User expands the last Batch from the list in the left side
    Then Approve all wells in selected test batch
    Then User clicks on Archive
    And Verify that Archive Window is opened
    And Verify Archive icon is present and enabled
    When User clicks on the "Print PDF" button checkbox
    Then Verify "Print PDF" checkbox is selected
    When User clicks on the "Download PDF" button checkbox
    Then Verify "Download PDF" checkbox is selected
    And Verify Archive icon is present and enabled
    When User clicks on Archive icon
    Then Verify message "Archive Complete" is present
    And Verify message "Please close this window to proceed" is present
    Then Verify PDF File is downloaded
    And Clean up Downloads folder
    Then Verify OS Print dialog