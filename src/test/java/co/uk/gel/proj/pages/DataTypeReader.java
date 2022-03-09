package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DataTypeReader {

    private WebDriver driver;
    private SeleniumLib seleniumLib;
    HashMap<String, Integer> title = new HashMap<String, Integer>();
    public Map<String, String> dataTypeList = new TreeMap<>();

    public DataTypeReader(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public Map<String, String> getDataType(String table) {
        try {
            String fileExtension = ".xlsx";
            String sheetName = "Sheet1";
            String directoryPath = System.getProperty("user.dir") + File.separator + "dataType";
            File file = new File(directoryPath + File.separator + table + fileExtension);
            if (!file.exists()) {
                Debugger.println("Data Type file : " + table + " not exists.");
                Assert.assertTrue("Data Type file : " + table + " not exists.", false);
            }
            FileInputStream fileinput = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileinput);
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + table);
                return null;
            }
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                Debugger.println("Sheet " + sheetName + " doesn't exists in the File: " + file);
                return null;
            }
            int noofrows = sheet.getPhysicalNumberOfRows();
            if (noofrows == 0) {
                Debugger.println("ExcelReader: " + sheetName + " Sheet Has NO Rows ");
                return null;
            }

            Row titleRow = sheet.getRow(0);
            if (titleRow == null) {
                Debugger.println("sheet does not contain the titles");
                return null;
            }

            short minColIx = titleRow.getFirstCellNum(); //get the first column index for a row
            short maxColIx = titleRow.getLastCellNum(); //get the last column index for a row
            for (short colIx = minColIx; colIx < maxColIx; colIx++) { //loop from first to last index
                Cell cell = titleRow.getCell(colIx); //get the cell
                title.put(cell.getStringCellValue(), cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
            }

            String field = "";
            String dataType = "";
            for (int i = 1; i < noofrows; i++) {
                try {
                    field = sheet.getRow(i).getCell(title.get("Property")).getStringCellValue();
                    dataType = sheet.getRow(i).getCell(title.get("RangeURI")).getStringCellValue().toLowerCase();
                } catch (NullPointerException exp) {
                        field = sheet.getRow(i).getCell(title.get("Name")).getStringCellValue();
                        dataType = sheet.getRow(i).getCell(title.get("Type")).getStringCellValue().toLowerCase();
                }
                dataTypeList.put(field.toLowerCase().trim(), dataType.toLowerCase().trim());
            }
        } catch (Exception exp) {
            Debugger.println("Exception is Found in readColumnDataDictionary " + exp);
        }
        return dataTypeList;
    }


}
