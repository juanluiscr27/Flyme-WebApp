package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnectionPool {
    private static BasicDataSource dataSource = null;

    public synchronized static Connection getConnection() {
        Connection connection = null;

        try {
            if (dataSource == null) {
                PropertiesFileReader propReader = new PropertiesFileReader("mysql.db","config.properties");

                dataSource = new BasicDataSource();
                dataSource.setDriverClassName(propReader.getProperty("pool.driver"));
                dataSource.setUrl(propReader.getProperty("url"));
                dataSource.setUsername(propReader.getProperty("user"));
                dataSource.setPassword(propReader.getProperty("password"));
                // The initial number of connections that are created when the pool is started.
                dataSource.setInitialSize(Integer.parseInt(propReader.getProperty("pool.initsize")));
                // The maximum number of active connections that can be allocated from this pool
                dataSource.setMaxTotal(Integer.parseInt(propReader.getProperty("pool.maxsize")));
                dataSource.setMaxWaitMillis(3000L);
            }
            connection = dataSource.getConnection();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    public static void close(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void close(PreparedStatement statement){
        try {
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void rollback(Connection connection){
        try {
            connection.rollback();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

