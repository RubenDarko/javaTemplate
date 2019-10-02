@Automation @Review @ReferenceLibraryPreview @UserStoryTSS-315 @TSS-2548

Feature: Reference Library Preview

  User needs to view / download / print the full textual description of the selected Library Pattern

  @TEST_TSS-2266 @Automation
  Scenario Outline: Reference Library Preview
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Information Icon is displayed
    When User clicks on the Information Icon
    Then Verify message "<pattern>" is present
    And Verify icon "file-view" is present in Reference Library Information
    When User clicks on File View icon in Reference Library Information
    Then Verify that Preview PDF Window is opened
    And Verify the title is "<pattern> Pattern Description"
    Then Verify Close Panel Icon
    And Verify following icons in Preview PDF Window
      | printer  |
      | download |
      | minus    |
      | plus     |
    Then Verify Page Number is present in footer

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |