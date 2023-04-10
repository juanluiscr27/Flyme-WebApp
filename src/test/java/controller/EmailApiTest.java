package controller;

import model.EmailDTO;
import model.User;
import model.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.UserDAO;
import repository.UserRepository;
import util.Json;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class EmailApiTest {
    UserRepository userRepo;
    UserDTO user;

    @Before
    public void setUpEmailApiTest() {
        userRepo = new UserDAO();
        user =  userRepo.add(new User(
                "John",
                "Doe",
                "john.doe@email.com",
                "zXcVbNm@23",
                LocalDate.parse("1995-07-24"),
                "CA",
                'M',
                "1234567890",
                8500)
        );
    }
    @Test
    public void testIsEmailAvailableFalse() {
        String path = "http://127.0.0.1:8080/FlyMeWebApp/api/v1/emails";
        String parameter = "search";
        String email = "john.doe@email.com";

        String url = path + '?' + parameter + '=' + email;

        EmailDTO emailDTO = new EmailDTO();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> emailResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            emailDTO = Json.toObject(emailResponse.body(), EmailDTO.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            assertFalse(emailDTO.isAvailable());
        }
    }

    @Test
    public void testIsEmailAvailableTrue() {
        String path = "http://127.0.0.1:8080/FlyMeWebApp/api/v1/emails";
        String parameter = "search";
        String email = "notavailable@email.com";

        String url = path + '?' + parameter + '=' + email;

        EmailDTO emailDTO = new EmailDTO("", false);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header(parameter, email)
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            emailDTO = Json.toObject(response.body(), EmailDTO.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            assertTrue(emailDTO.isAvailable());
        }
    }
    @After
    public void tearDownEmailApiTest() {
        // Delete User from the Database after test
        userRepo.delete(user);
    }
}