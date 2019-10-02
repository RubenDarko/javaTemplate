@Automation @ApplicationGeneral @MaintenanceDateRange @UserStoryTSS-307 @TSS-2703

Feature: Maintenance Date Range

  User needs to query the maintenance history by a date range

  @TEST_TSS-2755 @Automation
  Scenario: Query Maintenance History Components
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    And User clicks on "clear" icon in Maintenance Page
    Then Verify message "Date Range" is present in System Configuration
    And Verify Date Range Picker dates
    Then Verify the Date Range component is present
    And Verify "search" Icon in System Configuration
    And Verify "clear" Icon in System Configuration

  @TEST_TSS-2756 @Automation
  Scenario: Reset Button
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    And User clicks on "clear" icon in Maintenance Page
    Then Verify the Date Range component is present
    Then Verify message "Date Range" is present in System Configuration
    And Verify Date Range Picker dates

  @TEST_TSS-2757 @Automation
  Scenario Outline: Search Days Records
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    And Schedule a sample maintenance for testing
    Then Verify the Date Range component is present
    And User clicks on "clear" icon in Maintenance Page
    When User clicks on "calendar" icon in Maintenance Page
    Then Verify Date Range window is present
    And Select the "<Range>" in Date Range Picker
    When User clicks on Apply button in Date Range Picker
    Then Verify Date Range window is not present
    When User clicks on "search" icon in Maintenance Page
    Then Validate records in table match the date range

    Examples:
      | Range     |
      | Yesterday |
      | Tomorrow  |

  @TEST_TSS-2758 @Automation
  Scenario Outline: Search Week Records
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Maintenance" option in System Menu
    And Schedule a sample maintenance for testing
    Then Verify the Date Range component is present
    And User clicks on "clear" icon in Maintenance Page
    When User clicks on "calendar" icon in Maintenance Page
    Then Verify Date Range window is present
    And Select the "<Week>" in Date Range Picker
    When User clicks on Apply button in Date Range Picker
    Then Verify Date Range window is not present
    When User clicks on "search" icon in Maintenance Page
    Then Validate records in table match the date range

    Examples:
      | Week     |
      | Previous |
      | Next     |