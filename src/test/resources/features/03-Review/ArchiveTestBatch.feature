@Automation @Review @ArchiveTestBatch @UserStoryTSS-131 @TSS-2332

Feature: Archive Test Batch

  User needs to 'Archive' a Test Batch

  @TEST_TSS-2121 @Automation
  Scenario: Archive LIS text validation
    Given The Review Page Is Active
    When User expands the last Batch from the list in the left side
    And User clicks on Archive
    Then Verify that Archive Window is opened
    And Verify message "Select any of the options below and then select archive" is present
    Then Verify message "By clicking on the archive button below, you are finalizing the test batch and the samples - they will only be accessible in the Archive Module" is present
    And Verify message "Select Archive Options" is present

  @TEST_TSS-2122 @Automation
  Scenario Outline: Archive LIS components validation
    Given The Review Page Is Active
    When User expands the last Batch from the list in the left side
    Then User clicks on Archive
    And Verify that Archive Window is opened
    Then Verify Close Panel Icon
    And Verify Archive icon is present and disabled
    When User clicks on the "<button1>" button checkbox
    Then Verify "<button1>" checkbox is selected
    And Verify "<button2>" checkbox is not selected
    Then Verify "<button3>" checkbox is not selected

    Examples:
      | button1       | button2      | button3       |
      | Print PDF     | Download PDF | Export to LIS |
      | Download PDF  | Print PDF    | Export to LIS |
      | Export to LIS | Print PDF    | Download PDF  |

  @TEST_TSS-2123 @Automation
  Scenario: Sample Archived
    Given The Review Page Is Active
    When User expands the last Batch from the list in the left side
    Then Approve all wells in selected test batch
    Then User clicks on Archive
    And Verify that Archive Window is opened
    And Verify Archive icon is present and enabled
    When User clicks on Archive icon
    Then Verify message "Archive Complete" is present
    And Verify message "Please close this window to proceed" is present
    Then Verify Close Panel Icon