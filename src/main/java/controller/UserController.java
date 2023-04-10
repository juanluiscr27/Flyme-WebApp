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

@WebServlet("/user")
public class UserController extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		UserRepository userRepo = new UserDAO();
		UserService userService = new UserService(userRepo);

		try {
			if (!username.equals("")) {
				UserDTO user = userService.find(username);
				session.setAttribute("user", user);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.PROFILE.path);
				requestDispatcher.forward(request, response);
			} else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.LOGIN.path);
				requestDispatcher.forward(request, response);
			}

		} catch (IllegalArgumentException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.LOGIN.path);
			requestDispatcher.forward(request, response);
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Register a new user to the app
	 * 
	 * @param request  HTTP Request
	 * @param response HTTP Response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserRepository userRepo = new UserDAO();
		UserService userService = new UserService(userRepo);

		User registrationRequest = new User(request.getParameter("first-name"), request.getParameter("last-name"),
				request.getParameter("email"), request.getParameter("password"),
				LocalDate.parse(request.getParameter("date-of-birth")), request.getParameter("nationality"),
				request.getParameter("gender").charAt(0), request.getParameter("phone-number"), 0);

		UserDTO registeredUser = userService.register(registrationRequest);

		/* If registration OK, go to Login page */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.LOGIN.path);
		requestDispatcher.forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");

		UserRepository userRepo = new UserDAO();
		UserService userService = new UserService(userRepo);

		userService.delete(user);

		session.invalidate();

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(StaticPage.HOME.path);
		requestDispatcher.forward(request, response);
	}
}
