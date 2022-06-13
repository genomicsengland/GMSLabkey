@RuleValidation
@DRVA_1223

Feature: participant_id of GMS tumour_topography must exist in participant table
  Scenario: participant_id of GMS tumour_topography must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from tumour_topography table from database
      | column     |
      | participant_id |
    Then the retrieved column of tumour_topography must exist in following participant table
      | column     |
      | participant_id |