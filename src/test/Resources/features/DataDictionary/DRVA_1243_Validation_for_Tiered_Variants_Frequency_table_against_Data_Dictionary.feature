@DataDictionary

@DRVA_1243
Feature: Validation Performed for Tiered Variants Frequency table against the Data Dictionary
  Scenario Outline: Validation Performed for Field in Tiered Variants Frequency table against the Data Dictionary
    Given the user launches the Labkey application
    And the user is logged into Labkey
    When the user navigates to nhs-gms project
    And the user navigates to current version project of gms
    And the user clicks on <labKey_table> table
    And the user retrieves the field from data dictionary for <data_dictionary_table> table
    Then the field of <labKey_table> table in LabKey should same as retrieved from Data Dictionary

    Examples:
      | data_dictionary_table     | labKey_table              |
      | Tiered_Variants_frequency | tiered_variants_frequency |

  Scenario Outline: Validation Performed for Participant count in Tiered Variants Frequency table against the LabKey Database
    Given the user launches the Labkey application
    And the user is logged into Labkey
    When the user navigates to gms project
    And the user navigates to current version project of gms
    And the user clicks on <labKey_table> table
    When the user retrieves the number of participants for <labKey_table> from the database
    Then the participant count on the dashboard for <labKey_table> table is same as the retrieved value

    Examples:
      | labKey_table              |
      | tiered_variants_frequency |

  Scenario Outline: Validation Performed for Data Type in Tiered Variants Frequency table against the LabKey Database
    Given the user launches the Labkey application
    When the user retrieves the data type from data dictionary for <data_dictionary_table> table
    Then the data type of <labKey_table> table in LabKey should same as retrieved from Data Dictionary

    Examples:
      | data_dictionary_table     | labKey_table              |
      | Tiered_Variants_frequency | tiered_variants_frequency |
