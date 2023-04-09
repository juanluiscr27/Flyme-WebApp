package controller;

import model.Flight;
import model.FlightSearchDTO;
import model.Reservation;
import repository.FlightDAO;
import repository.FlightRepository;
import service.FlightService;

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

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FlightRepository flightRepo = new FlightDAO();
        FlightService flightService = new FlightService(flightRepo);

        FlightSearchDTO flightSearch = new FlightSearchDTO(
                request.getParameter("from"),
                request.getParameter("to"),
                LocalDate.parse(request.getParameter("departure")),
                request.getParameter("return").equals("") ? null : LocalDate.parse(request.getParameter("return")),
                Integer.parseInt(request.getParameter("passengers"))
        );

        Boolean isRoundTrip = Boolean.valueOf(request.getParameter("round-trip"));

        Flight[] allFlights = flightService.findAllOneWay(flightSearch);

        HttpSession session = request.getSession();
        session.setAttribute("allFlights", allFlights);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.FLIGHT.path);
        requestDispatcher.forward(request, response);
    }
}
