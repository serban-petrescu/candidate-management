package ro.msg.cm.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 Properties loader class
 */
public class PropertiesLoader {

    public static Properties loadPropertiesFile(String resourceName) {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = null;
        try {
            input = loader.getResourceAsStream(resourceName);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
