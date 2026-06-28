package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverSetup {


    public static WebDriver driver;


    public static void setup(){

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");


        driver = new ChromeDriver(options);

    }



    public static void quit(){

        if(driver != null){

            driver.quit();

        }

    }

}