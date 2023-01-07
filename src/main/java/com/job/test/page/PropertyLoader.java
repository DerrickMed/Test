package com.job.test.page;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private final Properties properties;

    private PropertyLoader(){
        properties = new Properties();
        try (InputStream propFileInpStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(propFileInpStream);
        } catch (IOException e) {
            throw new RuntimeException("Bad configuration file!");
        }
    }

    private static class LazyHolder {
        static final PropertyLoader INSTANCE = new PropertyLoader();
    }


    public static String returnConfigValue(final String property) {
        return LazyHolder.INSTANCE.properties.getProperty(property);
    }
}
