@RuleValidation
@DRVA_1218

Feature: participant_id of GMS referral_participant must exist in participant table
  Scenario: participant_id of GMS referral_participant must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from referral_participant table from database
      | column |
      | participant_id |
    Then the retrieved column of referral_participant must exist in following participant table
      | column |
      | participant_id |