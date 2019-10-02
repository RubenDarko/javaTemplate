@Automation @ApplicationGeneral @EditUserInformation @UserStoryTSS-257 @TSS-2967

Feature: Edit User Information

  Admin needs to edit User's personal information

  @TEST_TSS-2959 @Automation
  Scenario: User Information
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Verify Users Information Table is displayed
    And Select the arrow icon for "User Send Results" row
    Then Verify the following fields are present
      | First name |
      | Last name  |
      | Email      |

  @TEST_TSS-2960 @Automation
  Scenario Outline: Edit User Information with Check Mark
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Verify Users Information Table is displayed
    And Select the arrow icon for "User Send Results" row
    Then Clean up "<Component>" field in User Management
    When User enters "<Value1>" in "<Component>" in User Management
    Then Click on "checkmark" button in System Screen
    And Verify the values in following components in User Management
      | Component   | Value    |
      | <Component> | <Value1> |
    Then Clean up "<Component>" field in User Management
    When User enters "<Value2>" in "<Component>" in User Management
    Then Click on "checkmark" button in System Screen
    And Verify the values in following components in User Management
      | Component   | Value    |
      | <Component> | <Value2> |

    Examples:
    | Component  | Value1        | Value2                      |
    | First name | TestUser      | User                        |
    | Last name  | TestLastName  | Send Results                |
    | Email      | email@etaluma | user_send_results@localhost |

  @TEST_TSS-2961 @Automation
  Scenario Outline: Edit User Information Clicking Outside the Box
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Verify Users Information Table is displayed
    And Select the arrow icon for "User Send Results" row
    Then Clean up "<Component>" field in User Management
    When User enters "<Value1>" in "<Component>" in User Management
    Then User clicks on the field "<Component>" in User Management
    And Verify the values in following components in User Management
      | Component   | Value    |
      | <Component> | <Value1> |
    Then Clean up "<Component>" field in User Management
    When User enters "<Value2>" in "<Component>" in User Management
    Then User clicks on the field "<Component>" in User Management
    And Verify the values in following components in User Management
      | Component   | Value    |
      | <Component> | <Value2> |

    Examples:
      | Component  | Value1        | Value2                      |
      | First name | TestUser      | User                        |
      | Last name  | TestLastName  | Send Results                |
      | Email      | email@etaluma | user_send_results@localhost |

  @TEST_TSS-2962 @Automation
  Scenario Outline: Edit User Information Clicking On The X Icon
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Verify Users Information Table is displayed
    And Select the arrow icon for "User Send Results" row
    Then Clean up "<Component>" field in User Management
    When User enters "<Value1>" in "<Component>" in User Management
    Then Click on "close" button in System Screen
    And Verify the values in following components in User Management
      | Component   | Value    |
      | <Component> | <Value2> |

    Examples:
      | Component  | Value1        | Value2                      |
      | First name | TestUser      | User                        |
      | Last name  | TestLastName  | Send Results                |
      | Email      | email@etaluma | user_send_results@localhost |

  @Automation
  Scenario: Restore Default Values
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Verify Users Information Table is displayed
    And Select the arrow icon for "User Send Results" row
    Then Clean up "First name" field in User Management
    When User enters "User" in "First name" in User Management
    Then Click on "checkmark" button in System Screen
    Then Clean up "Last name" field in User Management
    When User enters "Send Results" in "Last name" in User Management
    Then Click on "checkmark" button in System Screen
    Then Clean up "Email" field in User Management
    When User enters "user_send_results@localhost" in "Email" in User Management
    Then Click on "checkmark" button in System Screen
    And Verify the values in following components in User Management
      | Component  | Value                       |
      | First name | User                        |
      | Last name  | Send Results                |
      | Email      | user_send_results@localhost |

  @TEST_TSS-2963 @Automation
  Scenario: Write An Invalid User Email
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Verify Users Information Table is displayed
    And Select the arrow icon for "User Send Results" row
    Then Clean up "Email" field in User Management
    When User enters "user_send_results@localhost" in "Email" in User Management
    Then Click on "checkmark" button in System Screen
    And Verify the values in following components in User Management
      | Component   | Value                       |
      | Email       | user_send_results@localhost |
    Then Verify message "Error: Invalid email format" is present in System Configuration