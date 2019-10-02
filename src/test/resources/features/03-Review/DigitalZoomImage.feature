@Automation @Review @DigitalZoomImage @UserStoryTSS-106 @TSS-2311

Feature: Digital Zoom Image

  User needs to digitally zoom a well image up to 2x of the native resolution

  @TEST_TSS-2091 @Automation
  Scenario: Zoom In Sample Image
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User position the mouse over the image
    Then Zoom In Every Sample Image to 2x

  @TEST_TSS-2092 @Automation
  Scenario: Zoom Out Sample Images
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User position the mouse over the image
    Then Zoom In Every Sample Image to 2x
    And Zoom Out Every Sample Image to Original Size