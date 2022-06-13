@RuleValidation
@DRVA_1216

Feature: participant_id of GMS plated_sample must exist in participant table
  Scenario: participant_id of GMS plated_sample must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from plated_sample table from database
      | column     |
      | participant_id |
    Then the retrieved column of plated_sample must exist in following participant table
      | column     |
      | participant_id |