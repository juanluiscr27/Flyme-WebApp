package controller;

import model.BagFareDTO;
import model.DistanceFareDTO;
import model.PassengerRequest;
import model.Receipt;
import model.Reservation;
import model.User;
import model.UserDTO;
import repository.FlightDAO;
import repository.FlightRepository;
import repository.UserDAO;
import repository.UserRepository;
import service.FlightService;
import service.UserService;
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

        FlightRepository flightRepo = new FlightDAO();
        FlightService flightService = new FlightService(flightRepo);

        HttpSession session = request.getSession();

        Reservation reservation = (Reservation) session.getAttribute("reservation");

        String passengersJSON = request.getParameter("passengers");
        PassengerRequest[] passengers = Json.toObject(passengersJSON, PassengerRequest[].class);
        reservation.setFlightPassengers(passengers);

        UserRepository userRepo = new UserDAO();
        UserService userService = new UserService(userRepo);
        String username = (String) session.getAttribute("username");
        UserDTO user = userService.find(username);
        reservation.setUser(new User(user));

        BagFareDTO[] bagFares = flightService.findAllBagFares();
        DistanceFareDTO[] distanceFares = flightService.findAllDistanceFares();
        Receipt receipt = new Receipt(bagFares, distanceFares);
        reservation.setReceipt(receipt);

        session.setAttribute("reservation", reservation);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.SUMMARY.path);
        requestDispatcher.forward(request, response);
    }
}
