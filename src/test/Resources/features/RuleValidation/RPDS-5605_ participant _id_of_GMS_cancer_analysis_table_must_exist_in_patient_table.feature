@RuleValidation
@RPDS-5605

Feature: participant_id of GMS cancer analysis must exist in participant table
  Scenario: participant_id of GMS cancer analysis must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from cancer_analysis table from database
      | column     |
      | participant_id |
    Then the retrieved column of cancer_analysis must exist in following participant table
      | column     |
      | participant_id |