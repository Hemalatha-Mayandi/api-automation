package org.example.utils;

import java.io.InputStream;
import java.util.Properties;
import lombok.SneakyThrows;

public class PropertyUtils {

    private Properties properties;

    @SneakyThrows
    public PropertyUtils(String propertyFileName) {
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream(propertyFileName);
        this.properties = new Properties();
        this.properties.load(is);
    }

    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
}
