@RuleValidation

@DRVA_1249
Feature: Combination of patient_id + lab_sample_id + platekey + delivery_id must exist in sequencing_report matching genome_file_paths_and_types
  Scenario: Combination of patient_id + lab_sample_id + platekey + delivery_id must exist in sequencing_report matching genome_file_paths_and_types
    Given the user launches the Labkey application
    When the user retrieves the data of following column from genome_file_paths_and_types table from database
      | column         |
      | patient_id |
      | lab_sample_id  |
      | platekey       |
      | delivery_id    |
    Then the getting columns of genome_file_paths_and_types table must exist in following column for sequencing_report table in database
      | column         |
      | patient_id |
      | lab_sample_id  |
      | platekey       |
      | delivery_id    |
