@RuleValidation

@DRVA_1241

Feature: patient_id of panels_applied must exist in patient table

  Scenario: patient_id of panels_applied must exist in patient table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from panels_applied table from database
      | column         |
      | patient_id |
    Then the retrieved column of panels_applied must exist in following patient table
      | column         |
      | patient_id |

    Scenario: rare_diseases_family_id of panels_applied must exist in patient table
    Given the user launches the Labkey application
    Then user executes query by using panels_applied patient tables and Query result must be 0
      | column                  |
      | rare_diseases_family_id |

#########################################################################################
# "all tables except:
#tiering_data
#panels_applied
#domain_assignment
#aggregated_gvcf_sample_stats
#exomiser"
###########################################################################################
