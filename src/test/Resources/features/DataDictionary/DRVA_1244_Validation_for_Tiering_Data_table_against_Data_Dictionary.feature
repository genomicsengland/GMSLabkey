@DataDictionary_1

@DRVA_1244
Feature: Validation Performed for Tiering Data table against the Data Dictionary for v10
  Scenario Outline: Validation Performed for Field in Tiering Data table against the Data Dictionary for vv109
    Given the user launches the Labkey application
    And the user is logged into Labkey
    When the user navigates to gms project
    And the user navigates to current version project of gms
    And the user clicks on <labKey_table> table
    And the user retrieves the field from data dictionary for <data_dictionary_table> table
    Then the field of <labKey_table> table in LabKey should same as retrieved from Data Dictionary

    Examples:
      | data_dictionary_table | labKey_table |
      | Tiering_data          | tiering_data |

  Scenario Outline: Validation Performed for Patient count in Tiering Data table against the LabKey Database
    Given the user launches the Labkey application
    And the user is logged into Labkey
    When the user navigates to gms project
    And the user navigates to current version project of gms
    And the user clicks on <labKey_table> table
    When the user retrieves the number of patients for <labKey_table> from the database
    Then the patient count on the dashboard for <labKey_table> table is same as the retrieved value

    Examples:
      | labKey_table |
      | tiering_data |

  Scenario Outline: Validation Performed for Data Type in Tiering Data table against the LabKey Database
    Given the user launches the Labkey application
    When the user retrieves the data type from data dictionary for <data_dictionary_table> table
    Then the data type of <labKey_table> table in LabKey should same as retrieved from Data Dictionary

    Examples:
      | data_dictionary_table | labKey_table |
      | Tiering_data          | tiering_data |