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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;

@WebServlet("/user")
public class UserController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getAttribute("username");

        UserRepository userRepo = new UserDAO();
        UserService userService = new UserService(userRepo);

        try {
            User user = userService.find(username);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
            requestDispatcher.forward(request, response);

        } catch (IllegalArgumentException e) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.LOGIN.path);
            requestDispatcher.forward(request, response);
            System.out.println(e.getMessage());
        }
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
                request.getParameter("first-name"),
                request.getParameter("last-name"),
                request.getParameter("email"),
                request.getParameter("password"),
                LocalDate.parse(request.getParameter("date-of-birth")),
                request.getParameter("nationality"),
                request.getParameter("gender").charAt(0),
                request.getParameter("phone-number"),
                0
        );

        User registerdUser = userService.register(registrationRequest);

        /* If registration OK, go to Login page */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.LOGIN.path);
        requestDispatcher.forward(request, response);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String password = request.getParameter("password");
        String phone = request.getParameter("phone-number");
        User user = (User) request.getAttribute("user");

        user.setPassword(password);
        user.setPhone(phone);

        UserRepository userRepo = new UserDAO();
        UserService userService = new UserService(userRepo);

        User updatedUser = userService.update(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", updatedUser);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
        requestDispatcher.forward(request, response);
    }
}
