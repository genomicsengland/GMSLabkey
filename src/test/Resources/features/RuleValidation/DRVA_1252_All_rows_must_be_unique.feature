@RuleValidation
@DRVA_1252

Feature: all rows must be unique
  Scenario Outline: all rows must be unique
    Given the user launches the Labkey application
    When the user retrieves the count of all columns <table>  from the database
    And the user retrieves the count of desired columns of <table> from the database must be unique
    Examples:
      | table                                |
#      | condition                            |
#      | tumour_morphology                    |
#      | tumour_topography                    |
#      | observation_component                |
#      | referral_test                        |
#      | referral                             |
#      | tumour                               |
#      | observation_component                |
#      | gmc_exit_questionnaire               |
#      | exomiser                             |
#      | panels_applied                       |
#      | tiering_data                         |
#      | genome_file_paths_and_types          |
#      | sequencing_report                    |
#      | participant                          |
      | cancer_analysis                      |
