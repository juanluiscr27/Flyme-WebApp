package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Forwards the request to the Sign-Up HTML page
     * @param request HTTP Request
     * @param response HTTP Response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/signup.html");
        requestDispatcher.forward(request, response);
    }
}