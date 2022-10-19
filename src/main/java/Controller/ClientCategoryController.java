package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CategoryDAO;
import Dao.FundDAO;
import Model.Category;
import Model.Fund;

/**
 * Servlet implementation class ClientController
 */
public class ClientCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategoryDAO categoryDAO=new CategoryDAO();
		FundDAO fundDAO=new FundDAO();
		int categoryId=Integer.parseInt(request.getParameter("id"));
		Category category=categoryDAO.findById(categoryId);
		List<Fund> fundList=fundDAO.getFundListByCategory(categoryId);
		List<Category> categoryList=new CategoryDAO().search("");
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("category", category);
		request.setAttribute("fundList", fundList);
		request.getRequestDispatcher("client/category.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
