package repository;

import model.User;
import model.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Optional;

public class UserDAOTest {
    UserRepository userRepo;
    @Before
    public void setUpUserDAOTest() {

        userRepo = new UserDAO();
    }
    @Test
    public void testAddNewUser() {
        User expectedUser = new User(
                "John",
                "Doe",
                "john.doe@email.com",
                "zXcVbNm@23",
                LocalDate.parse("1995-07-24"),
                "CA",
                'M',
                "1234567890",
                8500
        );

        UserDTO actualUser = userRepo.add(expectedUser);

        assertEquals(expectedUser.getEmail(), actualUser.email());
        // Delete User from the Database after test
        userRepo.delete(actualUser);
    }

    @Test
    public void testFindUserByEmail() {
        User expectedUser = new User(
                "John",
                "Doe",
                "john.doe@email.com",
                "zXcVbNm@23",
                LocalDate.parse("1995-07-24"),
                "CA",
                'M',
                "1234567890",
                8500
        );

        userRepo.add(expectedUser);

        UserDTO actualUser = userRepo.findByEmail(
                expectedUser.getEmail()
        ).orElseThrow(()-> new RuntimeException("User not found"));

        assertEquals(expectedUser.getEmail(), actualUser.email());

        // Delete User from the Database after test
        userRepo.delete(actualUser);
    }

    @Test
    public void testDeleteNewUser() {
        User newUser = new User(
                "John",
                "Doe",
                "john.doe@email.com",
                "zXcVbNm@23",
                LocalDate.parse("1995-07-24"),
                "CA",
                'M',
                "1234567890",
                8500
        );
        // Insert User into the Database before test
        UserDTO expectedUser = userRepo.add(newUser);

        Long expectedUserID = expectedUser.id();

        userRepo.delete(expectedUser);
        Optional<UserDTO> actualUser = userRepo.find(expectedUserID);

        assertEquals(Optional.empty(), actualUser);
    }
}