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

    static int participantCount = 0;
    ResultSet rs = null;
    ArrayList familyIdList = new ArrayList();

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
                participantCount = count;
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
            if (count != participantCount) {
                Debugger.println("All existing count is " + participantCount + ", Desired count " + count);
                test_result = true;
            }
            Assert.assertFalse("All existing count is " + participantCount + ", Desired count " + count, test_result);

        } catch (Exception e) {
            Debugger.println("Could not retrieve data : exception " + e);
            Assert.assertFalse("Could not retrieve data : exception "+e, true);
        }

    }
    @Then("the retrieved column of GMS condition must exist in following patient table")
    public void theRetrievedColumnOfGMSConditionMustExistInFollowingPatientTable() {
    }
}
