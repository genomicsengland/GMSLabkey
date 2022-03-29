@RuleValidation

Feature: patient_id of GMS tumour_morphology must exist in patient table
  Scenario: patient_id of GMS tumour_morphology must exist in patient table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from tumour_morphology table from database
      | column     |
      | patient_id |
    Then the retrieved column of tumour_morphology must exist in following patient table
      | column     |
      | patient_id |