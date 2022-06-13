@RuleValidation
@DRVA_1241

Feature: participant_id of panels_applied must exist in participant table

  Scenario: participant_id of panels_applied must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from panels_applied table from database
      | column         |
      | participant_id |
    Then the retrieved column of panels_applied must exist in following participant table
      | column         |
      | participant_id |

    Scenario: referral_id of panels_applied must exist in participant table
    Given the user launches the Labkey application
    Then user executes query by using panels_applied participant tables and Query result must be 0
      | column                  |
      | referral_id |

#########################################################################################
# "all tables except:
#tiering_data
#panels_applied
#domain_assignment
#aggregated_gvcf_sample_stats
#exomiser"
###########################################################################################
