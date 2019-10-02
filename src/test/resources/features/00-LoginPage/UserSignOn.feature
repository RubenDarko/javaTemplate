@Automation @LoginPage @UserSignOn @UserStoryTSS-1 @TSS-2110

Feature: Login Feature

  This feature provides the ability to sign-on to the application in order to provide secure access to functionality and to provide an audit trail for key changes

  @TEST_TSS-2596 @Automation
  Scenario Outline: Verify Account
    Given the Sign In screen is visible
    When a "<username>" has been selected
    And the user enters a "<password>"
    Then set "<password>" as new password if required

    Examples:
      | username      | password        |
      | user          | User123!        |
      | ruben1        | User123!        |
      | ruben2        | User123!        |
      | isra1         | User123!        |
      | isra2         | User123!        |
      | user_reviewer | 4VjXb8Fv6zWHhF! |

  @TEST_TSS-2111 @Automation
  Scenario Outline: Invalid User
    Given the Sign In screen is visible
    When the user enters a "<username>" that is not in the database
    And the user enters a "<password>"
    Then the user is not logged in
    And an error message is displayed

    Examples:
      | username | password  |
      | somebody | something |

  @TEST_TSS-2112 @Automation
  Scenario Outline: Invalid Password
    Given the Sign In screen is visible
    When a "<username>" has been selected
    And the user enters a "<invalid_password>"
    Then the user is not logged in
    And the password field is cleared
    And an error message is displayed

    Examples:
      | username        | invalid_password |
      | test_user_valid | *                |
      | user            | *                |

  @TEST_TSS-2113 @Automation
  Scenario Outline: Valid User Name and Password
    Given the Sign In screen is visible
    When a "<valid_username>" has been selected
    And the user enters a "<valid_password>"
    Then the user is logged in
    And the user is redirected to the Home page

    Examples:
      | valid_username | valid_password |
      | user           | User123!       |

  @TEST_TSS-2114 @Automation
  Scenario Outline: Sign-On with default Password
    Given the Sign In screen is visible
    When a "<valid_username>" has been selected
    And the user enters a "<default_password>"
    Then the User will be prompted to create and confirm a new password

    Examples:
      | valid_username             | default_password |
      | test_user_password_expired | user             |

  @TEST_TSS-2115 @Automation
  Scenario Outline: Expired Password
    Given the Sign In screen is visible
    When a "<valid_username>" has been selected
    And the user enters a "<expired_password>"
    Then the User will be prompted to create and confirm a new password

    Examples:
      | valid_username             | expired_password |
      | test_user_password_expired | user             |