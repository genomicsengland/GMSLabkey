@RuleValidation

@DRVA_1245

  Feature: patient_id of tiering_data must exist in patient table
  Scenario: patient_id of tiering_data must exist in patient table
    Given the user launches the Labkey application
    Then user executes query by using tiering_data patient tables and Query result must be 0
      | column                  |
      | patient_id          |

  Scenario: rare_diseases_family_id of tiering_data must exist in patient table
    Given the user launches the Labkey application
    Then user executes query by using tiering_data patient tables and Query result must be 0
      | column                  |
      | rare_diseases_family_id |