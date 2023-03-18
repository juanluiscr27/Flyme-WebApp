package repository;

import model.Payment;
import model.User;

import java.util.Optional;

public interface PaymentRepository {
    Payment add(Payment newPayment);

    Optional<Payment> find(Long paymentId);

    Optional<Payment> findByUser(User user);

    Payment update(Payment payment);

    void delete(Payment payment);
}
