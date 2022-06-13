@RuleValidation
@DRVA_1238

Feature: participant_id of gmc_exit_questionnaire must exist in participant table
  Scenario: participant_id of gmc_exit_questionnaire must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from gmc_exit_questionnaire table from database
      | column     |
      | participant_id |
    Then the retrieved column of gmc_exit_questionnaire must exist in following participant table
      | column     |
      | participant_id |