@Automation @Review @ReferenceLibraryPatterns @UserStoryTSS-107 @TSS-1750

Feature: Reference Library Patterns

  User needs to choose a Library Pattern for comparison with the selected Sample Image

  @TEST_TSS-1751 @Automation
  Scenario: Components without Reference Library
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "Select a Pattern" in the Reference Library combo box
    Then Verify that the Plus Icon is displayed
    And Verify message "Select a pattern from the menu above to view a reference image." is present

  @TEST_TSS-1752 @Automation
  Scenario Outline: Components with Reference Library
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Plus Icon is displayed
    And Verify that the Pattern Image is displayed

    Examples:
    | pattern     |
    | Homogeneous |
    | Cytoplasmic |
    | Nucleardots |

  @TEST_TSS-1753 @Automation
  Scenario Outline: Change from a Reference Library to a different one
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<patternOne>" in the Reference Library combo box
    Then Verify that the Plus Icon is displayed
    And Verify that the Pattern Image is displayed
    When User selects "<patternTwo>" in the Reference Library combo box
    Then Verify that the Plus Icon is displayed
    And Verify that the Pattern Image is displayed

    Examples:
      | patternOne  | patternTwo  |
      | Homogeneous | Cytoplasmic |
      | Homogeneous | Nucleardots |
      | Cytoplasmic | Homogeneous |
      | Cytoplasmic | Nucleardots |
      | Nucleardots | Homogeneous |
      | Nucleardots | Cytoplasmic |

  @TEST_TSS-1754 @Automation
  Scenario Outline: Select all Reference Images
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Plus Icon is displayed
    And Verify that the Pattern Image is displayed
    Then Navigate through all Images in the Pattern

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1755 @Automation
  Scenario Outline: Hide a Reference Library
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Plus Icon is displayed
    And Verify that the Pattern Image is displayed
    Then Close the Pattern Library Overlay
    And Verify message "Select a pattern from the menu above to view a reference image." is present

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |
