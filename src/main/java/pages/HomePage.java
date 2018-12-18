package pages;

import io.appium.java_client.ios.IOSDriver;

/**
 * @ClassName HomePage
 * @Description TODO
 * @Author william
 * @Date 2018/12/18 2:50 PM
 * @ModifyDate 2018/12/18 2:50 PM
 * @Version 1.0
 */
public class HomePage extends PageBase{
    public IOSDriver driver;
    public HomePage(IOSDriver driver){
        this.driver = driver;
    }
    //Xpath
    public String Search = "//XCUIElementTypeNavigationBar[@name=\"TBCTabBar\"]/XCUIElementTypeButton";
    public String flow = "//XCUIElementTypeNavigationBar[@name=\"TBCTabBar\"]/XCUIElementTypeButton[@name=\"关注\"]";
    public String find = "//XCUIElementTypeNavigationBar[@name=\"TBCTabBar\"]/XCUIElementTypeButton[@name=\"发现\"]";
    public String writeString = "//XCUIElementTypeApplication[@name=\"简书\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeTabBar/XCUIElementTypeButton[3]";

    //ID
    public String skip = "下次再说";
    public String writeButton = "icon tabbar write";
    public String cancle = "icon chosepic cancel";
}