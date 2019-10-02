@Automation @Review @AddPatternToResult @UserStoryTSS-314 @TSS-1860

Feature: Add Pattern To Result

  User needs to add the selected Library Pattern to the Result

  @TEST_TSS-1875 @Automation
  Scenario Outline: Add Pattern Result Icon Inactive
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    When User selects "<pattern>" in the Reference Library combo box
    And User selects "Select a Pattern" in the Reference Library combo box
    Then Verify message "Select a pattern from the menu above to view a reference image." is present
    And Verify that the Plus Icon is disabled
    Then Clean up from patterns

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1876 @Automation
  Scenario Outline: Add Pattern Result Icon Active When Select a Reference
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Plus Icon is enabled

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1877 @Automation
  Scenario Outline: Add Pattern Result
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
    And User selects "<intensity>" in "Intensity" Combo box
    When User clicks on "save" Icon
    Then Verify "<pattern>" and "<intensity>" in Text field
    When User clicks on Close Panel Icon
    Then Verify "<pattern>" and "<intensity>" in Patterns

    Examples:
      | pattern     | intensity |
      | Homogeneous | 1+        |
      | Cytoplasmic | 2+        |
      | Nucleardots | 3+        |

  @TEST_TSS-1878 @Automation
  Scenario Outline: Edit Pattern Result
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
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

  @TEST_TSS-1879 @Automation
  Scenario Outline: Add Pattern Result Inactive With Reference Added
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
    When User selects "<pattern>" in the Reference Library combo box
    And Verify that the Plus Icon is disabled

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1880 @Automation
  Scenario Outline: Delete Pattern Result
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well to approve
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
    When User clicks on Pattern "<pattern>" with Intensity "<deleteInt>" in Text field
    When User clicks on "trash" Icon

    Examples:
      | pattern     | deleteInt |
      | Homogeneous | 2+        |
      | Cytoplasmic | 3+        |
      | Nucleardots | 4+        |