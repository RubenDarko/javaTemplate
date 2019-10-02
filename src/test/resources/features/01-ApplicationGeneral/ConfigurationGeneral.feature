@Automation @ApplicationGeneral @ConfigurationGeneral @UserStoryTSS-251 @TSS-2433

Feature: Configuration General

  Admin needs to configure the general site and security properties for the application

  @TEST_TSS-2226 @Automation
  Scenario: System Configuration General components
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    And Verify the next messages in System Configuration
      | Configuration - General |
      | Company Info            |
      | Company Name            |
      | Location                |
      | Time Zone               |
      | Disk                    |
      | Model                   |
      | Capacity                |
      | Free Space              |
      | Preferences             |
      | Default Language        |
      | Password Security       |
      | Auto Sign Off           |

  @TEST_TSS-2227 @Automation
  Scenario Outline: Configuration General Inputs Validation
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    And Click on component "<component>" in System Screen
    Then Verify that the default value in "<component>" is empty
    And Verify "checkmark" button is present
    And Verify "close" button is present

    Examples:
      | component    |
      | Company Name |
      | Location     |

  @TEST_TSS-2228 @Automation
  Scenario Outline: Configuration General Validate Input Values
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    Then Clean up "<component>" field in System Screen
    When User enters "<string>" in "<component>"
    Then Click on "checkmark" button in System Screen
    And Verify the string size is "<expectedSize>" in "<component>" field
    Then Clean up "<component>" field in System Screen
    And Click on component "<component>" in System Screen
    And Verify that the default value in "<component>" is empty

    Examples:
      | component    | expectedSize | string                                                        |
      | Company Name | 10           | 0123456789                                                    |
      | Company Name | 50           | 01234567890123456789012345678901234567890123456789            |
      | Company Name | 50           | 0123456789012345678901234567890123456789012345678901234567890 |
      | Location     | 10           | 0123456789                                                    |
      | Location     | 50           | 01234567890123456789012345678901234567890123456789            |
      | Location     | 50           | 0123456789012345678901234567890123456789012345678901234567890 |

  @TEST_TSS-2231 @Automation
  Scenario: Configuration General Dropdowns Validation
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    Then Verify the values in following components
      | Component         | Value        |
      | Time Zone         | PST (UTC-8)  |
      | Default Language  | English      |
      | Password Security | 8 characters |
      | Auto Sign Off     | 10 minutes   |

  @TEST_TSS-2232 @Automation
  Scenario: Configuration General Default Language Dropdown Values
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    Then Verify the values in dropdown "Default Language"
      | English             |
      | French              |
      | German              |
      | Spanish             |
      | Italian             |
      | Portuguese (Brazil) |
      | Standard Mandarin   |

  @TEST_TSS-2233 @Automation
  Scenario: Configuration General Password Security Dropdown Values
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    Then Verify the values in dropdown "Password Security"
      | 4 Characters  |
      | 5 Characters  |
      | 6 Characters  |
      | 7 Characters  |
      | 8+ Characters |

  @TEST_TSS-2234 @Automation
  Scenario: Configuration General Auto Sign Off Values
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    Then Verify the values in dropdown "Auto Sign Off"
      | 5 Minutes  |
      | 10 Minutes |
      | 15 Minutes |
      | 30 Minutes |
      | 60 Minutes |

  @TEST_TSS-2235 @Automation
  Scenario: Configuration General Location Change Value
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    Then Clean up "Location" field in System Screen
    When User enters "Guadalajara" in "Location"
    Then Click on "checkmark" button in System Screen
    And Verify the values in following components
      | Component | Value       |
      | Location  | Guadalajara |
    Then Clean up "Location" field in System Screen
    And Click on component "Location" in System Screen
    And Verify that the default value in "Location" is empty

  @TEST_TSS-2236 @Automation
  Scenario: Configuration General Time Zone Dropdown Values
    Given The Home Page Is Active
    When User clicks on the Settings Icon
    Then Select "System Configuration" option in Dropdown Menu
    When User expands "Configuration" option in System Menu
    Then Select "General" option in System Menu
    Then Verify the values in dropdown "Time Zone"
      | ACDT (UTC+10:30)          |
      | ACST (UTC+09:30)          |
      | ACT (UTC−05)              |
      | ACT (UTC+06:30 – UTC+09)  |
      | ACWST (UTC+08:45)         |
      | ADT (UTC−03)              |
      | AEDT (UTC+11)             |
      | AEST (UTC+10)             |
      | AFT (UTC+04:30)           |
      | AKDT (UTC−08)             |
      | AKST (UTC−09)             |
      | ALMT (UTC+06)             |
      | AMST (UTC−03)             |
      | AMT (UTC−04)              |
      | AMT (UTC+04)              |
      | ANAT (UTC+12)             |
      | AQTT (UTC+05)             |
      | ART (UTC−03)              |
      | AST (UTC+03)              |
      | AST (UTC−04)              |
      | AWST (UTC+08)             |
      | AZOST (UTC±00)            |
      | AZOT (UTC−01)             |
      | AZT (UTC+04)              |
      | BDT (UTC+08)              |
      | BIOT (UTC+06)             |
      | BIT (UTC−12)              |
      | BOT (UTC−04)              |
      | BRST (UTC−02)             |
      | BRT (UTC−03)              |
      | BST (UTC+06)              |
      | BST (UTC+11)              |
      | BST (UTC+01)              |
      | BTT (UTC+06)              |
      | CAT (UTC+02)              |
      | CCT (UTC+06:30)           |
      | CDT (UTC−05)              |
      | CDT (UTC−04)              |
      | CEST (UTC+02)             |
      | CET (UTC+01)              |
      | CHADT (UTC+13:45)         |
      | CHAST (UTC+12:45)         |
      | CHOT (UTC+08)             |
      | CHOST (UTC+09)            |
      | CHST (UTC+10)             |
      | CHUT (UTC+10)             |
      | CIST (UTC−08)             |
      | CIT (UTC+08)              |
      | CKT (UTC−10)              |
      | CLST (UTC−03)             |
      | CLT (UTC−04)              |
      | COST (UTC−04)             |
      | COT (UTC−05)              |
      | CST (UTC−06)              |
      | CST (UTC+08)              |
      | CST (UTC−05)              |
      | CT (UTC+08)               |
      | CVT (UTC−01)              |
      | CWST (UTC+08:45)          |
      | CXT (UTC+07)              |
      | DAVT (UTC+07)             |
      | DDUT (UTC+10)             |
      | DFT (UTC+01)              |
      | EASST (UTC−05)            |
      | EAST (UTC−06)             |
      | EAT (UTC+03)              |
      | ECT (UTC−04)              |
      | ECT (UTC−05)              |
      | EDT (UTC−04)              |
      | EEST (UTC+03)             |
      | EET (UTC+02)              |
      | EGST (UTC±00)             |
      | EGT (UTC−01)              |
      | EIT (UTC+09)              |
      | EST (UTC−05)              |
      | FET (UTC+03)              |
      | FJT (UTC+12)              |
      | FKST (UTC−03)             |
      | FKT (UTC−04)              |
      | FNT (UTC−02)              |
      | GALT (UTC−06)             |
      | GAMT (UTC−09)             |
      | GET (UTC+04)              |
      | GFT (UTC−03)              |
      | GILT (UTC+12)             |
      | GIT (UTC−09)              |
      | GMT (UTC±00)              |
      | GST (UTC−02)              |
      | GST (UTC+04)              |
      | GYT (UTC−04)              |
      | HDT (UTC−09)              |
      | HAEC (UTC+02)             |
      | HST (UTC−10)              |
      | HKT (UTC+08)              |
      | HMT (UTC+05)              |
      | HOVST (UTC+08)            |
      | HOVT (UTC+07)             |
      | ICT (UTC+07)              |
      | IDLW (UTC−12)             |
      | IDT (UTC+03)              |
      | IOT (UTC+03)              |
      | IRDT (UTC+04:30)          |
      | IRKT (UTC+08)             |
      | IRST (UTC+03:30)          |
      | IST (UTC+05:30)           |
      | IST (UTC+01)              |
      | IST (UTC+02)              |
      | JST (UTC+09)              |
      | KALT (UTC+02)             |
      | KGT (UTC+06)              |
      | KOST (UTC+11)             |
      | KRAT (UTC+07)             |
      | KST (UTC+09)              |
      | LHST (UTC+10:30)          |
      | LHST (UTC+11)             |
      | LINT (UTC+14)             |
      | MAGT (UTC+12)             |
      | MART (UTC−09:30)          |
      | MAWT (UTC+05)             |
      | MDT (UTC−06)              |
      | MET (UTC+01)              |
      | MEST (UTC+02)             |
      | MHT (UTC+12)              |
      | MIST (UTC+11)             |
      | MIT (UTC−09:30)           |
      | MMT (UTC+06:30)           |
      | MSK (UTC+03)              |
      | MST (UTC+08)              |
      | MST (UTC−07)              |
      | MUT (UTC+04)              |
      | MVT (UTC+05)              |
      | MYT (UTC+08)              |
      | NCT (UTC+11)              |
      | NDT (UTC−02:30)           |
      | NFT (UTC+11)              |
      | NOVT (UTC+07)             |
      | NPT (UTC+05:45)           |
      | NST (UTC−03:30)           |
      | NT (UTC−03:30)            |
      | NUT (UTC−11)              |
      | NZDT (UTC+13)             |
      | NZST (UTC+12)             |
      | OMST (UTC+06)             |
      | ORAT (UTC+05)             |
      | PDT (UTC−07)              |
      | PET (UTC−05)              |
      | PETT (UTC+12)             |
      | PGT (UTC+10)              |
      | PHOT (UTC+13)             |
      | PHT (UTC+08)              |
      | PKT (UTC+05)              |
      | PMDT (UTC−02)             |
      | PMST (UTC−03)             |
      | PONT (UTC+11)             |
      | PST (UTC−08)              |
      | PST (UTC+08)              |
      | PYST (UTC−03)             |
      | PYT (UTC−04)              |
      | RET (UTC+04)              |
      | ROTT (UTC−03)             |
      | SAKT (UTC+11)             |
      | SAMT (UTC+04)             |
      | SAST (UTC+02)             |
      | SBT (UTC+11)              |
      | SCT (UTC+04)              |
      | SDT (UTC−10)              |
      | SGT (UTC+08)              |
      | SLST (UTC+05:30)          |
      | SRET (UTC+11)             |
      | SRT (UTC−03)              |
      | SST (UTC−11)              |
      | SST (UTC+08)              |
      | SYOT (UTC+03)             |
      | TAHT (UTC−10)             |
      | THA (UTC+07)              |
      | TFT (UTC+05)              |
      | TJT (UTC+05)              |
      | TKT (UTC+13)              |
      | TLT (UTC+09)              |
      | TMT (UTC+05)              |
      | TRT (UTC+03)              |
      | TOT (UTC+13)              |
      | TVT (UTC+12)              |
      | ULAST (UTC+09)            |
      | ULAT (UTC+08)             |
      | UTC (UTC±00)              |
      | UYST (UTC−02)             |
      | UYT (UTC−03)              |
      | UZT (UTC+05)              |
      | VET (UTC−04)              |
      | VLAT (UTC+10)             |
      | VOLT (UTC+04)             |
      | VOST (UTC+06)             |
      | VUT (UTC+11)              |
      | WAKT (UTC+12)             |
      | WAST (UTC+02)             |
      | WAT (UTC+01)              |
      | WEST (UTC+01)             |
      | WET (UTC±00)              |
      | WIT (UTC+07)              |
      | WST (UTC+08)              |
      | YAKT (UTC+09)             |
      | YEKT (UTC+05)             |