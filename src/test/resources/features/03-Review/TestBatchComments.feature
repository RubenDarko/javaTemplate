@Automation @Review @TestBatchComments @UserStoryTSS-137 @TSS-3109

Feature: Test Batch Comments Section

  User needs to change Comments to the Sample

  @TEST_TSS-3076 @Automation
  Scenario: Comments Components
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the first Well in list
    Then Verify that the Label Comments is present
    And Verify that the Comments Edit Button is present
    When User clicks on the Comments Edit Button
    Then Verify that the Add Comments Panel is present
    And Verify that Comment Panel has the title "Add Comments"
    And Verify that the label "Recommendation" is present in Comments Panel
    And Verify that the label "Comments" is present in Comments Panel
    And Verify that the Titer checkbox is present
    And Verify that the ComboBox Starting Ratio 1 is present
    And Verify that the ComboBox Ending Ratio 1 is present
    And Verify that the "Save" Button is present
    And Verify that the "Cancel" Button is present
    And Verify that the X Button is present

  @TEST_TSS-3075 @Automation
  Scenario: Comment Not Change On X Button
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the second Well in list
    Then Verify that the Comments Edit Button is present
    When User clicks on the Comments Edit Button
    Then Verify that the Add Comments Panel is present
    When User clear the comments in the text area
    And User types "Automation" in the text area
    And User clicks on the Comment Panel X Button
    Then Verify that the Comment Panel is closed
    And Verify that the Comment has not change

  @TEST_TSS-3074 @Automation
  Scenario: Comment Not Change On Cancel Button
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the second Well in list
    Then Verify that the Comments Edit Button is present
    When User clicks on the Comments Edit Button
    Then Verify that the Add Comments Panel is present
    When User clear the comments in the text area
    And User types "Automation" in the text area
    And User clicks on the Comments Panel "Cancel" Button
    Then Verify that the Comment Panel is closed
    And Verify that the Comment has not change

  @TEST_TSS-3073 @Automation
  Scenario: Titer Validation
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the second Well in list
    Then Verify that the Comments Edit Button is present
    When User clicks on the Comments Edit Button
    Then Verify that the Add Comments Panel is present
    When User clicks on the Titer Checkbox
    Then Verify that the Starting Ratio 1 dropdown is enabled
    And Verify that the Ending Ratio 1 dropdown is enabled
    When User clicks on the Comments Panel "Save" Button
    Then Verify that the Comment Panel is closed
    And Verify that the Comments section contains Titer text
    When User clicks on the Comments Edit Button
    Then Verify that the Add Comments Panel is present
    And Verify that the Titer checkbox is unchecked
    When User clicks on the Comments Panel "Save" Button
    Then Verify that the Comment Panel is closed
    And Verify that the Comments section not contains Titer text

  @TEST_TSS-3072 @Automation
  Scenario: Starting Ratio Greater Than Ending Ratio
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the second Well in list
    Then Verify that the Comments Edit Button is present
    When User clicks on the Comments Edit Button
    Then Verify that the Add Comments Panel is present
    When User clicks on the Titer Checkbox
    Then Verify that the Starting Ratio 1 dropdown is enabled
    And Verify that the Ending Ratio 1 dropdown is enabled
    And Verify that the Comments Panel "Save" Button is not enable with the following values
      | Value1 | Value2 |
      | 2560   | 20     |
      | 1280   | 40     |
      | 640    | 80     |
      | 320    | 160    |

  @TEST_TSS-3071 @Automation
  Scenario: Ending Ratio Greater Than Starting Ratio
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    And Select the second Well in list
    Then Verify that the Comments Edit Button is present
    When User clicks on the Comments Edit Button
    Then Verify that the Add Comments Panel is present
    When User clicks on the Titer Checkbox
    Then Verify that the Starting Ratio 1 dropdown is enabled
    And Verify that the Ending Ratio 1 dropdown is enabled
    And Verify that the Comments Panel "Save" Button is enable with the following values
      | Value1 | Value2 |
      | 20     | 20     |
      | 20     | 40     |
      | 40     | 80     |
      | 80     | 160    |
      | 160    | 320    |
      | 320    | 640    |
      | 640    | 1260   |
      | 1260   | 2560   |
      | 2560   | 2560   |
    When User clicks on the Comments Panel "Save" Button
    Then Verify that the Comment Panel is closed
    And Verify that the Comments section contains Titer text