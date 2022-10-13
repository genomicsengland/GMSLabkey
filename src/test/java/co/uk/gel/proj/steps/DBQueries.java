package co.uk.gel.proj.steps;

import co.uk.gel.proj.util.Debugger;

public class DBQueries {
    private static String query = "";

    private static String conditionTable = "list.c246d8286_condition";
    private static String tumourMorphologyTable = "list.c246d8296_tumour_morphology";
    private static String tumourTopographyTable = "list.c246d8297_tumour_topography";
    private static String observationComponentTable = "list.c246d8288_observation_component";
    private static String observationTable = "list.c246d8287_observation";
    private static String participantTable = "list.c246d8289_participant";
    private static String referralParticipantTable = "list.c246d8292_referral_participant";
    private static String referralTestTable = "list.c246d8293_referral_test";
    private static String referralTable = "list.c246d8291_referral";
    private static String tumourTable = "list.c246d8295_tumour";
    private static String platedSampleTable = "list.c246d8290_plated_sample";
    private static String sampleTable = "list.c246d8294_sample";
    private static String exomiserTable = "list.c213d8115_exomiser";
    private static String genomeFilePathAndTypesTable = "list.c213d8116_genome_file_paths_and_types";
    private static String gmcExitQuestionnaireTable = "list.c213d8117_gmc_exit_questionnaire";
    private static String panelsAppliedTable = "list.c213d8118_panels_applied";
    private static String sequencingReportTable = "list.c213d8119_sequencing_report";
    private static String tieredVariantsFrequencyTable= "list.c213d8121_tiered_variants_frequency";
    private static String tieringDataTable = "list.c213d8122_tiering_data";


    public static String tableNameMatches(String table) {
        Debugger.println("searching for " + table);
        table=table.trim();
        if (table.equalsIgnoreCase("condition")) {
            table = conditionTable;
        } else if (table.equalsIgnoreCase("tumour_morphology")) {
            table = tumourMorphologyTable;
        } else if (table.equalsIgnoreCase("tumour_topography")) {
            table = tumourTopographyTable;
        } else if (table.equalsIgnoreCase("observation_component")) {
            table = observationComponentTable;
        } else if (table.equalsIgnoreCase("observation")) {
            table = observationTable;
        } else if (table.equalsIgnoreCase("participant")) {
            table = participantTable;
        } else if (table.equalsIgnoreCase("referral_participant")) {
            table = referralParticipantTable;
        } else if (table.equalsIgnoreCase("referral_test")) {
            table = referralTestTable;
        } else if (table.equalsIgnoreCase("referral")) {
            table = referralTable;
        }  else if (table.equalsIgnoreCase("tumour")) {
            table = tumourTable;
        } else if (table.equalsIgnoreCase("exomiser")) {
            table = exomiserTable;
        }  else if (table.equalsIgnoreCase("genome_file_paths_and_types")) {
            table = genomeFilePathAndTypesTable;
        } else if (table.equalsIgnoreCase("gmc_exit_questionnaire")) {
            table = gmcExitQuestionnaireTable;
        }  else if (table.equalsIgnoreCase("panels_applied")) {
            table = panelsAppliedTable;
        }  else if (table.equalsIgnoreCase("sequencing_report")) {
            table = sequencingReportTable;
        } else if (table.equalsIgnoreCase("tiered_variants_frequency")) {
            table = tieredVariantsFrequencyTable;
        }  else if (table.equalsIgnoreCase("tiering_data")) {
            table = tieringDataTable;
        } else if (table.equalsIgnoreCase("plated_sample")) {
            table = platedSampleTable;
        }  else if (table.equalsIgnoreCase("sample")) {
            table = sampleTable;
        }
        return table;
    }

    public static String getCountofColumns(String column, String table) {
        Debugger.println("table name = " + table + "; and column name = " + column);
        String tableName = tableNameMatches(table);
        query = "select count(distinct(" + column + "))\n" +
                "from " + tableName;
        return query;
    }

    public static String getCountofDesiredCoumns(String table1, String table2, String column) {
        Debugger.println("table1 name = " + table1 + "; and column name = " + column);
        Debugger.println("table2 name = " + table2 + "; and column name = " + column);
        String tableName1 = tableNameMatches(table1);
        String tableName2 = tableNameMatches(table2);

        query = "select count(distinct(rdf." + column + "))\n" +
                "from " + tableName1 + " pa inner join " + tableName2 + " rdf on " +
                "rdf." + column + "=pa." + column;
        return query;
    }

    public static String getInstanceCount(String column, String table){
        String tableName = tableNameMatches(table);
        query ="select count("+column+")\n" +
                "from "+tableName+" \n" +
                "where "+column+" not like 'pp%'\n" +
                "and "+column+" not like 'rr%'\n" +
                "and "+column+" not like 'ss%'";
        return query;
    }

    public static String getParticipantCount(String table) {
        String tableName = tableNameMatches(table);
        Debugger.println("table Name = " + tableName);
        query = "select count(*) as count from " + tableName;
        return query;
    }

    public static String getCountOfTableColumns(String table1, String table2, String column) {
        Debugger.println("table1 name = " + table1 + "; and column name = " + column);
        Debugger.println("table2 name = " + table2 + "; and column name = " + column);
        String tableName1 = tableNameMatches(table1);
        String tableName2 = tableNameMatches(table2);

        //referral_id  and participant_id comparison for tables**
        query = "select count(*) from " +tableName1 +"\n"+
                 "where " +column + " not in "+
                "(select " +column + " from " + tableName2 + ")";
        return query;
    }

    public static String getUniqueCombinationCount(String column, String table) {
        Debugger.println("table name = " + table + "; and column name = " + column);
        String tableName = tableNameMatches(table);
        query = "select count(distinct (" + column + "))\n" +
                "from " + tableName;
        return query;
    }

    public static String getCountOfparticipantlabSampleIdPlatekey(String table1, String table2) {
        String tableName1 = tableNameMatches(table1);
        String tableName2 = tableNameMatches(table2);
        query = "select count(distinct (gfpt.participant_id,gfpt.platekey,gfpt.delivery_id))\n" +
                "from \n" +
                tableName1 + " gfpt\n" +
                "inner join\n" +
                tableName2 + " sr\n" +
                "on gfpt.participant_id = sr.participant_id\n" +
                "and gfpt.platekey = sr.platekey\n" +
                "and gfpt.delivery_id = sr.delivery_id\n" ;

        Debugger.println(query);
        return query;
    }

    public static String getQuery(String table) {
        String tableN = tableNameMatches(table);
        Debugger.println("table name = " + tableN);
        query = "select *\n" +
                "from " + tableN + " \n" +
                "where false;";
        return query;
    }

    public static String getCountOfAllColumnsFromATable(String listString, String table) {
        String tableName = tableNameMatches(table);
        Debugger.println("table Name = " + tableName);
        query = "select count (distinct (" + listString + ")) from " + tableName;
        return query;
    }

}//end
