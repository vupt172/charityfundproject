package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDAO;
import Model.User;

/**
 * Servlet implementation class ClientUserController
 */
public class ClientUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientUserController() {
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
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.getRequestDispatcher("client/userinfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
        User sessionUser=(User)session.getAttribute("user");
        UserDAO userDAO=new UserDAO();
		if (action != null && action.equals("update")) {

			String username = request.getParameter("username");
			String fullname = request.getParameter("fullname");
			String sdt = request.getParameter("sdt");
			String email = request.getParameter("email");
			String diachi = request.getParameter("address");
			boolean result = userDAO.updateUserInfo(username, fullname, email, sdt, diachi);
			String message = "";
			if (result) {
              sessionUser=userDAO.findByName(username);
              session.setAttribute("user", sessionUser);
				message = "Cập nhật thành công";
			} else {
				message = "Cập nhật thất bại.Đã có lỗi xảy ra";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("client/userinfo.jsp").forward(request, response);
		}
	}

}
