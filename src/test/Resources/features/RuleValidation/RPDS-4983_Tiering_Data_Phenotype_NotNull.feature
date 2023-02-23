@RuleValidation
@RPDS-4983

  Feature: phenotype of tiering_data must not be null or NA
  Scenario: phenotype of tiering_data must not be null or NA
    Given the user launches the Labkey application
    Then user executes query by tiering_data tables and Query result must be zero
      | column            |
      | phenotype         |
