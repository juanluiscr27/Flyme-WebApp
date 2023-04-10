package controller;

import model.Payment;
import model.User;
import model.UserDTO;
import repository.PaymentDAO;
import repository.PaymentRepository;
import service.PaymentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        PaymentRepository paymentRepo = new PaymentDAO();
        PaymentService paymentService = new PaymentService(paymentRepo);

        try {
            Payment payment = paymentService.findByUser(user);
            System.out.println("Payment found");
            request.setAttribute("payment", payment);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.MYPAYMENTS.path);
            requestDispatcher.forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        PaymentRepository paymentRepo = new PaymentDAO();
        PaymentService paymentService = new PaymentService(paymentRepo);

        Payment newPayment = new Payment(
                request.getParameter("card-number"),
                request.getParameter("name-on-card"),
                LocalDate.parse(request.getParameter("expiry-date")),
                Integer.parseInt(request.getParameter("security-code")),
                user.id()
        );

        Payment payment = paymentService.add(newPayment);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
        requestDispatcher.forward(request, response);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        PaymentRepository paymentRepo = new PaymentDAO();
        PaymentService paymentService = new PaymentService(paymentRepo);

        Payment newPayment = new Payment(
                Integer.parseInt(request.getParameter("payment-id")),
                request.getParameter("card-number"),
                request.getParameter("name-on-card"),
                LocalDate.parse(request.getParameter("expiry-date")),
                Integer.parseInt(request.getParameter("security-code")),
                user.id()
        );

        Payment payment = paymentService.update(newPayment);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
        requestDispatcher.forward(request, response);
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        PaymentRepository paymentRepo = new PaymentDAO();
        PaymentService paymentService = new PaymentService(paymentRepo);

        paymentService.delete(user);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
        requestDispatcher.forward(request, response);
    }
}
