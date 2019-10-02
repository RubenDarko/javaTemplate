@Automation @Review @SelectedSamplesResult @UserStoryTSS-358 @TSS-3106

Feature: Selected Samples Result

  User needs to view the selected sample's results when selecting from the sample list

  @TEST_TSS-3034 @Automation
  Scenario: Result Components
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    And Verify following icons in Sample Result are present
      | Reject   |
      | Negative |
      | Positive |

  @TEST_TSS-3035 @Automation
  Scenario Outline: Positive, Negative and Reject Icons
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    And Store selected sample result
    When User selects "<Result1>" in Sample Result
    Then Verify "<Result1>" Icon in Sample Result is selected
    And Verify "<Result2>" Icon in Sample Result is not selected
    Then Verify "<Result3>" Icon in Sample Result is not selected
    And Select the initial sample result

    Examples:
      | Result1  | Result2  | Result3  |
      | Negative | Positive | Reject   |
      | Positive | Negative | Reject   |
      | Reject   | Positive | Negative |

  @TEST_TSS-3036 @Automation
  Scenario: Patterns Components
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    And Verify "edit" Icon is displayed

  @TEST_TSS-3037 @Automation
  Scenario Outline: Adding and Removing Patterns
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the last Well in list to approve
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Plus Icon is enabled
    When User clicks on Add Pattern To Result button
    Then Verify Result Patterns Panel is displayed
    And Verify message "Select an existing pattern label below to edit." is present
    And Verify Patterns Text field is displayed
    And Verify "Pattern" Combo box is displayed
    And Verify "Intensity" Combo box is displayed
    And Verify "clear" Icon is displayed
    And Verify "save" Icon is displayed
    And Verify "trash" Icon is displayed
    And Verify Close Panel Icon
    When User selects "<pattern>" in "Pattern" Combo box
    And User selects "<intensity>" in "Intensity" Combo box
    When User clicks on "save" Icon
    Then Verify "<pattern>" and "<intensity>" in Text field
    When User clicks on Close Panel Icon
    Then Verify "<pattern>" and "<intensity>" in Patterns
    When User clicks on edit button for "Patterns"
    Then Verify Result Patterns Panel is displayed
    And Verify message "Select an existing pattern label below to edit." is present
    And Verify Patterns Text field is displayed
    And Verify "Pattern" Combo box is displayed
    And Verify "Intensity" Combo box is displayed
    And Verify "clear" Icon is displayed
    And Verify "save" Icon is displayed
    And Verify "trash" Icon is displayed
    And Verify Close Panel Icon
    When User clicks on Pattern "<pattern>" with Intensity "<intensity>" in Text field
    When User clicks on "trash" Icon

    Examples:
      | pattern     | intensity |
      | Homogeneous | 1+        |
      | Cytoplasmic | 2+        |
      | Nucleardots | 3+        |

  @TEST_TSS-3038 @Automation
  Scenario Outline: Update Pattern Intensity
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Plus Icon is enabled
    When User clicks on Add Pattern To Result button
    Then Verify Result Patterns Panel is displayed
    And Verify message "Select an existing pattern label below to edit." is present
    And Verify Patterns Text field is displayed
    And Verify "Pattern" Combo box is displayed
    And Verify "Intensity" Combo box is displayed
    And Verify "clear" Icon is displayed
    And Verify "save" Icon is displayed
    And Verify "trash" Icon is displayed
    And Verify Close Panel Icon
    When User selects "<pattern>" in "Pattern" Combo box
    And User selects "<fromInt>" in "Intensity" Combo box
    When User clicks on "save" Icon
    Then Verify "<pattern>" and "<fromInt>" in Text field
    When User clicks on Close Panel Icon
    Then Verify "<pattern>" and "<fromInt>" in Patterns
    When User clicks on edit button for "Patterns"
    Then Verify Result Patterns Panel is displayed
    And Verify message "Select an existing pattern label below to edit." is present
    And Verify Patterns Text field is displayed
    And Verify "Pattern" Combo box is displayed
    And Verify "Intensity" Combo box is displayed
    And Verify "clear" Icon is displayed
    And Verify "save" Icon is displayed
    And Verify "trash" Icon is displayed
    And Verify Close Panel Icon
    When User clicks on Pattern "<pattern>" with Intensity "<fromInt>" in Text field
    And User selects "<toInt>" in "Intensity" Combo box
    When User clicks on "save" Icon
    Then Verify "<pattern>" and "<toInt>" in Text field
    When User clicks on Close Panel Icon
    Then Verify "<pattern>" and "<toInt>" in Patterns

    Examples:
      | pattern     | fromInt | toInt |
      | Homogeneous | 1+      | 2+    |
      | Cytoplasmic | 2+      | 3+    |
      | Nucleardots | 3+      | 4+    |

  @TEST_TSS-3039 @Automation
  Scenario: Comments Section
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    And User clicks on edit button for "Comments"
    Then Verify that the Add Comments Panel is present
    And Verify that Comment Panel has the title "Add Comments"
    When User types "Comment for Test" in the text area
    Then User clicks on the Comments Panel "Save" Button
    And Verify that the Comment Panel is closed
    Then Verify "Comment for Test" in Comments section
    And User clicks on edit button for "Comments"
    Then Verify that the Add Comments Panel is present
    And Verify that Comment Panel has the title "Add Comments"
    When User types "DRVision mock result" in the text area
    Then User clicks on the Comments Panel "Save" Button
    And Verify that the Comment Panel is closed
    Then Verify "DRVision mock result" in Comments section