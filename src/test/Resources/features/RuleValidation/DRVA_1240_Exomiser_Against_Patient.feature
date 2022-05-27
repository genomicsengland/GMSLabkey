@RuleValidation

@DRVA_1240
Feature: patient_id of exomiser must not exist in patient table

  Scenario: patient_id of exomiser must not exist in patient table
    Given the user launches the Labkey application
    Then user executes query by using exomiser patient tables and Query result must be 0
      | column    |
      | patient_id |

  Scenario: family_id of exomiser must not exist in patient table
    Given the user launches the Labkey application
    Then user executes query by using exomiser patient tables and Query result must be 0
      | column    |
      | referral |
#    referral as replacement of family id