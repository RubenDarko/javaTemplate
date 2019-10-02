@Automation @Review @ReferenceLibraryInformation @UserStoryTSS-314 @TSS-1858

Feature: Reference Library Information

  User needs to view the brief textual description of the selected Library Pattern

  @TEST_TSS-1861 @Automation
  Scenario Outline: Show Reference Library Information With Icon
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Information Icon is displayed
    When User clicks on the Information Icon
    Then Verify message "<pattern>" is present

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1862 @Automation
  Scenario Outline: Show Reference Library Information With Dropdown
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Information Dropdown is displayed
    When User clicks on the Information Dropdown
    Then Verify message "<pattern>" is present

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1863 @Automation
  Scenario Outline: Show Reference Library Information For All Images
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Navigate through all Images in the Pattern
    And Verify that the Information Icon is displayed
    When User clicks on the Information Icon
    Then Verify message "<pattern>" is present

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1864 @Automation
  Scenario Outline: Hide Reference Library Information With Icon
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Information Icon is displayed
    When User clicks on the Information Icon
    Then Verify message "<pattern>" is present
    When User clicks on the Information Icon
    Then Verify Overlay does not show the Information

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1865 @Automation
  Scenario Outline: Hide Reference Library Information With Dropdown
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Information Dropdown is displayed
    When User clicks on the Information Dropdown
    Then Verify message "<pattern>" is present
    When User clicks on the Information Dropdown
    Then Verify Overlay does not show the Information

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |

  @TEST_TSS-1866 @Automation
  Scenario Outline: Clear Reference Library Information With X Icon
    Given The Review Page Is Active
    When User expands Batch "HEp2-20200826B" from the list in the left side
    Then Select the first Well in list
    When User selects "<pattern>" in the Reference Library combo box
    Then Verify that the Information Icon is displayed
    When User clicks on the Information Icon
    Then Verify message "<pattern>" is present
    When Close the Pattern Library Overlay
    And Verify message "Select a pattern from the menu above to view a reference image." is present

    Examples:
      | pattern     |
      | Homogeneous |
      | Cytoplasmic |
      | Nucleardots |