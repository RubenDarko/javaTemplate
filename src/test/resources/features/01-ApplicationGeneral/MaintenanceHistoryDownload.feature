@Automation @ApplicationGeneral @MaintenanceHistoryDownload @UserStoryTSS-306 @TSS-2702

Feature: Maintenance History Download

  User needs to view/print/download a maintenance history report (URS-057)

  @TEST_TSS-2742 @Automation
  Scenario: Maintenance Download Button
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    And Verify that the "file-csv" icon is present
    Then Click on the "file-csv" icon
    Then Verify CSV File is downloaded
    And Clean up Downloads folder