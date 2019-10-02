@Automation @Review @TestBatchSummary @UserStoryTSS-235 @TSS-2385

Feature: Test Batch Summary

  User needs to view a summary of the Test Batch

  @TEST_TSS-2191 @Automation
  Scenario: Test Batches first option selected
    Given The Review Page Is Active
    When The Test Batches list in the left side is collapsed
    Then The first Test Batch is selected

  @TEST_TSS-2192 @Automation
  Scenario: Test Batches list info section
    Given The Review Page Is Active
    And The Test Batches list in the left side is collapsed
    Then The first Test Batch is selected
    Then Verify "HEp2-20200826B" in Batch info section
    And Verify "Type: HEp-2" in Batch info section
    And Verify "Lot: 1810245B" in Batch info section

  @TEST_TSS-2193 @Automation
  Scenario: Test Batch Summary content with QC Failed
    Given The Review Page Is Active
    And The Test Batches list in the left side is collapsed
    Then User selects the Test Batch "ANCA-20210916C" in left list
    And Verify the next messages in Test Batch Summary
      | Test Batch Summary  |
      | Kit Lot #           |
      | Exp. Date           |
      | Cofactor Value      |
      | Source              |
      | Slide Type          |
      | Slide Mfg Part      |
      | #                   |
      | Well Count          |
      | Mask Color          |
      | Created By          |
      | Created Time        |
      | Last Updated        |
      | Worklist            |
      | Imaging Sessions    |
      | Session ID          |
      | Slides              |
      | Samples             |
      | Positive Ctrl       |
      | Negative Ctrl       |
      | Patient Sample      |
      | Imaged              |
      | Confirmed           |
      | Approved            |
      | QC Failed           |
    Then Verify the next icons by label
      | Imaged    |
      | Confirmed |
      | Approved  |
    And Verify the next icons by type
      | uncertain |
      | negative  |
      | positive  |
      | rejected  |
      | warning   |
      | file-pdf  |
      | archive   |