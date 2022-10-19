package AdminController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Api.*;
import Dao.UserDAO;
import Model.User;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
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
		UserDAO userDAO = new UserDAO();
		if (action != null && action.equals("add")) {
			int roleList[] = { 1,2 };
			String statusList[] = { "NotActivated", "Active", "Inactive", "Banned" };
			//set attribute
			request.setAttribute("roleList", roleList);
			request.setAttribute("statusList", statusList);
			request.getRequestDispatcher("/admin/user/user-form.jsp").forward(request, response);
			return;
		}
		else if(action !=null &&action.equals("edit")) {
		
			int roleList[] = { 1, 2};
			String statusList[] = { "NotActivated", "Active", "Inactive", "Banned" };
			int userId=Integer.parseInt(request.getParameter("id"));
			User user=userDAO.findById(userId);
			//set attribute;
			request.setAttribute("roleList", roleList);
			request.setAttribute("statusList", statusList);
			request.setAttribute("test", "test");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/admin/user/user-form.jsp").forward(request, response);
			return;
		}
		// check action
	
		// if no action
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");
		String roleName = request.getParameter("role");
		int page = 1;
		username = (username == null) ? "" : username;
		email = (email == null) ? "" : email;
		sdt = (sdt == null) ? "" : sdt;
		roleName = (roleName == null) ? "" : roleName;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<User> userList = userDAO.getPage(page, username, email, sdt, roleName);
		int totalItems = userDAO.getTotalItems(username, email, sdt, roleName);
		int totalPage = (int) Math.ceil((double) totalItems / UserDAO.TOTAL_ITEMS_PER_PAGE);
		String roles[] = { "Admin", "User" };
		// set attribute
		request.setAttribute("roles", roles);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("userList", userList);
		//
		request.getRequestDispatcher("/admin/user/user.jsp").forward(request, response);
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
		// check action
		String action = request.getParameter("action");
		if (action != null && action.equals("add")) {
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setDiachi(request.getParameter("address"));
			user.setEmail(request.getParameter("email"));
			user.setFullName(request.getParameter("fullname"));
			user.setRole(Integer.parseInt(request.getParameter("role")));
			user.setSdt(request.getParameter("sdt"));
			user.setStatus(UserDAO.STATUS_NOTACTIVATED);
			String password=StringAPI.generateRandomPassword(8);
			try {
				user.setPassword(StringAPI.encodePassword(password));
			}
			catch(Exception e) {
				e.printStackTrace();
				return;
			}
			User returnUser = userDAO.save(user);
			if (returnUser != null) {
				File registerForm = new File(getServletContext().getRealPath("/document/registerform.txt"));
				UserOperator.sendRegisterMail(user,password, registerForm);
				response.sendRedirect(request.getContextPath() + "/admin/user/?status=afteradd&result=true");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user/?action=add&result=false");
				return;
			}
		} else if (action != null && action.equals("edit")) {
			User user=new User();
			System.out.println("user id :"+request.getParameter("id"));
			user.setId(Integer.parseInt(request.getParameter("id")));
			user.setUsername(request.getParameter("username"));
			user.setRole(Integer.parseInt(request.getParameter("role")));
			user.setEmail(request.getParameter("email"));
			user.setFullName(request.getParameter("fullname"));
			user.setSdt(request.getParameter("sdt"));
			System.out.println("status"+request.getParameter("status"));
			user.setStatus(request.getParameter("status"));
			user.setDiachi(request.getParameter("address"));
			boolean result=userDAO.update(user);
			response.sendRedirect(request.getContextPath()+"/admin/user/?status=afteredit&result="+result);
			return;
		} else if (action != null && action.equals("delete")) {
		int userId=Integer.parseInt(request.getParameter("user-id"));
		User u=userDAO.findById(userId);
		if(u.getRole()==1) {
			response.sendRedirect(request.getContextPath()+"/admin/user?status=afterdelete&result=false&cause=adminrole");
			return;
		}
		boolean checkIfUserHasData=userDAO.checkIfUserHasData(userId);
		if(checkIfUserHasData) {
			boolean result=userDAO.updateStatus("Inactive", userId);
			response.sendRedirect(request.getContextPath()+"/admin/user?status=updateStatusToInactive&result="+result);
		}
		else {
			boolean result=userDAO.delete(userId);
			response.sendRedirect(request.getContextPath()+"/admin/user/?status=afterdelete&result="+result);
			return;
		}
		}
		else if(action!=null && action.equals("resetpassword")) {
			System.out.println("ResetPassword");
	     int userId=Integer.parseInt(request.getParameter("id"));
	     String newPassword=StringAPI.generateRandomPassword(8);
	     boolean result=userDAO.updatePassword(newPassword, userId);
	     if(result) {
	    	 User u=userDAO.findById(userId);
	    	 UserOperator.sendPasswordResetMail(u, newPassword);
	    	 response.sendRedirect(request.getContextPath()+"/admin/user/?status=afterupdatepassword&result=true");
	     }
	     else {
	    	 response.sendRedirect(request.getContextPath()+"/admin/user/?status=afterupdatepassword&result=false");
	     }
		}
	}

}
