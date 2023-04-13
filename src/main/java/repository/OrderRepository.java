package repository;

import model.Confirmation;
import model.Order;
import model.Reservation;
import model.UserDTO;

import java.util.Map;
import java.util.Optional;

public interface OrderRepository {
    Confirmation add(Reservation reservation);

    Optional<Order> find(Long orderId);

    Map<Long, Order> findAllByUser(UserDTO user);

    void delete(Order order);
}
