package co.uk.gel.proj.config;

import org.junit.Assert;

import java.io.File;
import java.util.Scanner;

/**
 * Created by SANTHOSH LP on 4/26/2019.
 */
public class AppConfig {

    private static String app_username;
    private static String app_password;
    private static String appURL;
    private static String app_version;
    private static String home_page_url;

    public static void loadAppConfig(){
        Scanner file_scanner	= loadFile("AppConfig.txt");
        String line = "";
        String[] linearray;
        while(file_scanner.hasNextLine()) {
            line = file_scanner.nextLine();
            if(line == null || line.trim().length() == 0){
                continue;
            }
            if(line.startsWith("#")){
                continue;
            }
            linearray = line.split("=");
            if(linearray == null || linearray.length != 2){
                continue;
            }
            if (linearray[0].trim().equalsIgnoreCase("APP_URL")){
                appURL = linearray[1].trim();
            }else if (linearray[0].trim().equalsIgnoreCase("APP_USERNAME")){
                app_username = linearray[1].trim();
            } else if (linearray[0].trim().equalsIgnoreCase("APP_PASSWORD")){
                app_password = linearray[1].trim();
            }else if (linearray[0].trim().equalsIgnoreCase("APP_VERSION")){
                app_version = linearray[1].trim();
            }else if (linearray[0].trim().equalsIgnoreCase("HOME_PAGE_URL")){
                home_page_url = linearray[1].trim();
            }
        }
    }
    private static Scanner loadFile(String filename){

        String configlocation = System.getProperty("user.dir") + File.separator +"config/";
        Scanner file_scanner;
        try{
            file_scanner = new Scanner(new File(configlocation+filename));
            return file_scanner;
        }catch (Exception exp){
            Assert.assertFalse("Exception from reading AppConfig :"+exp, false);
        }
        return null;
    }

    public static String getAppURL() {
        if(appURL == null || appURL.isEmpty()){
            loadAppConfig();
        }
        return appURL;
    }
    public static String getHomePageURL() {
        if(home_page_url == null || home_page_url.isEmpty()){
            loadAppConfig();
        }
        return home_page_url;
    }


    public static String getApp_username() {
        return app_username;
    }

    public static void setApp_username(String app_username) {
        AppConfig.app_username = app_username;
    }

    public static String getApp_password() {
        return app_password;
    }

    public static void setApp_password(String app_password) {
        AppConfig.app_password = app_password;
    }

    public static String getApp_version(){return app_version;}

    public static void setApp_version(){ AppConfig.app_version = app_version;}

}//end
