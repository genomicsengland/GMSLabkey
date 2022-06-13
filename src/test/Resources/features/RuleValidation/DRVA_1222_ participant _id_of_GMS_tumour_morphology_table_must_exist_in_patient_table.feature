@RuleValidation
  @DRVA_1222

Feature: participant_id of GMS tumour_morphology must exist in participant table
  Scenario: participant_id of GMS tumour_morphology must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from tumour_morphology table from database
      | column     |
      | participant_id |
    Then the retrieved column of tumour_morphology must exist in following participant table
      | column     |
      | participant_id |