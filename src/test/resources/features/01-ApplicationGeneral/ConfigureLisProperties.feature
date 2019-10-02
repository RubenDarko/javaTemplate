@Automation @ApplicationGeneral @ConfigureLisProperties @UserStoryTSS-252 @TSS-2701

Feature: Configure LIS Properties

  Admin needs to configure LIS properties for the application

  @TEST_TSS-2734 @Automation
  Scenario: LIS Components
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "LIS" option in System Menu
    Then Verify title is "Configuration - LIS" in System Configuration
    And Verify radio button "Bidirectional HL7 Interface" in Configuration LIS
    Then Verify radio button "Unidirectional Interface (File Drop)" in Configuration LIS
    And Verify message "Transmit Results to LIS" is present in Configuration LIS
    Then Verify message "Query Patient Data from LIS" is present in Configuration LIS
    And Verify message "IP Address" is present in Configuration LIS
    Then Verify message "Port" is present in Configuration LIS
    And Verify message "Transmit Results to LIS" is present in Configuration LIS
    Then Verify message "File Path" is present in Configuration LIS
    And Verify "Transmit Results to LIS" toggle button
    Then Verify "Transmit Results to LIS" toggle button
    And Verify "Query Patient Data from LIS" toggle button
    When Click on component "IP Address" in System Screen
    Then Verify "checkmark" button is present
    And Verify "close" button is present
    When Click on component "Port" in System Screen
    Then Verify "checkmark" button is present
    And Verify "close" button is present
    Then Verify the Test Connection button is present

  @TEST_TSS-2735 @Automation
  Scenario: LIS Default Values
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "LIS" option in System Menu
    Then Verify radio button "Bidirectional HL7 Interface" is checked
    And Verify radio button "Unidirectional Interface (File Drop)" is unchecked
    When Click on component "IP Address" in System Screen
    Then Verify that the default value in "IP Address" is empty
    When Click on component "Port" in System Screen
    Then Verify that the default value in "Port" is empty
    And Verify "Transmit Results to LIS" toggle button is "false"
    Then Verify "Transmit Results to LIS" toggle button is "false"
    And Verify "Query Patient Data from LIS" toggle button is "false"

  @TEST_TSS-2736 @Automation
  Scenario Outline: LIS Toggle Buttons
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "LIS" option in System Menu
    Then Click on radio button "<Radio>" in Configuration LIS
    And Verify "<Toggle>" toggle button
    Then Click on "<Toggle>" toggle button
    And Verify "<Toggle>" toggle button attribute "value" is "true"
    Then Click on "<Toggle>" toggle button
    And Verify "<Toggle>" toggle button attribute "value" is "false"
    Then Click on radio button "Bidirectional HL7 Interface" in Configuration LIS

    Examples:
    | Radio                       | Toggle                      |
    | Bidirectional HL7 Interface | Transmit Results to LIS     |
    | Bidirectional HL7 Interface | Query Patient Data from LIS |

  @TEST_TSS-2737 @Automation
  Scenario Outline: LIS Input Validations
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "LIS" option in System Menu
    Then Clean up "<Component>" field in System Screen
    When User enters "<Value>" in "<Component>"
    Then Click on "checkmark" button in System Screen
    And Verify the values in following components
      | Component   | Value   |
      | <Component> | <Value> |
    When User enters "<Value2>" in "<Component>"
    Then User clicks on the "close" button in System Screen
    And Verify the values in following components
      | Component   | Value   |
      | <Component> | <Value> |
    Then Clean up "<Component>" field in System Screen
    And Click on component "<Component>" in System Screen
    And Verify that the default value in "<Component>" is empty

    Examples:
    | Component  | Value       | Value2      |
    | IP Address | 10.10.10.10 | 10.10.10.11 |
    | Port       | 8080        | 8088        |