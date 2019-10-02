@Automation @Archive @QueryArchivedPatientResults @UserStoryTSS-264 @TSS-2930

Feature: Query Archived Patient Results

  User needs to Query Archived Patient Results

  @TEST_TSS-2931 @Automation
  Scenario: Query Criteria Components
    Given The Archive Page Is Active
    Then Verify the next Radio Buttons are present in Archive Page
      | Batch Name |
      | Slide ID   |
      | Sample ID  |
    And Verify the next Text Fields and Placeholders in Archive Page
      | Field    | Placeholder    |
      | Sample   | Enter          |
      | KitLot   | Enter Kit Lot  |
      | WorkList | Enter WorkList |
    Then Verify the next Combo Boxes are present in Archive Page
      | ComboBox   | Label             |
      | User       | Select User       |
      | Slide Type | Select Slide Type |
      | Patterns   | Select Pattern    |
    And Verify the next Results Check Boxes are present in Archive Page
      | Negative |
      | Positive |
      | Rejected |
    Then Verify the next User Edited Buttons are present in Archive Page
      | Yes |
      | No  |
      | Any |
    And Verify the next Icons are present in Archive Page
      | Clear  |
      | Search |

  @TEST_TSS-2932 @Automation
  Scenario Outline: Search Criteria Results
    Given The Archive Page Is Active
    Then Click on "Search" icon in Archive Page
    And Store temporal result values
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page
    When Select "<Pattern>" in "Patterns" Combo Box
    Then Click on "Search" icon in Archive Page
    And Validate all results contain "<Pattern>" in column "Patterns"
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page
    When Select "<SlideType>" in "Slide Type" Combo Box
    Then Click on "Search" icon in Archive Page
    And Validate all results contain "<SlideType>" in column "Slide Type"
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page
    When Select "<Results>" Check Box
    Then Click on "Search" icon in Archive Page
    And Validate all results contain "<Results>" in column "Results"
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page
    When Select "<Pattern>" in "Patterns" Combo Box
    Then Select "<SlideType>" in "Slide Type" Combo Box
    And Select "<Results>" Check Box
    Then Click on "Search" icon in Archive Page
    And Validate all results contain "<Pattern>" in column "Patterns"
    Then Validate all results contain "<SlideType>" in column "Slide Type"
    And Validate all results contain "<Results>" in column "Results"
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page
    Then Enter "Batch Name" and validate results
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page
    Then Enter "Slide ID" and validate results
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page
    Then Enter "Sample ID" and validate results
    Then Click on "Clear" icon in Archive Page
    And Click on "Search" icon in Archive Page

    Examples:
      | Pattern     | SlideType   | Results  |
      | Nucleardots | Double ANCA | Positive |
      | Nucleardots | nDNA        | Negative |
      | Nucleardots | HEp-2       | Positive |
      | Homogeneous | Double ANCA | Negative |
      | Homogeneous | nDNA        | Positive |
      | Homogeneous | HEp-2       | Negative |
      | Cytoplasmic | Double ANCA | Positive |
      | Cytoplasmic | nDNA        | Negative |
      | Cytoplasmic | HEp-2       | Positive |

  @TEST_TSS-2933 @Automation
  Scenario Outline: No Results
    Given The Archive Page Is Active
    When Select "<Pattern>" in "Patterns" Combo Box
    Then Select "<SlideType>" in "Slide Type" Combo Box
    And Select "<Results>" Check Box
    Then Select "<Radio>" Radio Button and enter Value "invalidValue"
    Then Click on "Search" icon in Archive Page
    And Validate message "Enter search criteria above and click on search to view results" is displayed in Archive Page

    Examples:
      | Pattern     | SlideType   | Results  | Radio      |
      | Nucleardots | Double ANCA | Positive | Batch Name |
      | Homogeneous | nDNA        | Positive | Slide  ID  |
      | Cytoplasmic | HEp-2       | Positive | Sample ID  |

  @TEST_TSS-2934 @Automation
  Scenario: Clear Criteria
    Given The Archive Page Is Active
    When Select "any" in "Patterns" Combo Box
    Then Select "any" in "Slide Type" Combo Box
    And Select "any" in "User" Combo Box
    Then Select "Sample ID" Radio Button and enter Value "invalidValue"
    And Select "Negative" Check Box
    Then Select "Positive" Check Box
    And Select "Rejected" Check Box
    Then Enter "Test Kit Lot" in "KitLot" text field
    And Enter "Test Sample" in "Sample" text field
    Then Enter "Test WorkList" in "Worklist" text field
    And Click on "Clear" icon in Archive Page
    And Verify the next Text Fields are empty in Archive Page
      | Sample   |
      | KitLot   |
      | WorkList |
    Then Verify the next Combo Boxes are empty in Archive Page
      | User       |
      | Slide Type |
      | Patterns   |
    And Verify the next Results Check Boxes are unchecked in Archive Page
      | Negative |
      | Positive |
      | Rejected |