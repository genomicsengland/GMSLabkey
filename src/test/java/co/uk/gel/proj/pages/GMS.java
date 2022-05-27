package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GMS {

    private WebDriver driver;
    private SeleniumLib seleniumLib;

    public GMS(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }


    public void navigatesToVersion(String version) {
        try {
            By versionPath = By.xpath("//span[contains(text(),'main-programme_v" + version + "_')]");
            if (!seleniumLib.isElementPresent(versionPath)) {
                Debugger.println("Given Version " + version + " is NOT available in main Programme");
                return;
            }
            seleniumLib.clickOnElement(versionPath);
            seleniumLib.pageLoadTime();
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in navigatesToVersion " + exp);
        }
    }

    public String navigatesToVersion() {
        try {
            SeleniumLib.sleepInSeconds(3);
            By versionPath = By.xpath("//span[contains(text(),'gms-release-" +AppConfig.getApp_version()+"')]");
//            By versionPath = By.xpath("//span[contains(text(),'main-programme_v" + version + "_')]");
           SeleniumLib.waitForElementVisible(versionPath);
            if (!seleniumLib.isElementPresent(versionPath)) {
                Debugger.println("Given current Version  is NOT available in main Programme");
                return "Given current Version  is NOT available in main Programme";
            }
            seleniumLib.clickOnElement(versionPath);
            seleniumLib.pageLoadTime();
            return "Success";
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in navigatesToVersion " + exp);
            return "EXCEPTION is Found in navigatesToVersion " + exp;
        }
    }


    public void clickOnTable(String table) {
        try {
            SeleniumLib.sleepInSeconds(3);
            By tablePath = By.xpath("//span[contains(@class,'dataview-icon')]/following-sibling::a[text()='" + table + "']");
            if (!seleniumLib.isElementPresent(tablePath)) {
                seleniumLib.waitForElementVisible(tablePath);
                Debugger.println("Table is NOT available " + table);
                Assert.assertTrue("Table is NOT available " + table, false);
            }
            seleniumLib.highLightElement(tablePath);
            seleniumLib.clickOnElement(tablePath);
            seleniumLib.pageLoadTime();
            SeleniumLib.sleepInSeconds(10);
            seleniumLib.pageLoadTime();
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in clickOnTable " + exp);
        }
    }

    public List<String> getUIField() {
        try {
            By fieldPath = By.xpath("(//tr[@class='labkey-col-header-row'])[1]/th/following-sibling::th");
            seleniumLib.sleepInSeconds(5);
            for (int i = 0; i < 5; i++) {
                try {
                    if (!seleniumLib.isElementPresent(fieldPath)) {
                        Debugger.println("Waiting for table to appear..");
                        seleniumLib.sleepInSeconds(4);
                        seleniumLib.waitForElementVisible(fieldPath);
                    }
                } catch (Exception exp) {
                    Debugger.println("Table is not loaded..");
                }
            }

            List<WebElement> fieldList = seleniumLib.getElements(fieldPath);
            ArrayList<String> UIfieldList = new ArrayList<String>();
            for (int i = 0; i < fieldList.size(); i++) {
                String field = fieldList.get(i).getText();
                field = field.replaceAll(" ", "_").toLowerCase();
                UIfieldList.add(field);
            }
            Collections.sort(UIfieldList);
            return UIfieldList;
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in getUIField " + exp);
            return null;
        }
    }

    public int getPatientCount() {
        try {
            By countPath = By.xpath("(//div[@class='labkey-pagination'])[1]/div/a");
            if (!seleniumLib.isElementPresent(countPath)) {
                for (int i = 0; i < 5; i++) {
                    try {
                        if (!seleniumLib.isElementPresent(countPath)) {
                            seleniumLib.sleepInSeconds(2);
                            seleniumLib.waitForElementVisible(countPath);
                        }
                    } catch (Exception exp) {
                        Debugger.println("Waiting to load the table..");
                    }
                }
            }
            seleniumLib.highLightElement(countPath);
            String count = seleniumLib.getText(countPath);
            Debugger.println("COUNT is " + count);
            if (count.contains("of")) {
                count = count.split("of")[1].trim();
            }
            if (count.contains(",")) {
                count = count.replaceAll(",", "").trim();
            }
            return Integer.parseInt(count);
        } catch (Exception exp) {
            Debugger.println("EXCEPTION Found in getParticipantCount " + exp);
            return -1;
        }
    }

    public String clearDirectory() {
        try {
            String directoryPath = System.getProperty("user.dir") + File.separator + "downloads";
            File directory = new File(directoryPath);
            File[] files = directory.listFiles();
            Debugger.println("Already file present number is : " + files.length);
            for (File file : files) {
                if (!file.delete()) {
                    return "File is NOT Deleted " + file;
                }
            }
        } catch (Exception exp) {
            return "EXCEPTION is found in clearDirectory " + exp;
        }
        return "Success";
    }


    public void exportToExcel() {
        try {
            String vmessage = clearDirectory();
            Debugger.println("After clearing directory.. " + vmessage);
            if (!vmessage.equalsIgnoreCase("Success")) {
                Debugger.println("Clear directory is Failed " + vmessage);
            }

            By exportDropDwnPath = By.xpath("//span[text()='Export']");
            By exportBtnPath = By.xpath("//span[text()='Export to Excel']");
            if (!seleniumLib.isElementPresent(exportDropDwnPath)) {
                for (int i = 0; i < 4; i++) {
                    if (!seleniumLib.isElementPresent(exportDropDwnPath)) {
                        try {
                            seleniumLib.sleepInSeconds(1);
                            seleniumLib.waitForElementVisible(exportDropDwnPath);
                        } catch (Exception exp) {
                            Debugger.println("Waiting to Load the table");
                        }
                    }
                }
            }
            seleniumLib.highLightElement(exportDropDwnPath);
            seleniumLib.clickOnElement(exportDropDwnPath);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement(exportBtnPath);
            Debugger.println("click on export button");
            seleniumLib.sleepInSeconds(70);  // to download the file
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found while downloading the xlsx " + exp);
            Assert.assertTrue("Error while downloading the xlsx", false);
        }
    }


    public void navigatesToMainProgramme() {
        try {

            By mainProgrammeTitle = By.xpath("//div[@class='labkey-folder-title']");
            if (!seleniumLib.isElementPresent(mainProgrammeTitle)) {
                Debugger.println("Main Programme Title is NOT available..");
            }
            seleniumLib.highLightElement(mainProgrammeTitle);
            seleniumLib.clickOnElement(mainProgrammeTitle);
            seleniumLib.sleepInSeconds(1);
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in navigatesToMainProgramme " + exp);
            Assert.assertTrue("EXCEPTION is Found in navigatesToMainProgramme ", false);
        }
    }


}//end




