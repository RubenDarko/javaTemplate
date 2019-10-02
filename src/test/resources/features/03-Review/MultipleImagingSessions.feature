@Automation @Review @MultipleImagingSessions @UserStoryTSS-466 @TSS-2045

Feature: Multiple Imaging Sessions

  User needs to be prevented from interacting with Patient Samples when QC fails

  @TEST_TSS-2046 @Automation
  Scenario: Mixed Status Icon
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    And User selects the first Well Sample in the list
    Then The Slide Preview has "2" Wells in Mixed Status

  @TEST_TSS-2047 @Automation
  Scenario: First Instance Selected
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then User selects the first Well Sample in the list