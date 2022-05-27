package co.uk.gel.proj.steps;


import co.uk.gel.proj.util.DBQueryExecuter;
import co.uk.gel.proj.util.Debugger;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import edu.emory.mathcs.backport.java.util.Arrays;
import gherkin.lexer.De;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DBStepDefs {

    static int patientCount = 0;
    ResultSet rs = null;
    ArrayList familyIdList = new ArrayList();

    @When("^the user retrieves the number of patients for (.*) from the database$")
    public void theUserRetrievesTheNumberOfPatientsForCancer_analysisFromTheDatabase(String table) {
        try {
            String query = DBQueries.getPatientCount(table);
            Debugger.println("Query : " + query);
            int count;
            ResultSet rs = DBQueryExecuter.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt("count");
                Debugger.println("Patient count for Table '" + table + "' = " + count);
                patientCount = count;
            }
        } catch (Exception e) {
            Debugger.println("Could not retrieve patient count for Table '" + table + "': exception " + e);
        }
    }

    @When("^the user retrieves the count of following column from (.*) table from database$")
    public void theUserRetrievesTheCountOfFollowingColumnFromMhldds_episodeTableFromDatabase(String table, DataTable inp_data) {
        try {
            String column = "";
            for (Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
                if (column.isEmpty() || column.equalsIgnoreCase("")) {
                    column = data.get("column");
                } else {
                    column = column + "," + data.get("column");
                }
            }
            String query = DBQueries.getCountofColumns(column, table);
            Debugger.println("Query : " + query);
            rs = DBQueryExecuter.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count");
                Debugger.println("Count for Table '" + table + "' = " + count);
                patientCount = count;
            }
        } catch (Exception e) {
            Debugger.println("Could not retrieve data : exception " + e);
        }
    }

    @Then("^the retrieved column of (.*) must exist in following (.*) table$")
    public void theRetrievedColumnOfMhldds_episodeMustExistInForPatientTable(String table1, String table2, DataTable inp_data) {
        boolean test_result = false;
        try {
            String column = "";
            for (Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
                if (column.isEmpty() || column.equalsIgnoreCase("")) {
                    column = data.get("column");
                } else {
                    column = column + "," + data.get("column");
                }
            }
            String query = DBQueries.getCountofDesiredCoumns(table1, table2, column);
            Debugger.println("Query : " + query);
            rs = DBQueryExecuter.executeQuery(query);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("count");
                Debugger.println("selected column count = " + count);
            }
            if (count != patientCount) {
                Debugger.println("All existing count is " + patientCount + ", Desired count " + count);
                test_result = true;
            }
            Assert.assertFalse("All existing count is " + patientCount + ", Desired count " + count, test_result);

        } catch (Exception e) {
            Debugger.println("Could not retrieve data : exception " + e);
            Assert.assertFalse("Could not retrieve data : exception "+e, true);
        }

    }
    @Then("the retrieved column of GMS condition must exist in following patient table")
    public void theRetrievedColumnOfGMSConditionMustExistInFollowingPatientTable() {
    }

    @And("^the user retrieves zero count of following column from (.*)table from database$")
    public void theUserRetrievesZeroCountOfFollowingColumnFromPatientTableFromDatabase(String table, DataTable inp_data) {
        boolean testResult = false;
        int count =0;
        try{
            String column="";
            for(Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
                if (column.isEmpty()||column.equalsIgnoreCase("")){
                    column = data.get("column");
                } else {
                    column = column + "," + data.get("column");
                }
            }
            String query = DBQueries.getInstanceCount(column, table);
            Debugger.println("Query : "+query);
            rs = DBQueryExecuter.executeQuery(query);
            if(rs.next()){
                count = rs.getInt("count");
            }
            Debugger.println("Counts of instances :"+count);
            if(count==0){
                testResult = true;
            }
            Assert.assertTrue("Expected value : 0 Actual value " + count, testResult);
        }catch (Exception exp){
            Debugger.println("Could not retrieve data : exception "+ exp);
            Assert.assertFalse("Could not retrieve data : exception "+exp, true);
        }
    }

    @Then("user executes query by using (.*) (.*) tables and Query result must be 0")
    public void userExecutesQueryByUsingPanels_appliedParticipantTablesAndQueryResultMustBe(String table1, String table2, DataTable inp_data) {
        boolean test_result = false;
        try {
            String column = "";
            for (Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
                if (column.isEmpty() || column.equalsIgnoreCase("")) {
                    column = data.get("column");
                } else {
                    column = column + "," + data.get("column");
                }
            }
            String query = DBQueries.getCountOfTableColumns(table1, table2, column);
            Debugger.println("Query : " + query);
            rs = DBQueryExecuter.executeQuery(query);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("count");
                Debugger.println("selected column count = " + count);
            }
            if (count > 0) {
                Debugger.println("Query result must be 0 but count is " + count);
                test_result = true;
            }
            Assert.assertFalse("Query result must be 0 but count is " + count, test_result);

        } catch (Exception e) {
            Debugger.println("Could not retrieve data : exception " + e);
            Assert.assertFalse("Could not retrieve data : exception ", true);
        }
    }

    @When("^the user retrieves the data of following column from (.*) table from database$")
    public void theUserRetrievesTheDataOfFollowingColumnFromExomiserTableFromDatabase(String table, DataTable inp_data) {
        try {
            String column = "";
            for (Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
                if (column.isEmpty() || column.equalsIgnoreCase("")) {
                    column = data.get("column");
                } else {
                    column = column + "," + data.get("column");
                }
            }
            String query = DBQueries.getUniqueCombinationCount(column, table);
            Debugger.println("Query : " + query);
            rs = DBQueryExecuter.executeQuery(query);
            String family_id;
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count");
                Debugger.println("Patient count for Table '" + table + "' = " + count);
                patientCount = count;
            }
        } catch (Exception e) {
            Debugger.println("Could not retrieve data : exception " + e);
        }
    }

    @Then("^the getting columns of (.*) table must exist in following column for (.*) table in database$")
    public void theGettingColumnsOfGenome_file_paths_and_typesTableMustExistInFollowingColumnForSequencing_reportTableInDatabase(String table1, String table2, DataTable inp_data) {
        boolean test_result = false;
        try {
            String column = "";
            for (Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
                if (column.isEmpty() || column.equalsIgnoreCase("")) {
                    column = data.get("column");
                } else {
                    column = column + "," + data.get("column");
                }
            }
            String query = DBQueries.getCountOfparticipantlabSampleIdPlatekey(table1, table2);
            Debugger.println("Query : " + query);
            rs = DBQueryExecuter.executeQuery(query);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("count");
                Debugger.println("selected column count = " + count);
            }
            if (count != patientCount) {
                Debugger.println("All participant count is " + patientCount + ", unique count " + count);
                test_result = true;
            }
            Assert.assertFalse("All participant count is " + patientCount + ", unique count " + count, test_result);

        } catch (Exception e) {
            Debugger.println("Could not retrieve data : exception " + e);
            Assert.assertFalse("Could not retrieve data : exception ", true);
        }
    }

}
