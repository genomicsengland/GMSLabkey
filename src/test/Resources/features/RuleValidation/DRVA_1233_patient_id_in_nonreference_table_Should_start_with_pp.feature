@RuleValidation

Feature: patient_id in non-reference table should start with pp
  Scenario: patient_id in non-reference table should start with pp
    Given the user launches the Labkey application
    And the user retrieves zero count of following column from patient table from database
    |column|
    |patient_id|