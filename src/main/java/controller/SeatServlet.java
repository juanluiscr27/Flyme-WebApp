package controller;

import model.Flight;
import model.PassengerRequest;
import model.Reservation;
import model.SeatDTO;
import repository.FlightDAO;
import repository.FlightRepository;
import service.FlightService;
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

@WebServlet("/seats")
public class SeatServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        Reservation reservation = (Reservation) session.getAttribute("reservation");

        String passengersJSON = request.getParameter("passengers");
        PassengerRequest[] passengers = Json.toObject(passengersJSON, PassengerRequest[].class);

        try {
            Flight flight = reservation.getFlight();

            FlightRepository flightRepo = new FlightDAO();
            FlightService flightService = new FlightService(flightRepo);

            SeatDTO[] flightSeats = flightService.findAllFlightSeats(flight);

            reservation.setFlightSeats(flightSeats);
            request.setAttribute("passengers-seats", passengersJSON);
            session.setAttribute("passengers", passengers);
            session.setAttribute("reservation", reservation);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.SEATS.path);
            requestDispatcher.forward(request, response);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.SEARCH.path);
            requestDispatcher.forward(request, response);
        }
    }
}
