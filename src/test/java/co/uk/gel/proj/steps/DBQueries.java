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

}//end
