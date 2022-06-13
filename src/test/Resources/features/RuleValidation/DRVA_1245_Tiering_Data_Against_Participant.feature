@RuleValidation
@DRVA_1245

  Feature: participant_id of tiering_data must exist in participant table
  Scenario: participant_id of tiering_data must exist in participant table
    Given the user launches the Labkey application
    Then user executes query by using tiering_data participant tables and Query result must be 0
      | column                  |
      | participant_id          |

  Scenario: referral_id of tiering_data must exist in referral table
    Given the user launches the Labkey application
    Then user executes query by using tiering_data referral tables and Query result must be 0
      | column                  |
      | referral_id |