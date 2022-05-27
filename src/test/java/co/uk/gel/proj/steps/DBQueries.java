package co.uk.gel.proj.steps;

import co.uk.gel.proj.util.Debugger;

public class DBQueries {
    private static String query = "";

    private static String cancerAnalysisTable = "list.c205d7634_cancer_analysis";
    private static String rareDiseaseAnalysisTable = "list.c205d7640_rare_disease_analysis";

    public static String tableNameMatches(String table) {
        Debugger.println("searching for " + table);
        if (table.equalsIgnoreCase("Cancer_analysis")) {
            table = cancerAnalysisTable;
        } else if (table.equalsIgnoreCase("rareDiseaseAnalysisTable")) {
            table = rareDiseaseAnalysisTable;
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

    public static String getPatientCount(String table) {
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

        //rare_diseases_family_id  and participant_id comparison for tables**
        query = "select count(*) from " +tableName1 +"\n"+
                "where cast(" +column + " as integer) not in (select cast(" +column + " as integer)  from " + tableName2 + "\n" +
                "where " +column + " is not null) and " +column + " not in "+
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
        query = "select count(distinct (gfpt.participant_id,gfpt.lab_sample_id,gfpt.platekey,gfpt.delivery_id))\n" +
                "from \n" +
                tableName1 + " gfpt\n" +
                "inner join\n" +
                tableName2 + " sr\n" +
                "on gfpt.participant_id = sr.participant_id\n" +
                "and gfpt.platekey = sr.plate_key\n" +
                "and gfpt.delivery_id = sr.delivery_id\n" +
                "and gfpt.lab_sample_id = sr.lab_sample_id";

        Debugger.println(query);
        return query;
    }

}//end
