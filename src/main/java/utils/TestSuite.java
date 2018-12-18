package utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName TestSuite
 * @Description TODO
 * @Author william
 * @Date 2018/12/18 2:55 PM
 * @ModifyDate 2018/12/18 2:55 PM
 * @Version 1.0
 */
public class TestSuite {
    public IOSDriver driver;
    public Action au;

    public Yml yml;
    public HomePage homePage;

    public File timeProfilerTraceZip;
    public File activityMonitorTraceZip;
    public File coreAnimationTraceZip;
    public File allocationsTraceZip;
    public File energyLogTraceZip;
    public File leaksTraceZip;
    public File networkTraceZip;

    public static final String ACTIVITYMONITOR = "Activity Monitor";
    public static final String TIMEPROFILER = "Time Profiler";
    public static final String NETWORK = "Network";
    public static final String COREANIMATION = "Core Animation";
    public static final String ALLOCATIONS = "Allocations";
    public static final String ENERGYLOG = "Energy Log";
    public static final String LEAKS = "Leaks";


    @BeforeSuite
    public void iOSSettings()throws MalformedURLException, IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.1");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("deviceName", "iPhone7");
        capabilities.setCapability("bundleId","com.jianshu.Hugo");
        capabilities.setCapability("udid","your udid");
        //capabilities.setCapability("app", yml.readYML("app"));
        //capabilities.setCapability("app", System.getProperty("user.dir") + "/app/*.ipa");

        IOSDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        au = new Action(driver);

        homePage = new HomePage(driver);

    }


    @AfterSuite
    public void collectTrace() throws InterruptedException {

    }
}