@RuleValidation
@DRVA_1221

Feature: participant_id of GMS tumour must exist in participant table
  Scenario: participant_id of GMS tumour must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from tumour table from database
      | column     |
      | participant_id |
    Then the retrieved column of tumour must exist in following participant table
      | column     |
      | participant_id |