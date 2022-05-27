@RuleValidation

@DRVA_1238
Feature: patient_id of gmc_exit_questionnaire must exist in patient table
  Scenario: participant_id of gmc_exit_questionnaire must exist in patient table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from gmc_exit_questionnaire table from database
      | column     |
      | patient_id |
    Then the retrieved column of gmc_exit_questionnaire must exist in following patient table
      | column     |
      | patient_id |