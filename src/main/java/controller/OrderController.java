package controller;
import model.Confirmation;
import model.Reservation;
import repository.OrderDAO;
import repository.OrderRepository;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Reservation reservation = (Reservation) session.getAttribute("reservation");

        OrderRepository orderRepo = new OrderDAO();
        OrderService orderService = new OrderService(orderRepo);

        Confirmation confirmation = orderService.book(reservation);
        request.setAttribute("confirmation", confirmation);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.CONFIRMATION.path);
        requestDispatcher.forward(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
        requestDispatcher.forward(request, response);
    }
}
