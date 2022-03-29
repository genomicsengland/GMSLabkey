@RuleValidation

Feature: patient_id of GMS plated_sample must exist in patient table
  Scenario: patient_id of GMS plated_sample must exist in patient table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from plated_sample table from database
      | column     |
      | patient_id |
    Then the retrieved column of plated_sample must exist in following patient table
      | column     |
      | patient_id |