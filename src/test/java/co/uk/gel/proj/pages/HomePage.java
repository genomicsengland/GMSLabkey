package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private SeleniumLib seleniumLib;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public void navigatesToProject(String project) {
        try {

            Debugger.println("Redirecting To... " + project);
            By projectPath = By.xpath("//span[text()='" + project + "']");
            Debugger.println("Redirecting To... " + projectPath);
//            By projects = By.xpath("//span[text()='Projects']");
//            SeleniumLib.waitForElementVisible(projects);
            if (!seleniumLib.isElementPresent(projectPath)) {
                Debugger.println("Given Project is NOT available " + project);
                seleniumLib.sleepInSeconds(4);
                for (int i = 0; i < 5; i++) {
                    try {
                        if (!seleniumLib.isElementPresent(projectPath)) {
                            seleniumLib.sleepInSeconds(1);
                            seleniumLib.waitForElementVisible(projectPath);
                        }
                    } catch (Exception exp) {
                        // no need to do anything
                    }
                }
                if (!seleniumLib.isElementPresent(projectPath)) {
                    Assert.assertTrue("Given Project is NOT listed : " + project, false);
                }
            }
            seleniumLib.clickOnElement(projectPath);
        } catch (Exception exp) {
            Debugger.println("EXCEPTION is Found in navigatesToProject " + exp);
        }
    }


}
