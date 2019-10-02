@Automation @TestSetup @VerifyAuditTablesData @UserStoryTSS-116 @TSS-2275

Feature: Verify Audit Tables Data

  Verify an audit record is inserted every time a record is inserted/updated/deleted for some tables.

  @TEST_TSS-2276 @Automation
  Scenario: Verify that Table test_batch_aud is being updated correctly
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    #Then Verify that fields were created in test_batch_aud DB table
      #| Field  			   |
      #| created_by		   |
      #| created_date       |
      #| last_modified_by   |
      #| last_modified_date |
      #| signature		   |
    And a Slide "123456" is selected
    #Then Verify that fields are valid in test_batch_aud DB table
      #| Field  			   |
      #| last_modified_date |
      #| signature		   |

  @TEST_TSS-2277 @Automation
  Scenario: Verify that Table slide_aud is being updated correctly
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    #Then Verify that fields were created in slide_aud DB table
      #| Field  			   |
      #| created_by		   |
      #| created_date       |
      #| last_modified_by   |
      #| last_modified_date |
      #| signature		   |
    And set Positive Control in well
      | well  |
      | 1     |
    #Then Verify that fields are valid in slide_aud DB table
      #| Field  			   |
      #| last_modified_date |
      #| signature		   |

  @TEST_TSS-2278 @Automation
  Scenario: Verify that Table well_sample_aud is being updated correctly
    Given The Test Setup Page Is Active
    Then Clean up Test Batches
    When User Triggers A "00011162695227B220505095968756" Scan
    And a Slide "123456" is selected
    And set Positive Control in well
      | well  |
      | 1     |
    #Then Verify that fields were created in well_sample_aud DB table
      #| Field  			   |
      #| created_by		   |
      #| created_date       |
      #| last_modified_by   |
      #| last_modified_date |
      #| signature		   |
    And set Negative Control in well
      | well  |
      | 1     |
    #Then Verify that fields are valid in well_sample_aud DB table
      #| Field  			   |
      #| last_modified_date |
      #| signature		   |