@RuleValidation
@DRVA_1219

Feature: referral_id of GMS referral_test must exist in referral table
  Scenario: referral_id of GMS referral_test must exist in referral table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from referral_test table from database
      | column |
      | referral_id |
    Then the retrieved column of referral_test must exist in following referral table
      | column |
      | referral_id |