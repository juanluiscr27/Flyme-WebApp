package controller;

import model.CountryDTO;
import repository.FlightDAO;
import repository.FlightRepository;
import service.FlightService;
import util.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.List;

@WebServlet("/api/v1/countries")
public class CountryApi extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String search = request.getParameter("search");

        FlightRepository flightRepo = new FlightDAO();
        FlightService flightService = new FlightService(flightRepo);
        List<CountryDTO> countries = flightService.findAllCountries(search);

        String countriesListJSON = Json.parseJson(countries);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print(countriesListJSON);
        out.flush();
    }
}
