package Controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Api.MailOperator;
import Api.StringAPI;
import Api.UserOperator;
import Dao.UserDAO;
import Model.User;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ok");
		UserDAO userDAO=new UserDAO();
		//check action
		String action=request.getParameter("action");
		if(action!=null&&action.equals("activate")) {
			String username=request.getParameter("username");
			String key=request.getParameter("key");
			boolean result=userDAO.activate(username, key);
			if(result) {
				request.setAttribute("username", username);
				request.getRequestDispatcher("notification/activatesuccess.jsp").forward(request, response);
				return;
			}
			else {
				request.setAttribute("username", username);
				request.getRequestDispatcher("notification/activatefail.jsp").forward(request, response);
				return;
			}
		}
		//if no action
		System.out.println("register");
		request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			User newUser=new User();
			newUser.setUsername(request.getParameter("username"));
			newUser.setEmail(request.getParameter("email"));
			newUser.setDiachi(request.getParameter("address"));
			newUser.setSdt(request.getParameter("sdt"));
			newUser.setFullName(request.getParameter("fullname"));
			String password=StringAPI.generateRandomPassword(8);
			newUser.setPassword(StringAPI.encodePassword(password));
			newUser.setStatus(UserDAO.STATUS_NOTACTIVATED);
			newUser.setRole(UserDAO.USER_ROLE);
			UserDAO userDAO=new UserDAO();
			//check if user exist
			User existUser=userDAO.findByName(newUser.getUsername());
			if(existUser!=null) {
				System.out.println("Exist username");
				request.setAttribute("message","Username is already exist!");
				request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
				return;
			}
			existUser=userDAO.findByEmail(newUser.getEmail());
			if(existUser!=null) {
				System.out.println("Exist email");
				request.setAttribute("message","Email is already exist!");
				request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
				return;
			}
			//
			User returnUser=userDAO.save(newUser);
			if(returnUser!=null) {
				System.out.println("create user");
				File registerForm=new File(getServletContext().getRealPath("/document/registerform.txt"));
				UserOperator.sendRegisterMail(returnUser,password, registerForm);
				request.setAttribute("user", returnUser);
				request.getRequestDispatcher("/client/registersuccess.jsp").forward(request, response);
				return;
			}
			else {
				System.out.println("create user fail");
				request.setAttribute("response-message","Some errors occur!");
				request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
				return;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		
		
	}

}
