@RuleValidation

Feature: referral_id in non-reference table should start with rr
  Scenario: referral_id in non-reference table should start with rr
    Given the user launches the Labkey application
    And the user retrieves zero count of following column from referral table from database
    |column|
    |referral_id|