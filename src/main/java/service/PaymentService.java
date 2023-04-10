package service;

import model.Payment;
import model.User;
import model.UserDTO;
import repository.PaymentRepository;

import java.util.Optional;

public class PaymentService {
    private final PaymentRepository paymentRepo;

    public PaymentService(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }
    public Payment add(Payment newPayment) {
        return paymentRepo.add(newPayment);
    }
    public Payment findByUser(UserDTO user) throws IllegalArgumentException {
        Optional<Payment> optionalPayment = paymentRepo.findByUser(user);
        return optionalPayment.orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }
    public Payment update(Payment payment) {
        return paymentRepo.update(payment);
    }

    public void delete(UserDTO user) {
        try {
            Payment userPayment = findByUser(user);
            paymentRepo.delete(userPayment);
        } catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }
    }
}
