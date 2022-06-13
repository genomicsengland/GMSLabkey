@RuleValidation
@DRVA_1248

Feature: participant_id of genome_file_paths_and_types must exist in participant table
  Scenario: participant_id of genome_file_paths_and_types must exist in participant table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from genome_file_paths_and_types table from database
      | column         |
      | participant_id |
    Then the retrieved column of genome_file_paths_and_types must exist in following participant table
      | column         |
      | participant_id |