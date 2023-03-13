package repository;

import model.Payment;
import model.User;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment add(Payment newPayment, User user);

    Optional<Payment> find(Long paymentId);

    Optional<User> findByUser(User user);

    Payment update(Payment payment);

    void delete(Payment payment);
}
