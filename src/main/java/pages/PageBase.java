package pages;

/**
 * @ClassName PageBase
 * @Description TODO
 * @Author william
 * @Date 2018/12/18 2:51 PM
 * @ModifyDate 2018/12/18 2:51 PM
 * @Version 1.0
 */
public class PageBase {
    public String getElement(String originalStr, String newValue){
        return String.format(originalStr,newValue);
    }
}