package repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesFileReader {
    Properties prop;

    public PropertiesFileReader(String path) {
        try (InputStream input = Files.newInputStream( Paths.get(path)) ) {

            prop = new Properties();

            // Load the properties file to the properties object
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getProperty(String property) {
        // Get the property value from the list
        return prop.getProperty(property);
    }
}
