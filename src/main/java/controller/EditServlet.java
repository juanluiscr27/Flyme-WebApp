package controller;

import model.User;
import model.UserDTO;
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

@WebServlet("/update")
public class EditServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Update user password
	 * 
	 * @param request  HTTP Request
	 * @param response HTTP Response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		User user = new User( (UserDTO) session.getAttribute("user") );
		
		user.setPassword(password);

		UserRepository userRepo = new UserDAO();
		UserService userService = new UserService(userRepo);

		UserDTO updatedUser = userService.updatePassword(user);
		session.setAttribute("user", updatedUser);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
		requestDispatcher.forward(request, response);
	}
}
