
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repository.DatabaseConnectionPoolTest;
import repository.PropertiesFileReaderTest;
import repository.UserDAOTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PropertiesFileReaderTest.class,
        DatabaseConnectionPoolTest.class,
        UserDAOTest.class,
})

public class TestSuite {
}
