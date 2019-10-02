@Automation @TestSetup @SignatureChecksumVerification @UserStoryTSS-218 @TSS-2267

Feature: Signature Checksum Verification

  User needs to verify a signature is still valid after an update.

  @TEST_TSS-2268 @Automation
  Scenario: Verify Signature is valid for test_batch_aud after an update
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    #Then Verify signature field is valid in test_batch_aud DB table
    And a Slide "123456" is selected
    #Then Verify signature field is valid in test_batch_aud DB table
    And Clean up Test Batches

  @TEST_TSS-2269 @Automation
  Scenario: Verify Signature is valid for slide_aud after an update
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    #Then Verify signature field is valid in slide_aud DB table
    And set Positive Control in well
      | well  |
      | 1     |
    #Then Verify signature field is valid in slide_aud DB table
    And Clean up Test Batches

  @TEST_TSS-2270 @Automation
  Scenario: Verify Signature is valid for well_sample_aud after an update
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    Then a Slide "123456" is selected
    And set Positive Control in well
      | well  |
      | 1     |
    #Then Verify signature field is valid in well_sample_aud DB table
    And set Negative Control in well
      | well  |
      | 1     |
    #Then Verify signature field is valid in well_sample_aud DB table
    And Clean up Test Batches