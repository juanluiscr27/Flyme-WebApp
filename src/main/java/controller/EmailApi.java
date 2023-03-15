package controller;

import repository.UserDAO;
import repository.UserRepository;
import service.UserService;
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
@WebServlet("/api/v1/emails")
public class EmailApi extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String search = request.getParameter("search");

        UserRepository userRepo = new UserDAO();
        UserService userService = new UserService(userRepo);
        List<String> allEmails = userService.findAllEmails(search);

        String emailsListJSON = Json.parseJson(allEmails);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print(emailsListJSON);
        out.flush();

    }
}
