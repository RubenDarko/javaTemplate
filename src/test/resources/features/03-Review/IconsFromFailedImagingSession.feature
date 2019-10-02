@Automation @Review @IconsFromFailedImagingSession @UserStoryTSS-1342 @TSS-2665

Feature: Icons From Failed Imaging Session

  User needs to see the icons being updated when the QC samples pass or fail in an Imaging Session

  @TEST_TSS-2307 @Automation
  Scenario: Failed Sample Well Components
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    And Verify QC Failed Icon
    And Verify message "QC Failed" is present
    And Verify message "The following QC failed for Session ID" is present
    And Verify buttons for Failed QC samples

  @TEST_TSS-2308 @Automation
  Scenario: Modify a Fail Sample Well
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    When User clicks on the first failed well
    And Select "positive" button in Result
    Then Verify that well result has not changed
    And Select "negative" button in Result

  @TEST_TSS-2309 @Automation
  Scenario: Change Sample Well Status to Fail
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    And Select "negative" button in Result
    Then Verify Failed Icon and Label
    And Verify that all related wells has changed
    Then Select "positive" button in Result