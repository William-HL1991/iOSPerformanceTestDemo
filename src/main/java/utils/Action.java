package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Action
 * @Description 基于ios gestures封装而来，各种命令见http://appium.io/docs/en/commands/mobile-command/#execute-mobile-command
 * @Author william
 * @Date 2018/12/18 2:52 PM
 * @ModifyDate 2018/12/18 2:52 PM
 * @Version 1.0
 */
public class Action {
    public IOSDriver driver;
    private int timeout = 20;
    private WebDriverWait wait;

    public Action(IOSDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
    }

    /**
     * 元素的点击操作
     * @param by
     *           需要点击的元素
     */
    public void click(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e1){
                e1.printStackTrace();
            }
            driver.findElement(by).click();
        }
    }

    /**
     *  @param :direct
     *               'up', 'down', 'left' or 'right'. 这4个参数是固定的
     */
    public void swipePage(String direct){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direct);
        driver.executeScript("mobile: swipe", params);
    }

    /**
     * iOS swipe by ios gestures
     * @param sx
     * @param sy
     * @param ex
     * @param ey
     * @param duration
     */
    public void swipe(int sx, int sy, int ex, int ey , Double duration){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Map<String, Object> params = new HashMap<>();
        params.put("duration", duration);
        params.put("fromX", sx);
        params.put("fromY", sy);
        params.put("toX", ex);
        params.put("toY", ey);
        driver.executeScript("mobile: dragFromToForDuration", params);
    }

    /**
     *
     * @param bundleId
     *              操作app的bundleId
     */
    public void lunchApp(String bundleId){
        Map<String, Object> bundleArgs = new HashMap<>();
        bundleArgs.put("direction", bundleId);
        driver.executeScript("mobile: launchApp", bundleArgs);
    }

    /**
     *
     * @param bundleId
     *              操作app的bundleId
     */
    public void terminateApp(String bundleId){
        Map<String, Object> bundleArgs = new HashMap<>();
        bundleArgs.put("direction", bundleId);
        driver.executeScript("mobile: terminateApp", bundleArgs);
    }

    // TODO
    /**
     * 具体参见https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/ios/ios-xctest-mobile-apps-management.md
     * mobile: queryAppState
     * mobile: activateApp
     * mobile: isAppInstalled
     * mobile: removeApp
     * mobile: installApp
     * /


     /**
     *
     * @param endElement
     *                swipe to this element
     * @param up
     *          direction true or false
     */
    public void move(By endElement,boolean up) {
        int x = driver.manage().window().getSize().width;
        int y = driver.manage().window().getSize().height;

        PointOption start = PointOption.point(x/2, y*3/5 );
        PointOption end = PointOption.point(x/2, y*2/5);

        TouchAction action = new TouchAction(driver);
        while (true) {
            try {
                driver.findElement(endElement);
                break;
            } catch (Exception e){
                if (up){
                    action.press(start);
                } else {
                    action.press(end);
                }
                action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
                if (up){
                    action.moveTo(end);
                } else {
                    action.moveTo(start);
                }
                action.perform();
            }
        }
    }

    /**
     *
     * @param x int coordinate
     * @param y int coordinate
     *          tap coordinate
     */
    public void tapByCoordinate(int x, int y){
        TouchAction action = new TouchAction<>(driver);
        action.press(PointOption.point(x, y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)));
        action.release();
        action.perform();
    }

    /**
     *
     * @param alertContent
     *                  alert display
     */
    public void iOSAlertPresented(String alertContent){
        wait.until(ExpectedConditions.alertIsPresent());
        HashMap<String, String> args = new HashMap<>();
        args.put("action", "getButtons");
        List<String> buttons = (List<String>)driver.executeScript("mobile: alert", args);

        // find the text of the button which is alertContent
        String buttonLabel = null;
        for (String button : buttons) {
            if (button.equals(alertContent) || button.contains(alertContent) || button.contentEquals(alertContent)) {
                buttonLabel = button;
            }
        }

        if (buttonLabel == null) {
            throw new Error("Did not get a third alert button as we were expecting");
        }

        args.put("action", "accept");
        args.put("buttonLabel", buttonLabel);
        driver.executeScript("mobile: alert", args);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     *
     * @param by WebElement
     * @param value String
     *              向控件输入内容
     */
    public void setValue(By by,String value){
        this.click(by);
        driver.findElement(by).sendKeys(value);
    }

    /**
     * android refresh
     */
    public void  refresh(){
        driver.navigate().refresh();
    }


    /**
     *
     * @param by WebElement
     * @return element display status
     *                  控件是否展示
     */
    public boolean isElementPresented(By by){
        boolean isDisplay = false;
        try{
            isDisplay = driver.findElement(by).isDisplayed();
        }catch (Exception e){
            isDisplay =false;
        }
        return isDisplay;
    }

    /**
     *
     * @param timeout
     *              sleep time
     */
    public void waitFor(long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    /**
     *
     * @param by
     *      wait webElement load
     */
    public void waitForElementPresent(By by){
        try{
            (new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(by));
        }catch(Exception e){

        }

    }

    /**
     * 参见https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/ios/ios-xctest-performance.md
     * @param profile
     *              profile :Activity Monitor or Time Profiler
     */
    public void iOSStartPerfRecord(String  profile){
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("timeout", 60000);
        args.put("pid", "current");
        args.put("profileName", profile);
        driver.executeScript("mobile: startPerfRecord", args);
    }

    /**
     * @param proflie
     * @param fileName
     */
    public void iOSStopPerfRecord(String proflie, File fileName) throws IOException {
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("timeout", 60000);
        args.put("pid", "current");
        args.put("profileName", proflie);
        String b64Zip = (String)driver.executeScript("mobile: stopPerfRecord", args);
        byte[] bytesZip = Base64.getMimeDecoder().decode(b64Zip);
        FileOutputStream stream = new FileOutputStream(fileName);
        stream.write(bytesZip);
    }
}