package controller;

import model.EmailDTO;
import org.junit.Test;
import util.Json;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class EmailApiTest {

    @Test
    public void testIsEmailAvailableTrue() {
        String path = "http://127.0.0.1:8080/FlyMeWebApp/api/v1/emails";
        String parameter = "search";
        String email = "juan@email.com";

        String url = path + '?' + parameter + '=' + email;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            EmailDTO emailDTO = Json.toObject(response.body(), EmailDTO.class);

            assertFalse(emailDTO.isAvailable());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testIsEmailAvailableFalse() {
        String path = "http://127.0.0.1:8080/FlyMeWebApp/api/v1/emails";
        String parameter = "search";
        String email = "notvalid@email.com";

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
}