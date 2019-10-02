@Automation @ApplicationGeneral @MaintenanceLog @UserStoryTSS-189 @TSS-2699

Feature: Maintenance Log

  User needs to log maintenance performed on the instrument

  @TEST_TSS-2706 @Automation
  Scenario: Maintenance Components
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    Then Verify title is "Maintenance" in System Configuration
    And Verify message "Add Maintenance Log" is present in System Configuration
    Then Verify that the radio button "Scheduled" is present
    And Verify that the radio button "Unscheduled" is present
    Then Verify message "Maintenance Description" is present in System Configuration
    And Verify the Maintenance Description Text Area
    Then Verify that the "printer" icon is present

  @TEST_TSS-2707 @Automation
  Scenario: Maintenance Table Components
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    And Schedule a sample maintenance for testing
    Then Verify the Maintenance Table is present in System Configuration
    And Verify "Scheduled" as Table Header
    Then Verify "User" as Table Header
    And Verify "Time" as Table Header
    Then Verify "Description" as Table Header
    And Verify the Date Range component is present
    Then Verify that the "clear" icon is present
    Then Verify that the "search" icon is present
    Then Verify that the "file-csv" icon is present
    Then Verify that the "printer" icon is present

  @TEST_TSS-2708 @Automation
  Scenario Outline: Maintenance Log
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    Then Click on the radio button "<Radio>"
    And Schedule a sample maintenance for testing
    Then Validate cell value is "<Scheduled>" in added record

    Examples:

    | Radio       | Scheduled |
    | Scheduled   | Yes       |
    | Unscheduled | No        |