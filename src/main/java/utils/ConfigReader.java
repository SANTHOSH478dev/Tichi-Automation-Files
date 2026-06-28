package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
        try {
            FileInputStream file = new FileInputStream(
                    "src/test/resources/config.properties"
            );

            prop = new Properties();
            prop.load(file);

        } catch (Exception e) {
            throw new RuntimeException("Config file not found");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}