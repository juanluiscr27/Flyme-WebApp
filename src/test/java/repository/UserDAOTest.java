package repository;

import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Optional;

public class UserDAOTest {
    UserRepository userRepo;
    User expectedUser;
    @Before
    public void setUpUserDAOTest() {

        userRepo = new UserDAO();
        expectedUser = new User(
                "John",
                "Doe",
                "john.doe@email.com",
                "zXcVbNm@23",
                LocalDate.parse("1995-07-24"),
                "CA",
                'M',
                "1234567890",
                8500);
    }
    @Test
    public void testAddNewUser() {
        User actualUser = userRepo.add(expectedUser);

        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        // Delete User from the Database after test
        userRepo.delete(actualUser);
    }
    @Test
    public void testDeleteNewUser() {
        // Insert User into the Database before test
        expectedUser = userRepo.add(expectedUser);
        Long expectedUserID = expectedUser.getId();

        userRepo.delete(expectedUser);
        Optional<User> actualUser = userRepo.find(expectedUserID);

        assertEquals(actualUser, Optional.empty());
    }
}