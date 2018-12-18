import io.appium.java_client.MobileBy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.TestSuite;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName ExampleTest
 * @Description TODO
 * @Author william
 * @Date 2018/12/18 2:59 PM
 * @ModifyDate 2018/12/18 2:59 PM
 * @Version 1.0
 */
public class ExampleTest extends TestSuite {
    @BeforeTest
    public void setup(){
        timeProfilerTraceZip = new File(System.getProperty("user.dir") + "/Trace/" + getClass().getName() + "TimeProfilerTrace.zip");
        activityMonitorTraceZip = new File(System.getProperty("user.dir") + "/Trace/" + getClass().getName() + "ActivityMonitorTrace.zip");
        allocationsTraceZip = new File(System.getProperty("user.dir") + "/Trace/" + getClass().getName() + "AllocationsTrace.zip");
        energyLogTraceZip = new File(System.getProperty("user.dir") + "/Trace/" + getClass().getName() + "EnergyLogTrace.zip");
        leaksTraceZip = new File(System.getProperty("user.dir") + "/Trace/" + getClass().getName() + "LeaksTrace.zip");
        networkTraceZip = new File(System.getProperty("user.dir") + "/Trace/" + getClass().getName() + "NetworkTrace.zip");
        coreAnimationTraceZip = new File(System.getProperty("user.dir") + "/Trace/" + getClass().getName() + "CoreAnimationTrace.zip");

        au.iOSStartPerfRecord(ACTIVITYMONITOR);
        au.iOSStartPerfRecord(TIMEPROFILER);
        au.iOSStartPerfRecord(COREANIMATION);
        au.iOSStartPerfRecord(NETWORK);
        au.iOSStartPerfRecord(ALLOCATIONS);
        au.iOSStartPerfRecord(ENERGYLOG);
        au.iOSStartPerfRecord(LEAKS);

    }
    @Test
    public void homeTest() throws InterruptedException, IOException {
        //4.1.1版本的简书
        au.waitForElementPresent(MobileBy.AccessibilityId(homePage.skip));
        au.click(MobileBy.AccessibilityId(homePage.skip));
        au.swipePage("up");
        au.swipePage("up");
        au.click(MobileBy.xpath(homePage.writeString));
        au.waitForElementPresent(MobileBy.AccessibilityId(homePage.cancle));
        au.click(MobileBy.AccessibilityId(homePage.cancle));

    }

    @AfterTest
    public void turndown() throws IOException {
        au.iOSStopPerfRecord(ACTIVITYMONITOR, activityMonitorTraceZip);
        au.iOSStopPerfRecord(TIMEPROFILER, timeProfilerTraceZip);
        au.iOSStopPerfRecord(COREANIMATION, coreAnimationTraceZip);
        au.iOSStopPerfRecord(NETWORK, networkTraceZip);
        au.iOSStopPerfRecord(ALLOCATIONS, allocationsTraceZip);
        au.iOSStopPerfRecord(ENERGYLOG, energyLogTraceZip);
        au.iOSStopPerfRecord(LEAKS, leaksTraceZip);

        au.driver.quit();

    }

}