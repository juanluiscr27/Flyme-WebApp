package controller;

import model.PassengerRequest;
import model.Reservation;
import util.Json;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/summary")
public class SummaryServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Reservation reservation = (Reservation) session.getAttribute("reservation");

        String passengersJSON = request.getParameter("passengers");
        PassengerRequest[] passengers = Json.toObject(passengersJSON, PassengerRequest[].class);
        reservation.setFlightPassengers(passengers);

        session.setAttribute("reservation", reservation);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.SUMMARY.path);
        requestDispatcher.forward(request, response);
    }
}
