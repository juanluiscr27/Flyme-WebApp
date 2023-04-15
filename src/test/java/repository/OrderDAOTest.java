package repository;

import model.CountryDTO;
import model.Order;
import model.UserDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class OrderDAOTest {
    OrderRepository orderRepo;
    @Before
    public void setUpOrderDAOTest() {

        orderRepo = new OrderDAO();
    }
    @Test //@Ignore("Test setup not fully implemented yet")
    public void testFindOrderById() {
        // TODO: Insert custom order
        long expectedOrderId = 2;

        Optional<Order> actualOrder= orderRepo.find(expectedOrderId);
        // Assert the optional contains an order
        assertTrue(actualOrder.isPresent());

    }

    @Test //@Ignore("Test setup not fully implemented yet")
    public void testFindAllOrdersByUser() {
        // TODO: Create user and insert custom orders

        UserDTO user = new UserDTO(
                21L,
                "John",
                "Doe",
                "john.doe@email.com",
                "zXcVbNm23",
                LocalDate.parse("1995-07-24"),
                new CountryDTO("CA", "Canada"),
                "Male",
                "4234567890",
                0
        );

        int expectedSize = 2;
        Map<Long,Order> orders = orderRepo.findAllByUser(user);

        assertEquals(expectedSize, orders.size());
    }
}