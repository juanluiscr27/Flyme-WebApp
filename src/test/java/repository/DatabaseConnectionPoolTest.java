package repository;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionPoolTest {
    Connection connection;
    Connection connection1;
    Connection connection2;
    Connection connection3;
    Connection connection4;
    Connection connection5;
    @Test
    public void testGetDatabaseConnectionFromPool() {
        connection = DatabaseConnectionPool.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testGetSeveralConnectionsFromPool() {
        connection = DatabaseConnectionPool.getConnection();
        connection1 = DatabaseConnectionPool.getConnection();
        connection2 = DatabaseConnectionPool.getConnection();

        assertNotNull(connection);
        assertNotNull(connection1);
        assertNotNull(connection2);
    }

    // @Ignore("[Test disabled] - Consumes a lot of resources")
    @Test
    public void testExhaustedConnectionPool() {
        connection = DatabaseConnectionPool.getConnection();
        connection1 = DatabaseConnectionPool.getConnection();
        connection2 = DatabaseConnectionPool.getConnection();
        connection3 = DatabaseConnectionPool.getConnection();
        connection4 = DatabaseConnectionPool.getConnection();
        connection5 = DatabaseConnectionPool.getConnection();

        assertNull(connection5);
    }
    @After
    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
            if (connection1 != null)
                connection1.close();
            if (connection2 != null)
                connection2.close();
            if (connection3 != null)
                connection3.close();
            if (connection4 != null)
                connection4.close();
            if (connection5 != null)
                connection5.close();
        } catch (SQLException e) {
            System.out.println("Error closing the database connection " + e.getMessage());
        }
    }
}