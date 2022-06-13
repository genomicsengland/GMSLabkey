package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DataDictionaryReader {

    private WebDriver driver;
    private SeleniumLib seleniumLib;
    HashMap<String, Integer> data = new HashMap<String, Integer>();
    public ArrayList<String> fieldList = new ArrayList<String>();
    public Map<String, String> dataTypeList = new TreeMap<String, String>();

    HashMap<String, Integer> eData = new HashMap<String, Integer>();
    public ArrayList<String> eFieldList = new ArrayList<String>();
    public Map<String, String> enumerationsList = new TreeMap<String, String>();


    public DataDictionaryReader(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }


    public void readColumnDataDictionary(String table, String columnName) {
        try {
            String fileExtension = ".xlsx";
            String sheetName = "Sheet1";
            String directoryPath = System.getProperty("user.dir") + File.separator + "dataDictionary";
            File file = new File(directoryPath + File.separator + table + fileExtension);
            if (!file.exists()) {
                Debugger.println("Data validation file : " + table + " not exists.");
                Assert.assertTrue("Data validation file : " + table + " not exists.", false);
                return;
            }
            FileInputStream fileinput = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileinput);
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + table);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                Debugger.println("Sheet " + sheetName + " doesn't exists in the File: " + file);
                return;
            }
            int noofrows = sheet.getPhysicalNumberOfRows();
            if (noofrows == 0) {
                Debugger.println("ExcelReader: " + sheetName + " Sheet Has NO Rows ");
                return;
            }

            Row titleRow = sheet.getRow(0);
            if (titleRow == null) {
                Debugger.println("sheet does not contain the titles");
            }

            short minColIx = titleRow.getFirstCellNum(); //get the first column index for a row
            short maxColIx = titleRow.getLastCellNum(); //get the last column index for a row
            for (short colIx = minColIx; colIx < maxColIx; colIx++) { //loop from first to last index
                Cell cell = titleRow.getCell(colIx); //get the cell
                data.put(cell.getStringCellValue(), cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
            }

            Integer column = data.get(columnName);
            String field = "";
            for (int i = 1; i < noofrows; i++) {
                field = sheet.getRow(i).getCell(column).getStringCellValue();
                field = field.toLowerCase();
                if (field.contains(" ")) {
                    field = field.replaceAll(" ", "_");
                }
                fieldList.add(field);
            }
            Collections.sort(fieldList);

        } catch (Exception exp) {
            Debugger.println("Exception is Found in readColumnDataDictionary " + exp);
        }
    }


    public void getDataType(String table) {
        try {
            String fileExtension = ".xlsx";
            String sheetName = "Sheet1";
            String directoryPath = System.getProperty("user.dir") + File.separator + "DBFieldsDataType";
//            String directoryPath = System.getProperty("user.dir") + File.separator + "dataType";

            File file = new File(directoryPath + File.separator + table + fileExtension);
            if (!file.exists()) {
                Debugger.println("Data validation file : " + table + " not exists.");
            }
            FileInputStream fileinput = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileinput);
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + table);
                return;
            }
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                Debugger.println("Sheet " + sheetName + " doesn't exists in the File: " + file);
                return;
            }
            int noofrows = sheet.getPhysicalNumberOfRows();
            if (noofrows == 0) {
                Debugger.println("ExcelReader: " + sheetName + " Sheet Has NO Rows ");
                return;
            }

            Row titleRow = sheet.getRow(0);
            if (titleRow == null) {
                Debugger.println("sheet does not contain the titles");
                return;
            }

            short minColIx = titleRow.getFirstCellNum(); //get the first column index for a row
            short maxColIx = titleRow.getLastCellNum(); //get the last column index for a row
            for (short colIx = minColIx; colIx < maxColIx; colIx++) { //loop from first to last index
                Cell cell = titleRow.getCell(colIx); //get the cell
                data.put(cell.getStringCellValue(), cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
            }

            String field = "";
            String dataType = "";
            for (int i = 1; i < noofrows; i++) {
                try {
                    field = sheet.getRow(i).getCell(data.get("Name")).getStringCellValue().trim();
                    dataType = sheet.getRow(i).getCell(data.get("Type")).getStringCellValue().toLowerCase().trim();

                } catch (NullPointerException exp1) {
                    field = sheet.getRow(i).getCell(data.get("Field")).getStringCellValue().trim();
                    dataType = sheet.getRow(i).getCell(data.get("Value")).getStringCellValue().toLowerCase().trim();
                }
                if (dataType.equalsIgnoreCase("timestamp without time zone")) {
                    dataType = "timestamp";
                } else if (dataType.equalsIgnoreCase("character varying")) {
                    dataType = "varchar";
                } else if (dataType.equalsIgnoreCase("double precision")) {
                    dataType = "double";
                } else if (dataType.equalsIgnoreCase("float")) {
                    dataType = "double";
                } else if (dataType.equalsIgnoreCase("text")) {
                    dataType = "varchar";
                }else if (dataType.equalsIgnoreCase("String")) {
                    dataType = "varchar";
                }else if (dataType.equalsIgnoreCase("xs:string")) {
                    dataType = "varchar";
                } else if (dataType.equalsIgnoreCase("participantId, xs:string")) {
                    dataType = "varchar";
                } else if (field.equalsIgnoreCase("UICC code; xs:string")) {
                    dataType = "varchar";
                } else if (field.equalsIgnoreCase("boolean")) {
                    dataType = "boolean";
                }else if (field.equalsIgnoreCase("decimal")){
                    dataType="double";
//                } else if (field.equalsIgnoreCase("participant_id")) {
//                    dataType = "Integer";
//                }else if (field.equalsIgnoreCase("participant_sk")) {
//                    dataType = "Integer";
//                }
//                else if (dataType.equalsIgnoreCase("enumerations:") || dataType.equalsIgnoreCase("enumerations")) {
//                    dataType = "varchar";
                } else if (dataType.contains("Enumerations") || dataType.contains("enumeration") || dataType.contains("Enumeration:")) {
                    dataType = "varchar";
//                } else if (dataType.equalsIgnoreCase("float")) {
//                    dataType = "double";
//                } else if (dataType.equalsIgnoreCase("xs:integer")) {
//                    dataType = "Integer";
                } else if (dataType.equalsIgnoreCase("xs:decimal")) {
                    dataType = "double";
                } else if (dataType.equalsIgnoreCase("dnaConcentration, xs:decimal")) {
                    dataType = "double";
                } else if (dataType.equalsIgnoreCase("laboratorysampleid, xs:string") || dataType.equalsIgnoreCase("rndid, xs:string")) {
                    dataType = "varchar";
                } else if (dataType.equalsIgnoreCase("plateid, xs:string") || dataType.equalsIgnoreCase("wellid, xs:string")){
                    dataType = "varchar";
                } else if (dataType.equalsIgnoreCase("string") || dataType.equalsIgnoreCase("varchar")) {
                    dataType = "varchar";
//                } else if (dataType.equalsIgnoreCase("maxLength(12) && minLength(3)")) {
//                    dataType = "Integer";
                } else if (dataType.equalsIgnoreCase("Date")) {
                    dataType = "timestamp";
                } else if (dataType.equalsIgnoreCase("datetime")) {
                    dataType = "timestamp";
                }  else if (dataType.equalsIgnoreCase("n")) {
                        dataType = "varchar";
//                } else if (dataType.equalsIgnoreCase("xs:datetime")) {
//                    dataType = "timestamp";
                } else if (dataType.contains("yyyy-mm-dd")) {
                    dataType = "timestamp";
                } else if (dataType.contains("yyyy/mm/dd")) {
                    dataType = "timestamp";
                } else if (dataType.equalsIgnoreCase("yyyy")){
                    dataType="Integer";
//                } else if (dataType.equalsIgnoreCase("ddmmyyy")) {
//                    dataType = "timestamp";
                } else if (dataType.equalsIgnoreCase("ccyy-mm-xx")||dataType.equalsIgnoreCase("yyyy/mm/dd")) {
                    dataType = "timestamp";
//                } else if (dataType.equalsIgnoreCase("mm-ddThh:mm:sss") || dataType.equalsIgnoreCase("dd-mmm-ccyy") || dataType.equalsIgnoreCase("ddmmyyyy") || dataType.equalsIgnoreCase("yyyymm")) {
//                    dataType = "timestamp";
//                } else if (dataType.equalsIgnoreCase("xs:double")) {
//                    dataType = "double";
                }else if (dataType.equalsIgnoreCase("xs:interger")) {
                    dataType = "Integer";
                } else if (dataType.equalsIgnoreCase("numeric")){
                    dataType = "integer";
//                } else if (dataType.equalsIgnoreCase("positiveinterger, xs:interger")) {
//                    dataType = "Integer";
                } else if (dataType.equalsIgnoreCase("xs:date")) {
                    dataType = "timestamp";
//                } else if (dataType.equalsIgnoreCase("Y or blank")) {
//                    dataType = "Boolean";
//                } else if (dataType.equalsIgnoreCase("xs:dateTime")) {
//                    dataType = "timestamp";
//                } else if (dataType.equalsIgnoreCase("int")) {
//                    dataType = "Integer";
                }else if (dataType.contains("Categorical")) {
                        dataType = "varchar";
                } else if (dataType.equalsIgnoreCase("character")) {
                    dataType = "varchar";
//                } else if (dataType.equalsIgnoreCase("ddmmyyy")) {
//                    dataType = "timestamp";
//                } else if (field.contains("idx")) {
//                    dataType = "Integer";
//                } else if (dataType.contains("xs:string")) {
//                    dataType = "varchar";
//                } else if (dataType.contains("string")) {
//                    dataType = "varchar";
                } else if (dataType.contains("dd/mm/yyyy")) {
                    dataType = "timestamp";
//                } else if (dataType.equalsIgnoreCase("")) {
//                    dataType = "varchar";
//                } else if (dataType.equalsIgnoreCase("ann")) {
//                    dataType = "varchar";
//                } else if (field.contains("date_of_birth")) {
//                    dataType = "Integer";
////                } else if (field.contains("date") || field.contains("timestamp")) {
////                    dataType = "timestamp";
                } else if (dataType.contains(" ")) {
                    String[] listOfString = dataType.split(" ");
                    if (listOfString.length > 1) {
                        dataType = "varchar";
                    }
                } else if (dataType.contains(",")) {
                    String[] listOfString = dataType.split(",");
                    if (listOfString.length > 1) {
                        dataType = "varchar";
                    }
//                } else {
//                    dataType = "varchar";
                }
                dataTypeList.put(field.toLowerCase().trim(), dataType.toLowerCase().trim());
            }

        } catch (InvalidFormatException exp) {
            Debugger.println("Exception is Found in readColumnDataDictionary " + exp);
        } catch (FileNotFoundException exp) {
            Debugger.println("Exception is Found in readColumnDataDictionary " + exp);
        } catch (IOException exp) {
            Debugger.println("Exception is Found in readColumnDataDictionary " + exp);
        } catch (Exception exp) {
            Debugger.println("Exception is Found in readColumnDataDictionary " + exp);
        }
    }

    public void getEnumerationsType(String table) {
        boolean testResult = false;
        try {
            String fileExtension = ".xlsx";
            String sheetName = "Sheet1";
            String directoryPath = System.getProperty("user.dir") + File.separator + "Enumerations";
            File file = new File(directoryPath + File.separator + table + fileExtension);
            if (!file.exists()) {
                Debugger.println("Data validation file : " + table + " not exists.");
            }
            FileInputStream fileinput = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileinput);
//            XSSFWorkbook workbook = new XSSFWorkbook(fileinput);
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + table);
                return;
            }
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                Debugger.println("Sheet " + sheetName + " doesn't exists in the File: " + file);
                return;
            }
            int noofrows = sheet.getPhysicalNumberOfRows();
            if (noofrows == 0) {
                Debugger.println("ExcelReader: " + sheetName + " Sheet Has NO Rows ");
                return;
            }

            Row titleRow = sheet.getRow(0);
            if (titleRow == null) {
                Debugger.println("sheet does not contain the titles");
                return;
            }

            short minColIx = titleRow.getFirstCellNum(); //get the first column index for a row
            short maxColIx = titleRow.getLastCellNum(); //get the last column index for a row
            for (short colIx = minColIx; colIx < maxColIx; colIx++) { //loop from first to last index
                Cell cell = titleRow.getCell(colIx); //get the cell
                eData.put(cell.getStringCellValue(), cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
            }

            String field = "";
            String dataType = "";
            for (int i = 1; i < noofrows; i++) {
                try {
                    field = sheet.getRow(i).getCell(eData.get("Field")).getStringCellValue();
                    dataType = sheet.getRow(i).getCell(eData.get("Enumerations")).getStringCellValue().toLowerCase();

                } catch (NullPointerException exp1) {
                    field = sheet.getRow(i).getCell(eData.get("Field")).getStringCellValue();
                    dataType = sheet.getRow(i).getCell(eData.get("Enumerations")).getStringCellValue().toLowerCase();
                }

//                if (dataType.equalsIgnoreCase("xs:string")) {
//                    dataType = "string";
//                }
//                if (dataType.equalsIgnoreCase("float")) {
//                    dataType = "double";
//                }
//                if (dataType.equalsIgnoreCase("varchar")) {
//                    dataType = "string";
//                }
//                if (dataType.equalsIgnoreCase("Date")) {
//                    dataType = "dateTime";
//                }
//                if (dataType.equalsIgnoreCase("participantId, xs:string")) {
//                    dataType = "Integer";
//                }
                testResult = true;
                enumerationsList.put(field, dataType);

            }
            Assert.assertTrue(testResult);
        } catch (Exception exp) {
            Debugger.println("Exception is Found in readColumnDataDictionary " + exp);
            Assert.assertTrue(testResult);
        }
    }

    @FindBy(xpath = "//table[contains(@class,'labkey-data-region')]//tbody/tr")
    public List<WebElement> rows;

    //table[contains(@class,'labkey-data-region')]//tbody/tr[1]/td[1]
    public String verifyTheEnumerations(String table) {
        try {
            By fieldPath = By.xpath("(//tr[@class='labkey-col-header-row'])[1]/th/following-sibling::th");
            for (int i = 0; i < 5; i++) {
                try {
                    if (!seleniumLib.isElementPresent(fieldPath)) {
                        Debugger.println("Waiting for table to appear..");
                        seleniumLib.sleepInSeconds(2);
                        seleniumLib.waitForElementVisible(fieldPath);
                    }
                } catch (Exception exp) {
                    Debugger.println("Table is not loaded..");
                }
            }

            List<WebElement> fieldList = seleniumLib.getElements(fieldPath);
//                ArrayList<String> UIfieldList = new ArrayList<String>();
//                for (int i = 0; i < fieldList.size(); i++) {
//                    String field = fieldList.get(i).getText();
//                    field = field.replaceAll(" ", "_").toLowerCase();
//                    UIfieldList.add(field);
//                }


//            List<String> UIField = mainProgramme.getUIField();
            System.out.println(enumerationsList);
            Set<String> keys = enumerationsList.keySet();

            for (String key : keys) {
                System.out.println(key);
                int index = 1;
                String expFieldValue = enumerationsList.get(key);
                for (int j = 0; j < fieldList.size(); j++) {
                    String field = fieldList.get(j).getText();
                    field = field.replaceAll(" ", "_").toLowerCase();
                    index++;
                    if (key.contains(field)) {
                        if (rows.size() == 0 || rows.isEmpty()) {
                            Debugger.println("The " + table + " is empty ");
                            return "The " + table + " is empty ";
                        }
                        for (int i = 1; i <= rows.size(); i++) {

                            By coluPath = By.xpath("//table[contains(@class,'labkey-data-region')]//tbody/tr[" + i + "]/td[" + index + "]");
                            String actField = seleniumLib.getText(coluPath).toLowerCase();
                            if (!actField.equals(" ")) {
                                if (!expFieldValue.contains(actField)) {
                                    Debugger.println("Act value : " + actField + " : Exp value miss match");
                                    return "Act value : " + actField + " : Exp value miss match";
                                }
                            }
                        }
                    }
                }
            }

            return "Success";
        } catch (Exception exp) {
            Debugger.println("Exception from : verifyTheEnumerations : " + exp);
            return "Exception from : verifyTheEnumerations : " + exp;
        }

    }
}



