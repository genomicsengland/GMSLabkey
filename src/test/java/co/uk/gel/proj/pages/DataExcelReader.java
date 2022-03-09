package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataExcelReader {
    public ArrayList<String> dataList = new ArrayList<>();

    public void combineColumn(String column) {
        try {
            String[] columnarr = column.split(",");
            String sheetName = "data";
            String directoryPath = System.getProperty("user.dir") + File.separator + "downloads";
            SeleniumLib.sleepInSeconds(10); // to ignore .part file
            File directory = new File(directoryPath);
            File[] files = directory.listFiles();
            Debugger.println("Number of downloaded file is : " + files.length);
            String fileName = "";
            for (File file : files) {
                fileName = file.getName();
                Debugger.println("Downloaded File is : " + fileName);
                if (!fileName.endsWith(".xlsx")) {
                    Debugger.println("Invalid File is downloaded " + fileName);
                    Assert.assertTrue("Invalid File is downloaded " + fileName, false);
                }
            }
            File file = new File(directoryPath + File.separator + fileName);
            Debugger.println("Path of file is : " + file.getAbsolutePath());
            InputStream is = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder().rowCacheSize(1000).bufferSize(102400).sstCacheSize(1485760).open(is);
            Debugger.println("workbook is created");
            int rowNum = 0;
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + fileName);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            Map<String, Integer> map = new HashMap<String, Integer>();

            for (Row row : sheet) {
                ++rowNum;
                if (rowNum == 1) {
                    for (Cell dataCell : row) {
                        Debugger.println("cell value is : " + dataCell.getStringCellValue());
                        map.put(dataCell.getStringCellValue(), dataCell.getColumnIndex());
                    }

                } else {
                    String data = "";
                    String columnName = "";
                    for (int i = 0; i < columnarr.length; i++) {
                        columnName = columnarr[i];
                        Cell dataCell = row.getCell(map.get(columnName));
                        data = data + " " + dataCell.getStringCellValue();
                    }
                    //  Debugger.println("data is : " + data);
                    dataList.add(data);
                }
            }
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in combineColumn " + exp);
            exp.printStackTrace();
            Assert.assertTrue("EXCEPTION is Found in combineColumn ", false);
        }
    }


    public List<String> validateCombineColumn(String column) {
        try {
            ArrayList<String> validateDataList = new ArrayList<>();
            String[] columnarr = column.split(",");
            String sheetName = "data";
            String directoryPath = System.getProperty("user.dir") + File.separator + "downloads";
            SeleniumLib.sleepInSeconds(10); // to ignore .part file
            File directory = new File(directoryPath);
            File[] files = directory.listFiles();
            Debugger.println("Number of downloaded file is : " + files.length);
            String fileName = "";
            for (File file : files) {
                fileName = file.getName();
                Debugger.println("Downloaded File is : " + fileName);
//                if (!fileName.endsWith(".xlsx")) {
//                    Debugger.println("Invalid File is downloaded " + fileName);
//                    Assert.assertTrue("Invalid File is downloaded " + fileName, false);
//                }
            }
            File file = new File(directoryPath + File.separator + fileName);
            Debugger.println("Path of file is : " + file.getAbsolutePath());
            InputStream is = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder().rowCacheSize(1000).bufferSize(102400).sstCacheSize(1485760).open(is);
            Debugger.println("workbook is created");
            int rowNum = 0;
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + fileName);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            Map<String, Integer> map = new HashMap<String, Integer>();

            for (Row row : sheet) {
                ++rowNum;
                if (rowNum == 1) {
                    for (Cell dataCell : row) {
                        Debugger.println("cell value is : " + dataCell.getStringCellValue());
                        map.put(dataCell.getStringCellValue(), dataCell.getColumnIndex());
                    }

                } else {
                    String data = "";
                    String columnName = "";
                    for (int i = 0; i < columnarr.length; i++) {
                        columnName = columnarr[i];
                        Debugger.println("column : "+columnName+", index is : "+map.get(columnName));
                        Cell dataCell = row.getCell(map.get(columnName));
                        data = data + " " + dataCell.getStringCellValue();
                    }
                      Debugger.println("data is : " + data);
                    validateDataList.add(data);
                }
            }
            Debugger.println("Number of data in validate list is : "+validateDataList.size());
            return validateDataList;
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in combineColumn " + exp);
            exp.printStackTrace();
            Assert.assertTrue("EXCEPTION is Found in combineColumn ", false);
            return null;
        }
    }


    public void getData(String fileName, String columnName) {
        try {

            String sheetName = "data";
            String fileExtension = ".xlsx";
            int rowNum = 0;
            SeleniumLib.sleepInSeconds(40); // to ignore .part file
            String directoryPath = System.getProperty("user.dir") + File.separator + "downloads";
            File file = new File(directoryPath + File.separator + fileName + fileExtension);
            Debugger.println("Path of file is : " + file.getAbsolutePath());
            InputStream is = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder().rowCacheSize(1000).bufferSize(102400).sstCacheSize(1485760).open(is);
            Debugger.println("workbook is created");
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + fileName);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            Map<String, Integer> map = new HashMap<String, Integer>();

            for (Row row : sheet) {
                ++rowNum;
                if (rowNum == 1) {
                    for (Cell dataCell : row) {
                        Debugger.println("cell value is : " + dataCell.getStringCellValue());
                        map.put(dataCell.getStringCellValue(), dataCell.getColumnIndex());
                    }
                } else {
                    String data = "";
                    Cell dataCell = row.getCell(map.get(columnName));
                    data = dataCell.getStringCellValue();
                    dataList.add(data);
                }
            }
            Debugger.println("Number of data in parent table is : "+dataList.size());
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in getData " + exp);
        }
    }

    public List<String> validateData(String fileName, String columnName) {
        try {
            ArrayList<String> validateDataList = new ArrayList<>();
            String sheetName = "data";
            String fileExtension = ".xlsx";
            int rowNum = 0;
            SeleniumLib.sleepInSeconds(10); // to ignore .part file
            String directoryPath = System.getProperty("user.dir") + File.separator + "downloads";
            File file = new File(directoryPath + File.separator + fileName + fileExtension);
            Debugger.println("Path of file is : " + file.getAbsolutePath());
            InputStream is = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder().rowCacheSize(1000).bufferSize(102400).sstCacheSize(1485760).open(is);
            Debugger.println("workbook is created");
            if (workbook == null) {
                Debugger.println("workbook could not create from the File: " + fileName);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            Map<String, Integer> map = new HashMap<String, Integer>();

            for (Row row : sheet) {
                ++rowNum;
                if (rowNum == 1) {
                    for (Cell dataCell : row) {
                        Debugger.println("cell value is : " + dataCell.getStringCellValue());
                        map.put(dataCell.getStringCellValue(), dataCell.getColumnIndex());
                    }
                } else {
                    String data = "";
                    Cell dataCell = row.getCell(map.get(columnName));
                    data = dataCell.getStringCellValue();
                    validateDataList.add(data);
                }
            }
            Debugger.println("Number of data in validate list is : "+validateDataList.size());
            return validateDataList;
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in getData " + exp);
            return null;
        }

    }

}//end