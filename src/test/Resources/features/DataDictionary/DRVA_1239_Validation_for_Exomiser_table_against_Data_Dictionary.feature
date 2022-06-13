@DataDictionary

@DRVA_1239
Feature: Validation Performed for Exomiser table against the Data Dictionary

  Scenario Outline: Validation Performed for Field in Exomiser table against the Data Dictionary
    Given the user launches the Labkey application
    And the user is logged into Labkey
    When the user navigates to gms project
    And the user navigates to current version project of gms
    And the user clicks on <labKey_table> table
    And the user retrieves the field from data dictionary for <data_dictionary_table> table
    Then the field of <labKey_table> table in LabKey should same as retrieved from Data Dictionary

    Examples:
      | data_dictionary_table | labKey_table |
      | Exomiser              | exomiser     |

  Scenario Outline: Validation Performed for participant count in Exomiser table against the LabKey Database
    Given the user launches the Labkey application
    And the user is logged into Labkey
    When the user navigates to gms project
    And the user navigates to current version project of gms
    And the user clicks on <labKey_table> table
    When the user retrieves the number of participants for <labKey_table> from the database
    Then the participant count on the dashboard for <labKey_table> table is same as the retrieved value

    Examples:
      | labKey_table |
      | exomiser     |

  Scenario Outline: Validation Performed for Data Type in Exomiser table against the LabKey Database
    Given the user launches the Labkey application
    When the user retrieves the data type from data dictionary for <data_dictionary_table> table
    Then the data type of <labKey_table> table in LabKey should same as retrieved from Data Dictionary

    Examples:
      | data_dictionary_table | labKey_table |
      | Exomiser              | exomiser     |