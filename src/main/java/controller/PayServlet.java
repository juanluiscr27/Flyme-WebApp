package controller;

import model.Payment;
import model.UserDTO;
import repository.PaymentDAO;
import repository.PaymentRepository;
import repository.UserDAO;
import repository.UserRepository;
import service.PaymentService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        UserRepository userRepo = new UserDAO();
        UserService userService = new UserService(userRepo);

        PaymentRepository paymentRepo = new PaymentDAO();
        PaymentService paymentService = new PaymentService(paymentRepo);

        try {
            UserDTO user = userService.find(username);
            Payment payment = paymentService.findByUser(user);
            request.setAttribute("payment", payment);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            request.setAttribute("message", "No payment found");

        } finally {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PAY.path);
            requestDispatcher.forward(request, response);
        }
    }
}
