package service;

import model.Confirmation;
import model.Order;
import model.Payment;
import model.Reservation;
import model.UserDTO;
import repository.OrderDAO;
import repository.OrderRepository;
import repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Confirmation book(Reservation reservation) {
        return orderRepo.add(reservation);
    }

    public Order findById(Long orderId) throws IllegalArgumentException {
        Optional<Order> optionalOrder= orderRepo.find(orderId);
        return optionalOrder.orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public List<Order> findAllByUser(UserDTO user) throws IllegalArgumentException {
        Map<Long,Order> userOrders = orderRepo.findAllByUser(user);
        return new ArrayList<>(userOrders.values());
    }

    public void delete(Long orderId) {
        try {
            Order order = findById(orderId);
            orderRepo.delete(order);
        } catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }
    }
}
