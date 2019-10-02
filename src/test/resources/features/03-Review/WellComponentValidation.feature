@Automation @Review @WellComponentValidation @UserStoryTSS-103 @TSS-3111

Feature: Well Component Validation

  User needs view/navigate to images for selected image channel (FITC, DAPI) for the selected Well

  @TEST_TSS-3088 @Automation
  Scenario: Well Components
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the first Well in list
    Then Verify that the Sample Image label is present
    And Verify that the "FITC" Button is present
    And Verify that the "DAPI" Button is present
    And Verify that the "FITC/DAPI" Button is present
    And Verify that an Image is present under Sample Image label
    And Verify that the image has dots
    And Verify that the image has Left arrow
    And Verify that the image has Right arrow
    And Verify that the image has Minus Button
    And Verify that the image has Plus Button
    And Verify that the image has a Report Image Icon
    And Verify that the image has a Image Meta Data Icon

  @TEST_TSS-3089 @Automation
  Scenario: Well Image Navigation With Arrows
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the first Well in list
    Then User navigates through all the Well Images with the Next Arrow
    And User navigates through all the Well Images with the Back Arrow

  @TEST_TSS-3094 @Automation
  Scenario: Well Results Section
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the first Well to approve
    Then Verify that the Results label is present
    And Verify that the "sample-negative" is present
    And Verify that the "sample-reject" is present
    And Verify that the "sample-positive" is present
    And Verify that the file-history Icon is present

  @TEST_TSS-3093 @Automation
  Scenario: Change Well Results
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And User selects the first Positive Well in list
    Then Verify that the "sample-positive" is selected
    Then Verify that the "sample-reject" is not selected
    Then Verify that the "sample-negative" is not selected
    When User clicks on the "sample-negative"
    Then Verify that the "sample-negative" is selected
    Then Verify that the "sample-positive" is not selected
    Then Verify that the "sample-reject" is not selected
    When User clicks on the "sample-reject"
    Then Verify that the "sample-reject" is selected
    Then Verify that the "sample-negative" is not selected
    Then Verify that the "sample-positive" is not selected
    And User clicks on the "sample-positive"