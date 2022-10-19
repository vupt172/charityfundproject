package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CategoryDAO;
import Dao.FoundationDAO;
import Model.Category;
import Model.Foundation;

/**
 * Servlet implementation class ClientFoundation
 */
public class ClientFoundation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientFoundation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	FoundationDAO foundationDAO=new FoundationDAO();
	List<Foundation> foundationList=foundationDAO.search("");
	List<Category> categoryList=new CategoryDAO().search("");
	request.setAttribute("categoryList", categoryList);
	request.setAttribute("foundationList", foundationList);
	request.getRequestDispatcher("client/foundation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
