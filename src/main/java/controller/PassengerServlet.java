package controller;

import model.Flight;
import model.Reservation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/passengers")
public class PassengerServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Flight[] allFlights = (Flight[]) session.getAttribute("allFlights");

        long flightId = Long.parseLong(request.getParameter("flightGroup"));
        Flight selectedFlight = null;

        for (Flight flight: allFlights) {
            if (flight.getId() == flightId){
                selectedFlight = flight;
                break;
            }
        }
        if (selectedFlight != null) {
            Reservation reservation = new Reservation(selectedFlight);
            session.setAttribute("reservation", reservation);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PASSENGERS.path);
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.FLIGHT.path);
            requestDispatcher.forward(request, response);
        }
    }
}
