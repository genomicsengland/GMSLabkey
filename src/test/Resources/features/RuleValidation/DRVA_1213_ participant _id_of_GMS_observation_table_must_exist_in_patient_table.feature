@RuleValidation
@DRVA_1213

Feature: participant_id of GMS observation must exist in participant table
  Scenario: participant_id of GMS observation must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from observation table from database
      | column     |
      | participant_id |
    Then the retrieved column of observation must exist in following participant table
      | column     |
      | participant_id |