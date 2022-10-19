package Controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Api.MailOperator;
import Api.StringAPI;
import Api.UserOperator;
import Dao.UserDAO;
import Model.User;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/client/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDAO userDAO = new UserDAO();
		String action = request.getParameter("action");
		if (action != null && action.equals("resetpassword")) {

			String email = request.getParameter("resetPasswordEmail");
			System.out.println("email :" + email);
			User u = userDAO.findByEmail(email);
			if (u == null) {
				request.setAttribute("message", "Reset password Email is incorect!");
			} else {
				String newPassword = StringAPI.generateRandomPassword(8);
				boolean result = userDAO.updatePassword(u.getUsername(), newPassword);
				if (!result) {
					request.setAttribute("message", "Update password fail!");
				} else {
					UserOperator.sendPasswordResetMail(u, newPassword);
					request.setAttribute("message", "New password has been sent to your email");
				}
			}
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		}
		// if no action
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + "|" + password);

		try {
			HashMap<String, Object> validateResults = userDAO.validate(username, password);
			boolean isValidate = (boolean) validateResults.get("isValidate");
			if (!isValidate) {
				String message = (String) validateResults.get("message");
				request.setAttribute("message", message);
				request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			} else {
				User user = (User) validateResults.get("user");
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				String rememberMe[] = request.getParameterValues("rememberme");
				Cookie ckUser, ckPassword;

				// check rememberme
				if (rememberMe != null) {
					System.out.println("cookie");
					ckUser = new Cookie("ckUser", username);
					ckUser.setMaxAge(5000);
					ckPassword = new Cookie("ckPassword", password);
					response.addCookie(ckUser);
					response.addCookie(ckPassword);
				} else {
					System.out.println("nocookie");
					ckUser = new Cookie("ckUser", "");
					ckUser.setMaxAge(0);
					response.addCookie(ckUser);
					ckPassword = new Cookie("ckPassword", "");
					ckPassword.setMaxAge(0);
					response.addCookie(ckPassword);

				}
				// check role to redirect
				if (user.getRole() == 1) {
					response.sendRedirect(request.getContextPath() + "/admin/home/");
					return;
				} else {
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

}
