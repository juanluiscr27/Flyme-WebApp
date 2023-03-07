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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.LOGIN.path);
        requestDispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("username");
        String password = request.getParameter("password");

        UserRepository userRepo = new UserDAO();
        UserService userService = new UserService(userRepo);

        try {
            User authenticatedUser = userService.login(email, password);

            HttpSession session = request.getSession();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.SEARCH.path);
            requestDispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.LOGIN.path);
            requestDispatcher.forward(request, response);
            System.out.println(e.getMessage());
        }
    }
}