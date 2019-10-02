@Automation @ApplicationGeneral @UserManagement @UserStoryTSS-255 @TSS-2966

Feature: User Management

  Admin needs to select a User from a list of users

  @TEST_TSS-2955 @Automation
  Scenario: User Management User Icon
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Verify "user-add" Icon in System Configuration
    When User clicks on "user-add" Icon in System Configuration
    Then Verify the next Text Fields and Placeholders in User Management
      | Field      | Placeholder              |
      | First name | Enter First name         |
      | Last name  | Enter Last name          |
      | Email      | Enter Email              |
      | Password   | Enter Temporary Password |
    And Verify "Role" Combo Box in User Management
    Then Verify the next Permissions in System Configuration
      | Create New Test Batch      |
      | Run Imager - Auto          |
      | Run Imager - Manual        |
      | Change Imager Setting      |
      | Review/Edit Results        |
      | Approve/Disapprove Results |
      | Perform Maintenance        |
      | Send Results               |
      | Receive Notifications      |
      | View Archived Results      |

  @TEST_TSS-2956 @Automation
  Scenario: User Management Admin Role
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "User Management" option in System Menu
    Then Select the arrow icon for "User Send Results" row
    And Verify permission "Send Results" is in status "on"

  @TEST_TSS-2957 @Automation
  Scenario: User Management Not Admin Role
    Given User Reviewer Access the Home Page
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    And Verify message "You are not authorized to perform this action" is present in System Configuration