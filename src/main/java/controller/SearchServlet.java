package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("username") != null) {

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.SEARCH.path);
            requestDispatcher.forward(request, response);
        } else {
            request.getRequestDispatcher(StaticPage.LOGIN.path).forward(request, response);
        }
    }
}
