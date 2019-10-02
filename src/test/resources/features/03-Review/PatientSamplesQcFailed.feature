@Automation @Review @PatientSamplesQcFailed @UserStoryTSS-463 @TSS-1958

Feature: Patient Samples QC Failed

  User needs to be prevented from interacting with Patient Samples when QC fails

  @TEST_TSS-1967 @Automation
  Scenario: Failed Samples Components Validation
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    And Verify QC Failed Icon
    And Verify message "QC Failed" is present
    And Verify message "The following QC failed for Session ID" is present
    And Verify buttons for Failed QC samples

  @TEST_TSS-1968 @Automation
  Scenario: Failed Samples Tooltip
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    And Verify Tooltip when mouse over the failed sample

  @TEST_TSS-1969 @Automation
  Scenario: Select Different Samples
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    And Verify navigation when Different Wells are selected

  @TEST_TSS-1970 @Automation
  Scenario: Sample With Failed Label Components Validation
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    And Verify Failed Icon and Label

  @TEST_TSS-1971 @Automation
  Scenario: No Interaction With Sample Results
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    And Verify user cannot interact with page components behind the Overlay

  @TEST_TSS-1972 @Automation
  Scenario: Interactions With Sample Results
    Given The Review Page Is Active
    When User expands Batch "ANCA-20210916C" from the list in the left side
    Then Select the first Well failed QC in list
    And Verify navigation when Different Wells are selected
    And Verify Failed Icon and Label