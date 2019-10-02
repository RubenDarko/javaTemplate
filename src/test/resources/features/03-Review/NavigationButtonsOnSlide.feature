@Automation @Review @NavigationButtonsOnSlide @UserStoryTSS-233 @TSS-1715

Feature: Navigation Buttons On Slide

  User needs to Navigate forward and backward through the Wells on the Slide

  @TEST_TSS-1726 @Automation
  Scenario: User Navigates Forward and Backward
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    And Verify Forward Arrow is enabled
    Then Click on the Forward Arrow
    And Verify the following sample in list is selected
    And Verify Backward Arrow is enabled
    Then Click on the Backward Arrow
    And Verify the previous sample in list is selected

  @TEST_TSS-1727 @Automation
  Scenario: User Tests Navigation Buttons Are Disabled With Mouse
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    And Verify Forward Arrow is enabled
    Then Click on the Forward Arrow
    And Verify the following sample in list is selected
    And Verify Backward Arrow is enabled
    Then Click on the Backward Arrow
    And Verify the previous sample in list is selected
    And Verify Backward Arrow is disabled
    Then Select the last Well in list
    And Verify Backward Arrow is enabled
    Then Click on the Backward Arrow
    And Verify the previous sample in list is selected
    And Verify Forward Arrow is enabled
    Then Click on the Forward Arrow
    And Verify the following sample in list is selected
    And Verify Forward Arrow is disabled

  @TEST_TSS-1728 @Automation
  Scenario: User Tests Navigation Buttons Are Disabled With Keyboard
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    And Verify Forward Arrow is enabled
    Then Press the Down Arrow in the keyboard
    And Verify the following sample in list is selected
    And Verify Backward Arrow is enabled
    Then Press the Up Arrow in the keyboard
    And Verify the previous sample in list is selected
    And Verify Backward Arrow is disabled
    Then Select the last Well in list
    And Verify Backward Arrow is enabled
    Then Press the Up Arrow in the keyboard
    And Verify the previous sample in list is selected
    And Verify Forward Arrow is enabled
    Then Press the Down Arrow in the keyboard
    And Verify the following sample in list is selected
    And Verify Forward Arrow is disabled