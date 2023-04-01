package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.Coordinate;
import model.Flight;
import model.Reservation;
import util.Json;

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
        Reservation reservation = (Reservation) session.getAttribute("reservation");
        Flight[] allFlights = (Flight[]) session.getAttribute("allFlights");

        // TODO: Add the flight to the reservation object
        session.setAttribute("reservation", reservation);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PASSENGERS.path);
        requestDispatcher.forward(request, response);
    }
}
