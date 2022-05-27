@RuleValidation

Feature: sample_id of plated sample must exist in sample table
  Scenario: sample_id of plated sample must exist in sample table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from sample table from database
      | column     |
      | sample_id |
    Then the retrieved column of plated_sample must exist in following sample table
      | column     |
      | sample_id |