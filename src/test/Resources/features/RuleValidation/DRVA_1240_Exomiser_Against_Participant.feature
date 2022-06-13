@RuleValidation
@DRVA_1240

Feature: participant_id of exomiser must not exist in participant table

  Scenario: participant_id of exomiser must not exist in participant table
    Given the user launches the Labkey application
    Then user executes query by using exomiser participant tables and Query result must be 0
      | column    |
      | participant_id |

  Scenario: family_id of exomiser must not exist in participant table
    Given the user launches the Labkey application
    Then user executes query by using exomiser referral tables and Query result must be 0
      | column    |
      | referral_id |
#    referral as replacement of family id