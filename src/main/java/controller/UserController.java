package controller;

import model.User;
import repository.UserDAO;
import repository.UserRepository;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.time.LocalDate;

@WebServlet("/user")
public class UserController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>User Profile</h2>");
        out.close();
    }
    /**
     * Register a new user to the app
     * @param request HTTP Request
     * @param response HTTP Response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserRepository userRepo = new UserDAO();
        UserService userService = new UserService(userRepo);

        User registrationRequest = new User(
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("first-name"),
                request.getParameter("last-name"),
                LocalDate.parse(request.getParameter("date-of-birth")),
                request.getParameter("nationality"),
                request.getParameter("gender").charAt(0),
                request.getParameter("phone-number")
        );

        System.out.println("Registration Request:\n\t" + registrationRequest);

        User registerdUser = userService.register(registrationRequest);
        System.out.println("Registration Response:\n\t" + registerdUser);

        /* If registration OK, go to Login page */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/login.html");
        requestDispatcher.forward(request, response);
    }
}
