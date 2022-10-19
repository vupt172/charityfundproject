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
 * Servlet implementation class ActivityController
 */
public class ActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivityController() {
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

		FundDAO fundDAO = new FundDAO();
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		CategoryDAO categoryDAO=new CategoryDAO();
		String fundStatus = request.getParameter("status");
		List<Category> categoryList=categoryDAO.search("");
		List<Fund> fundList = fundDAO.getClientPage(page, fundStatus);
		int totalItems = fundDAO.getTotalItems(fundStatus);
		int totalPages = (int) Math.ceil((double) totalItems / FundDAO.TOTAL_ITEMS_PER_CLIENTPAGE);
		// set attribute
		request.setAttribute("fundList", fundList);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("client/activity.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
