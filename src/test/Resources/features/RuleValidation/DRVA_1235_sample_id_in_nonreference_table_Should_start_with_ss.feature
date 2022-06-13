@RuleValidation
@DRVA_1235

Feature: sample_id in non-reference table should start with ss
  Scenario: sample_id in non-reference table should start with ss
    Given the user launches the Labkey application
    And the user retrieves zero count of following column from sample table from database
    |column|
    |sample_id|