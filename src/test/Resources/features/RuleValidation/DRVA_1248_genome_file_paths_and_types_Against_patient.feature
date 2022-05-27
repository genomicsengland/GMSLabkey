@RuleValidation

@DRVA_1248
Feature: patient_id of genome_file_paths_and_types must exist in patient table
  Scenario: patient_id of genome_file_paths_and_types must exist in patient table
    Given the user launches the Labkey application
    When the user retrieves the count of following column from genome_file_paths_and_types table from database
      | column         |
      | patient_id |
    Then the retrieved column of genome_file_paths_and_types must exist in following patient table
      | column         |
      | patient_id |