package ro.msg.cm.configuration;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 Properties loader class
 */
@Slf4j
public class PropertiesLoader {

    public static Properties loadPropertiesFile(String resourceName) {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = null;
        try {
            input = loader.getResourceAsStream(resourceName);
            prop.load(input);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return prop;
    }
}
