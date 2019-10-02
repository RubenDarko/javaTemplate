@Automation @Review @ViewMitoticCell @UserStoryTSS-359 @TSS-3105

Feature: Mitotic Elements

  User needs to view the mitotic cell thumbnails for the selected image

  @TEST_TSS-3013 @Automation
  Scenario Outline: Mitotic Elements
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the last Well in list to approve
    When User clicks on the button "<Button>"
    Then Verify Sample Image is displayed
    Then Verify Mitotic Components are present
    And Verify Mitotic Components have title

    Examples:
      | Button    |
      | FITC      |
      | DAPI      |
      | FITC/DAPI |