@Automation @Review @MetaDataService @UserStoryTSS-231 @TSS-2700

Feature: Meta Data Service

  User needs to view Classification Service meta-data of the selected Image

  @TEST_TSS-2725 @Automation
  Scenario: User Approves the first Well Sample Available
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    And Verify "imagemeta" Icon is displayed
    When User clicks on the "imagemeta" icon in Review Page
    And Validate that Image Meta Data panel is displayed
    Then Verify message "Image Meta Data" is present
    And Verify Image Meta Data contains "Slide ID:"
    Then Verify Image Meta Data contains "Well #:"
    And Verify Image Meta Data contains "Sample ID:"
    Then Verify Image Meta Data contains "Image:"