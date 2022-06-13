@RuleValidation
@DRVA_1233

Feature: participant_id in non-reference table should start with pp
  Scenario: participant_id in non-reference table should start with pp
    Given the user launches the Labkey application
    And the user retrieves zero count of following column from participant table from database
    |column|
    |participant_id|