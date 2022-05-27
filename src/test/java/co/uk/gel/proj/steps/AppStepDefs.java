package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.Report;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AppStepDefs extends Pages {

    public AppStepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @Given("^application is up and running$")
    public void applicationIsUpAndRunning() {
        Debugger.println("Application is Up and Running.");
    }

    @When("^the user login to the application with valid credentials$")
    public void theUserLoginToTheApplicationWithValidCredentials() {
        Debugger.println("User login to application with valid credentials.");
    }

    @Then("^the user should be be navigated to the application home page$")
    public void theUserShouldBeBeNavigatedToTheApplicationHomePage() {
        appHomePage.testMethod();
    }

    @Given("^the user launches the Labkey application$")
    public void theUserLaunchesTheLabkeyApplication() {
        appHomePage.navigatesToLabkey();
    }

    @And("^the user is logged into Labkey$")
    public void theUserIsLoggedIntoLabkey() {
        labKeyPage.loginToLabkey();
    }

    @When("^the user navigates to (.*) project$")
    public void theUserNavigatesToMainProgrammeProject(String project) {
        homePage.navigatesToProject(project);
    }

    @And("^the user navigates to version (.*) project of main programme$")
    public void theUserNavigatesToVersionProjectOfMainProgramme(String version) {
        gms.navigatesToVersion(version);
    }

    @And("^the user navigates to current version project of gms$")
    public void theUserNavigatesToCurrentVersionProjectOfGms() {
        boolean testResult = false;
       String vMessage =  gms.navigatesToVersion();
       if (!vMessage.equalsIgnoreCase("Success")){
           Debugger.println(vMessage);
           testResult = false;
       }else {
           testResult = true;
       }

       Assert.assertTrue(vMessage,testResult);
    }

    @And("^the user clicks on (.*) table$")
    public void theUserClicksOnCancer_analysisTable(String table) {
        gms.clickOnTable(table);
    }

    @And("^the user retrieves the field from data dictionary for (.*) table$")
    public void theUserRetrievesTheColumnNameFromDataDictionaryForCancerAnalysisTable(String table) {
        Debugger.println("theUserRetrievesTheColumnNameFromDataDictionaryForCancerAnalysisTable"+table);
        dataDictionaryReader.readColumnDataDictionary(table, "Field");
    }


    @Then("^the field of (.*) table in LabKey should same as retrieved from Data Dictionary$")
    public void theFieldOfCancer_analysisTableInLabKeyShouldSameAsRetrievedFromDataDictionary(String table) {
        boolean test_result = false;
        List<String> UIField = gms.getUIField();
        Debugger.println("UI: size "+UIField.size());
        Debugger.println("DATA DICTIONARY: "+dataDictionaryReader.fieldList.size());
        Debugger.println("UI: "+UIField.size()+" : " + UIField.toString());
        Debugger.println("DATA DICTIONARY: "+dataDictionaryReader.fieldList.size()+" : "  + dataDictionaryReader.fieldList.toString());
        if (!UIField.containsAll(dataDictionaryReader.fieldList)) {
            Debugger.println("FAIL");
            test_result = true;
        } else {
            Debugger.println("PASS");
        }
        Assert.assertFalse("UI: " + UIField.toString() + " \n DATA DICTIONARY: " + dataDictionaryReader.fieldList.toString(), test_result);
    }


    @Then("^the patient count on the dashboard for (.*) table is same as the retrieved value$")
    public void thePatientCountOnTheDashboardForCancer_analysisTableIsSameAsTheRetrievedValue(String table) {
        boolean test_result = false;
        String result = "";
        int patientCount = gms.getPatientCount();
        Debugger.println("UI Count : " + patientCount);
        Debugger.println("DB Count : " + DBStepDefs.patientCount);
        if (patientCount != DBStepDefs.patientCount) {
            Debugger.println("FAIL");
            test_result = true;
            result = "FAIL";
        } else {
            result = "PASS";
            Debugger.println("PASS");
        }
        Report.println(TestHooks.currentTagName + " " + TestHooks.currentFeature + " UI: " + patientCount + " DB: " + DBStepDefs.patientCount + " " + result);
        Assert.assertFalse("UI: " + patientCount + " \n DB: " + DBStepDefs.patientCount, test_result);
    }


    @When("^the user retrieves the data type from data dictionary for (.*) table$")
    public void theUserRetrievesTheDataTypeFromDataDictionaryForCancerAnalysisTable(String table) {
        dataDictionaryReader.getDataType(table);
    }


    @Then("^the data type of (.*) table in LabKey should same as retrieved from Data Dictionary$")
    public void theDataTypeOfCancer_analysisTableInLabKeyShouldSameAsRetrievedFromDataDictionary(String table) {
        boolean test_result = false;
        Map<String, String> UIDataType = dataTypeReader.getDataType(table);
        Debugger.println("UI Data type: " + UIDataType);
        Debugger.println("DD Data type: " + dataDictionaryReader.dataTypeList);
        if (UIDataType.equals(dataDictionaryReader.dataTypeList)) {
            test_result = true;
            Debugger.println("PASS");
        }
        Assert.assertTrue("UI: " + UIDataType + " \n Data Dictionary: " + dataDictionaryReader.dataTypeList, test_result);
    }

    @And("^the user downloads the (.*) sheet$")
    public void theUserDownloadsTheAggregate_gvcf_sample_statsSheet(String file) {
        gms.exportToExcel();
    }


    @And("^the user combines the column of (.*) table$")
    public void theUserCombinesTheColumnOfAggregate_gvcf_sample_statsTable(String table, DataTable inp_data) {
        String column = "";
        for (Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
            if (column.isEmpty() || column.equalsIgnoreCase("")) {
                column = data.get("column");
            } else {
                column = column + "," + data.get("column");
            }
        }
        Debugger.println("My column is : "+column);
        dataExcelReader.combineColumn(column);
    }

    @Then("^the combined column should be unique$")
    public void theCombinedColumnShouldBeUnique() {
        boolean test_result = false;
        String duplicateData = "";
        Debugger.println("Number of record is : "+dataExcelReader.dataList.size());
        for (int i = 0; i < dataExcelReader.dataList.size(); i++) {
            for (int j = i + 1; j < dataExcelReader.dataList.size(); j++) {
                if (dataExcelReader.dataList.get(i).equals(dataExcelReader.dataList.get(j))) {
                    duplicateData = dataExcelReader.dataList.get(i);
                    test_result = true;
                    Debugger.println("Duplicate Data is : " + dataExcelReader.dataList.get(i));
                }
            }
        }
        Assert.assertFalse("Duplicate Data is : " + duplicateData, test_result);

    }

    @And("^the user navigates to start page$")
    public void theUserNavigatesToStartPage() {
        gms.navigatesToMainProgramme();
    }


    @And("^the user retrieves the data of following (.*) from (.*) sheet$")
    public void theUserRetrievesTheDataOfFollowingColumnFromAggregate_gvcf_sample_statsSheet(String column,String table) {
           dataExcelReader.getData(table,column);
    }


    @Then("^the data of (.*) should same as retrieved data for (.*) sheet$")
    public void theDataOfFollowingColumnShouldSameAsRetrievedDataForParticipantSheet(String column,String table) {
        boolean test_result = false;
        List<String> referenceDataList = dataExcelReader.validateData(table, column);
        if(!referenceDataList.equals(dataExcelReader.dataList)){
            Debugger.println("Parent data "+dataExcelReader.dataList);
            Debugger.println("Reference Data "+referenceDataList);
            test_result = true;
        }
        Assert.assertFalse(test_result);
    }




    @And("^the user combines the column of (.*) table and should same as retrieved data$")
    public void theUserCombinesTheColumnOfdeath_detailsTableAndShouldSameAsRetrievedData(String table, DataTable inp_data) {
            String column = "";
            for (Map<String, String> data : inp_data.asMaps(String.class, String.class)) {
                if (column.isEmpty() || column.equalsIgnoreCase("")) {
                    column = data.get("column");
                } else {
                    column = column + "," + data.get("column");
                }
            }

           // dataExcelReader.validateCombineColumn(column);


        boolean test_result = false;
            Debugger.println("my column is : "+column);

        List<String> referenceDataList = dataExcelReader.validateCombineColumn(column);
        if(!referenceDataList.equals(dataExcelReader.dataList)){
            Debugger.println("Parent data "+dataExcelReader.dataList);
            Debugger.println("Reference Data "+referenceDataList);
            test_result = true;
        }
        Assert.assertFalse(test_result);
    }


//    @And("the user retrieves the field from enumerations for (.*) table")
//    public void theUserRetrievesTheFieldFromEnumerationsForData_dictionary_tableTable(String table) {
//        enumerationsReader.readColumnEnumerations(table, "Field");
//    }
//
//    @Then("the field of (.*) table in LabKey should same as retrieved from enumerations")
//    public void theFieldOfLabKey_tableTableInLabKeyShouldSameAsRetrievedFromEnumerations() {
//        boolean test_result = false;
//        List<String> UIField = mainProgramme.getUIField();
//        Debugger.println("UI: " + UIField.toString());
//        Debugger.println("DATA DICTIONARY: " + enumerationsReader.eFieldList.toString());
//        if (!UIField.equals(enumerationsReader.eFieldList)) {
//            Debugger.println("FAIL");
//            test_result = true;
//        } else {
//            Debugger.println("PASS");
//        }
//        Assert.assertFalse("UI: " + UIField.toString() + " \n DATA DICTIONARY: " + dataDictionaryReader.fieldList.toString(), test_result);
//    }

    @And("the user should able to verify enumerations for (.*)")
    public void theUserShouldAbleToVerifyEnumerations(String table) {
        boolean testResult = false;
        String vmessage = "";
        vmessage = dataDictionaryReader.verifyTheEnumerations(table);
        if (!vmessage.equalsIgnoreCase("Success")) {
            Debugger.println(vmessage);
            testResult = true;

        }

        Assert.assertFalse(vmessage, testResult);

    }

    @When("the user retrieves the enumerations from (.*) table")
    public void theUserRetrievesTheEnumerationsFromData_dictionary_tableTable(String table) {
        dataDictionaryReader.getEnumerationsType(table);
    }

    @When("^the user navigates to (.*) project of frequent releases$")
    public void theUserNavigatesToFrequentReleaseProjectOfFrequentReleases(String project) {
        Debugger.println("Frequent-Release");
        homePage.navigatesToProject(project);
    }

}//end
