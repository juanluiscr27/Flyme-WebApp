
import controller.EmailApiTest;
import model.ReceiptTest;
import repository.DatabaseConnectionPoolTest;
import repository.FlightDAOTest;
import repository.OrderDAOTest;
import repository.PropertiesFileReaderTest;
import repository.UserDAOTest;
import util.GeodesyTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PropertiesFileReaderTest.class,
        DatabaseConnectionPoolTest.class,
        UserDAOTest.class,
        FlightDAOTest.class,
        GeodesyTest.class,
        EmailApiTest.class,
        ReceiptTest.class,
        OrderDAOTest.class
})

public class TestSuite {
}
