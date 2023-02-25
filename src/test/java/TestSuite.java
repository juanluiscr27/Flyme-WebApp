
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repository.DatabaseConnectionPoolTest;
import repository.PropertiesFileReaderTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PropertiesFileReaderTest.class,
        DatabaseConnectionPoolTest.class,
})

public class TestSuite {
}
