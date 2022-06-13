@RuleValidation
@DRVA_1212

Feature: participant_id of GMS condition must exist in participant table
  Scenario: participant_id of GMS condition must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from condition table from database
      | column     |
      | participant_id |
    Then the retrieved column of condition must exist in following participant table
      | column     |
      | participant_id |