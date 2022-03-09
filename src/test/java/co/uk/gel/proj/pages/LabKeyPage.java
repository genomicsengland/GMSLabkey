package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LabKeyPage {

    private WebDriver driver;
    private SeleniumLib seleniumLib;
      By signInOption = By.xpath("//a[@class='header-link']"); //pre-prod
//    By signInOption = By.xpath("//a[@class='labkey-nomenu-text-link']"); //prod
//    By username = By.xpath("//label[contains(text(),'Username')]/following-sibling::input[@name='email']");  //prod
    By username = By.xpath("//label[contains(text(),'Username')]/following-sibling::input[@name='email']");  //preprod
//    By username = By.xpath("//input[@id='email']");
    By password = By.xpath("//label[contains(text(),'Password')]/following-sibling::input[@name='password']");
    By signInBtn = By.xpath("//span[text()='Sign In']/..");

    public LabKeyPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean isCurrentPage() {
        seleniumLib.sleepInSeconds(5);
        By header = By.xpath("//div[text()='Sign In']");
        seleniumLib.sleepInSeconds(2); // to load labkey page
        return (seleniumLib.isElementPresent(header));
    }

    public void loginToLabkey() {
        try {
            seleniumLib.sleepInSeconds(2);
            if (seleniumLib.isElementPresent(signInOption)) {
                seleniumLib.clickOnElement(signInOption);
            }

            if (isCurrentPage()) {
                seleniumLib.sleepInSeconds(2);
                seleniumLib.sendValue(username, AppConfig.getApp_username());
                seleniumLib.sendValue(password, AppConfig.getApp_password());
                seleniumLib.clickOnElement(signInBtn);
                seleniumLib.sleepInSeconds(10);
            } else {
                Debugger.println("IT is NOT navigated to Sign IN of LabKey.. Directly logged in LabKey..");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in loginToLabkey " + exp);
            Assert.assertTrue("EXCEPTION is Found in loginToLabkey", false);
        }
    }

}
