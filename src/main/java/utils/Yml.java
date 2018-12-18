package utils;

import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Yml
 * @Description TODO
 * @Author william
 * @Date 2018/12/18 2:56 PM
 * @ModifyDate 2018/12/18 2:56 PM
 * @Version 1.0
 */
public class Yml {
    public String readYML(String key) throws FileNotFoundException {
        File configFile = new File(System.getProperty("user.dir") + "/src/main/resources/config.yml");
        Map<String, String> configMap = Yaml.loadType(configFile, HashMap.class);
        return configMap.get(key);
    }

//    public static void main(String[] args) throws FileNotFoundException {
//        Yml yml = new Yml();
//         String app = yml.readYML("app");
//         System.out.print(app);
//    }
}