package com.sibme.StepDefinitions;

import com.sibme.init.DriverFactory;
import com.sibme.testUtilities.FileOperations;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.*;

import java.io.IOException;

public class Hooks {

    FileOperations fileOperations = new FileOperations();
    DriverFactory driverFactory = new DriverFactory();
    private AppiumDriver driver;


    @BeforeAll
    public static void getPage() throws IOException {
        System.out.println("This is before all");
    }

    long startTime;

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        startTime = System.currentTimeMillis();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Step Execution Time: " + executionTime + " milliseconds");
    }

    @Before
    public void getURL() throws IOException {
//    driverFactory.startAppiumService();
        driverFactory.setUp("android");
        driver = driverFactory.getDriver();
        System.out.println("This is driver:: " + driver);
    }

//    @AfterMethod
//    public void tearDown()
//    {
//        try {
//            System.out.println("Quiting driver");
//            driver.quit();
//        }
//        catch (Exception e){
//            System.out.println("Issue while quiting driver: "+ e);
//        }
//
//    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Take screen shot");
            // Take a screenshot and attach it to the scenario report
//            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
        try {
            System.out.println("Quiting driver");
//            driver=driverFactory.getDriver(platform);
            driver.quit();
//            driverFactory.stopAppiumService();

            //driver.quit();
        } catch (Exception e) {
            System.out.println("Already closed");
        }
    }

}




