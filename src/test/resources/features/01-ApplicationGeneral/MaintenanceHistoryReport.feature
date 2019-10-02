@Automation @ApplicationGeneral @MaintenanceHistoryReport @UserStoryTSS-85 @TSS-2687

Feature: Maintenance History Report

  User needs to view/print/download a maintenance history report (URS-057)

  @TEST_TSS-2695 @Automation
  Scenario: Maintenance History Components
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    Then Click on the "printer" icon
    And Validate that Maintenance Log Report panel is displayed

  @TEST_TSS-2696 @Automation
  Scenario: History Table PDF Validation
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    And Schedule a sample maintenance for testing
    Then Click on the "printer" icon
    And Validate that Maintenance Log Report panel is displayed
    Then Verify Maintenance Log Report contents

  @TEST_TSS-2697 @Automation
  Scenario: History Table PDF Download
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    Then Click on the "printer" icon
    And Validate that Maintenance Log Report panel is displayed
    When User clicks on "download" icon in Maintenance PDF Window
    Then Verify PDF File is downloaded
    And Clean up Downloads folder

  @TEST_TSS-2698 @Automation
  Scenario: History Table Print PDF
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    Then Click on the "printer" icon
    And Validate that Maintenance Log Report panel is displayed
    When User clicks on "printer" icon in Maintenance PDF Window
    Then Verify OS Print dialog