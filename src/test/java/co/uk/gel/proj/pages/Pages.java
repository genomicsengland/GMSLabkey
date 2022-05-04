package co.uk.gel.proj.pages;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    protected WebDriver driver;
    private SeleniumLib seleniumLib;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected AppHomePage appHomePage;
    protected LabKeyPage labKeyPage;
    protected HomePage homePage;
    protected GMS gms;
    protected DataDictionaryReader dataDictionaryReader;
    protected DataTypeReader dataTypeReader;
    protected DataExcelReader dataExcelReader;


    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        appHomePage = PageFactory.initElements(driver, AppHomePage.class);
        labKeyPage = PageFactory.initElements(driver, LabKeyPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        gms = PageFactory.initElements(driver, GMS.class);
        dataDictionaryReader = PageFactory.initElements(driver, DataDictionaryReader.class);
        dataTypeReader = PageFactory.initElements(driver, DataTypeReader.class);
        dataExcelReader = PageFactory.initElements(driver, DataExcelReader.class);
    }




}//end class
