package repository;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesFileReaderTest {
    @Test
    public void testReadConfigPropertiesFile() {
        //Create an input stream to read the properties file
        String actualUrl;
        String actualUser;
        String actualPassword;

        PropertiesFileReader propReader = new PropertiesFileReader("mysql.db", "config.properties");

        actualUrl = propReader.getProperty("url");
        actualUser = propReader.getProperty("user");
        actualPassword = propReader.getProperty("password");
        assertEquals("jdbc:mysql://localhost:3307/flyme", actualUrl);
        assertEquals("admin", actualUser);
        assertEquals("secret", actualPassword);
    }
}