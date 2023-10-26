package com.sibme.init;

import com.sibme.testUtilities.FileOperations;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
public class DriverFactory {
    FileOperations fileOperations=new FileOperations();

    public static Map<String, Object> driver = new HashMap<>();
    AppiumDriverLocalService service=null;



    public  void setUp(String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "12");  // Replace with your desired Android version
//        capabilities.setCapability("noReset", "true");  // Replace with your desired Android version
        capabilities.setCapability("automationName", "UiAutomator2"); // Specify the automation engine here
        capabilities.setCapability("appPackage", "com.bridesandgrooms.event");
        capabilities.setCapability("appActivity", "com.bridesandgrooms.event.ActivityContainer");
        driver.put(platform, new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities));
    }


//    https://medium.com/@iamfaisalkhatri/how-to-start-appium-server-programmatically-ec07292ab59#:~:text=Click%20on%20Start%20button%20in,Run%20window%20and%20hit%20Enter.&text=Next%2C%20double%20click%20on%20node_modules,js%20file.
//    Appium server is not running help
//    private AppiumDriverLocalService startAppium() {
//        AppiumDriverLocalService appiumDriverLocalService = null;
//
//        try {
//            // Initialize AppiumServiceBuilder with desired configurations
//            AppiumServiceBuilder builder = new AppiumServiceBuilder();
//            builder
//                    .usingAnyFreePort()
//                    .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
//                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
//                    .withArgument(GeneralServerFlag.RELAXED_SECURITY);
//
//            // Build the AppiumDriverLocalService
//            appiumDriverLocalService = builder.build();
//
//            // Check if the service is running, and start it if it's not
//            if (!appiumDriverLocalService.isRunning()) {
//                appiumDriverLocalService.start();
//                appiumDriverLocalService.clearOutPutStreams();
//                System.out.println("Appium Service Started");
//            }
//
//            // Get the URL of the Appium service
//            String serverUrl = appiumDriverLocalService.getUrl().toString();
//            System.out.println("Appium server started at: " + serverUrl);
//
//            // Perform your tests or actions here...
//
//        } catch (Exception e) {
//            System.out.println("Exception while starting Appium server: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // Make sure to stop the Appium service when done
//            if (appiumDriverLocalService != null && appiumDriverLocalService.isRunning()) {
//                appiumDriverLocalService.stop();
//                System.out.println("Appium Service Stopped");
//            }
//        }
//        return appiumDriverLocalService;
//    }

    public AppiumDriver getDriver() {
        return (AppiumDriver) driver.get("android");
    }


//    public void startAppiumService() {
//        int appiumServerPort = 4723;
//
//        // Suppress Appium logs
//        suppressAppiumLogs();
//
//        // Initialize the AppiumServiceBuilder with the desired configurations
//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.usingPort(appiumServerPort);
//
//        // Specify a file path to redirect the logs
//        builder.withLogFile(new File("appium_server.log"));
//
//        // Build the AppiumDriverLocalService
//        service = AppiumDriverLocalService.buildService(builder);
//
//        // Start the Appium service
//        service.start();
//
//        // Check if the service is running
//        if (service.isRunning()) {
//            System.out.println("Appium server started at: " + service.getUrl());
//        } else {
//            System.out.println("Failed to start the Appium server.");
//        }
//    }
//
//    private void suppressAppiumLogs() {
//        // Suppress Appium logs
//        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
//
//        // Suppress other unnecessary logs
//        Logger.getLogger("").setLevel(Level.OFF);
//    }
//    public void stopAppiumService(){
//        service.stop();
//        System.out.println("Appium server stopped.");
//
//    }



}