@Automation @Review @PreviewTestBatchReport @UserStoryTSS-236 @TSS-2408

Feature: Preview Test Batch Report

  User needs to preview a Test Batch Report

  @TEST_TSS-2259 @Automation
  Scenario Outline: Preview Button
    Given The Review Page Is Active
    And The Test Batches list in the left side is collapsed
    When User selects the Test Batch "<TestBatch>" in left list
    And Verify the next icons by type
      | file-pdf |

    Examples:
      | TestBatch      |
      | HEp2-20200826B |
      | ANCA-20210916C |
      | HEp2-20191027D |

  @TEST_TSS-2260 @Automation
  Scenario Outline: Test Batch PDF Report Components
    Given The Review Page Is Active
    When User selects the Test Batch "<TestBatch>" in left list
    Then User clicks on Preview PDF
    And Verify that Preview PDF Window is opened
    Then Verify Close Panel Icon
    And Verify following icons in Preview PDF Window
      | printer  |
      | download |
      | minus    |
      | plus     |
    Then Verify Page Number is present in footer
    And Verify the title is "TestBatch-<TestBatch>-Report"

    Examples:
    | TestBatch      |
    | HEp2-20200826B |
    | ANCA-20210916C |
    | HEp2-20191027D |

  @TEST_TSS-2261 @Automation
  Scenario Outline: Disable backdrop on Test Batch PDF Report
    Given The Review Page Is Active
    And The Test Batches list in the left side is collapsed
    When User selects the Test Batch "<TestBatch>" in left list
    Then User clicks on Preview PDF
    And Verify that Preview PDF Window is opened
    Then Verify that the Backdrop is disabled
    When User clicks on Close Preview Icon
    Then Verify that Preview PDF Window is hidden

    Examples:
      | TestBatch      |
      | HEp2-20200826B |
      | ANCA-20210916C |
      | HEp2-20191027D |

  @TEST_TSS-2262 @Automation
  Scenario Outline: OS Print Dialog
    Given The Review Page Is Active
    When User selects the Test Batch "<TestBatch>" in left list
    Then User clicks on Preview PDF
    And Verify that Preview PDF Window is opened
    Then Verify the title is "TestBatch-<TestBatch>-Report"
    When User clicks on "printer" icon in Preview PDF Window
    Then Verify OS Print dialog

    Examples:
      | TestBatch      |
      | HEp2-20200826B |
      | ANCA-20210916C |
      | HEp2-20191027D |

  @TEST_TSS-2263 @Automation
  Scenario Outline: Download PDF Report
    Given The Review Page Is Active
    When User selects the Test Batch "<TestBatch>" in left list
    Then User clicks on Preview PDF
    And Verify that Preview PDF Window is opened
    Then Verify the title is "TestBatch-<TestBatch>-Report"
    When User clicks on "download" icon in Preview PDF Window
    Then Verify the downloaded file is "TestBatch-<TestBatch>-Report.pdf"

    Examples:
      | TestBatch      |
      | HEp2-20200826B |
      | ANCA-20210916C |
      | HEp2-20191027D |