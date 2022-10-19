package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Api.StringAPI;
import Dao.UserDAO;
import Model.User;

/**
 * Servlet implementation class PasswordController
 */
public class PasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordController() {
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
		String action = request.getParameter("action");
		if (action != null && action.equals("changepassword")) {
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			if (u.getRole() == 1) {
				request.getRequestDispatcher("/admin/others/changepassword.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("/client/others/changepassword.jsp").forward(request, response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		UserDAO userDAO = new UserDAO();
		String action = request.getParameter("action");
		if (action != null && action.equals("changepassword")) {
			try {
				String username = request.getParameter("username");
				String oldPassword = request.getParameter("oldpassword");
				String newPassword = request.getParameter("newpassword");
				HttpSession session = request.getSession();
				User u = (User) session.getAttribute("user");
				if (!u.getPassword().equals(StringAPI.encodePassword(oldPassword))) {
					System.out.println("old password not same user password");
					request.setAttribute("message", "old password is incorrect!");
				} else {
					boolean result = userDAO.updatePassword(username, newPassword);
					if (result)
						request.setAttribute("message", "Update new password success!");

					else
						request.setAttribute("message", "Update new password fail!");

				}

				if (u.getRole() == 1) {
				
					request.getRequestDispatcher("/admin/others/changepassword.jsp").forward(request, response);
					return;
				} else {
					request.getRequestDispatcher("/client/others/changepassword.jsp").forward(request, response);
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

		}
	}

}
