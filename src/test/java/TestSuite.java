
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repository.DatabaseConnectionPoolTest;
import repository.PropertiesFileReaderTest;
import repository.UserDAOTest;
import util.GeodesyTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PropertiesFileReaderTest.class,
        DatabaseConnectionPoolTest.class,
        UserDAOTest.class,
        GeodesyTest.class,
})

public class TestSuite {
}
