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


}//end
