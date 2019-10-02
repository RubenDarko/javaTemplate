@Automation @Review @SampleResultAuditTrail @UserStoryTSS-229 @TSS-2371

Feature: Sample Audit Trail

  User needs to view the Sample Result Audit Trail

  @TEST_TSS-2140 @Automation
  Scenario: Audit button is present
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    And Verify Audit button is present

  @TEST_TSS-2142 @Automation
  Scenario: Audit button components
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    And Verify Audit button is present
    When User clicks on the Audit button
    Then Verify "Slide ID" text is present in Audit Trial
    And Verify "Well #" text is present in Audit Trial
    Then Verify following columns are present
      | Time     |
      | Result   |
      | Patterns |
      | Comments |
      | State    |
      | User     |
    And Verify Time column cell date format is "hh:mm:ss yyyy-MM-dd"
    Then Verify X button is present in Audit Trial

  @TEST_TSS-2143 @Automation
  Scenario: Audit button is present
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    And Verify Audit button is present
    When User clicks on the Audit button
    Then Verify X button is present in Audit Trial
    And Click on the X button to close Audit Trial
