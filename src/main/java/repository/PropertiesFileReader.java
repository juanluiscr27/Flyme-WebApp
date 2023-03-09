package repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
    Properties prop;
    String databaseServer;

    public PropertiesFileReader(String databaseServer, String path) {
        this.databaseServer = databaseServer;
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(path)) {

            prop = new Properties();

            // Load the properties file to the properties object
            prop.load(input);

        } catch (IOException e) {
            System.out.println("Cannot load properties file  '" + path + "'\n" + e.getMessage());
        }
    }

    public String getProperty(String propertyKey) {
        String value = null;
        try {
            // Get the property value from the list
            value = prop.getProperty(databaseServer + '.' + propertyKey);
        } catch (NullPointerException e) {
            System.out.println("Cannot retrieved property '" + propertyKey + "' from file");
        }
        return value;
    }
}
