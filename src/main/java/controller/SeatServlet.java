package controller;

import model.AirportDTO;
import model.Coordinate;
import model.CountryDTO;
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
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/seats")
public class SeatServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        Reservation reservation = (Reservation) session.getAttribute("reservation");
        // TODO: Get the flight instance from the reservation

        String passengersJSON = request.getParameter("passengers");

        FlightRepository flightRepo = new FlightDAO();
        FlightService flightService = new FlightService(flightRepo);

        // List<SeatDTO> flightSeats = flightService.findAllFlightSeats(flight);

        PassengerRequest[] passengers = Json.toObject(passengersJSON, PassengerRequest[].class);

        // TODO: Add the passengers to the reservation object
        session.setAttribute("reservation", reservation);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.SEATS.path);
        requestDispatcher.forward(request, response);
    }
}
