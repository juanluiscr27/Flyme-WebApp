package repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {

    @Test
    public void testReadConfigPropertiesFile() {
        //Create an input stream to read the properties file
        String expectedUrl = null;
        String expectedUser = null;
        String expectedPassword = null;

        try (InputStream input = Files.newInputStream(
                Paths.get("src/main/resources/config.properties")
        )) {

            Properties prop = new Properties();

            // Load the properties file to the properties object
            prop.load(input);

            // Get the property value and print it out
            expectedUrl = prop.getProperty("mysql.db.url");
            expectedUser = prop.getProperty("mysql.db.user");
            expectedPassword = prop.getProperty("mysql.db.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        assertEquals(expectedUrl, "jdbc:mysql://localhost:3307/flyme");
        assertEquals(expectedUser, "admin");
        assertEquals(expectedPassword, "secret");
    }
}
