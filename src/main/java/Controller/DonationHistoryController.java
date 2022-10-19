package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CategoryDAO;
import Dao.DonationDAO;
import Model.Category;
import Model.Donation;
import Model.User;

/**
 * Servlet implementation class DonationHistoryController
 */
public class DonationHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationHistoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DonationDAO donationDAO=new DonationDAO();
		int page =1;
		if(request.getParameter("page")!=null)page=Integer.parseInt(request.getParameter("page"));
		HttpSession session=request.getSession();
		User sessionUser=(User)session.getAttribute("user");
		if(sessionUser==null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		List<Donation> donationList=donationDAO.getPageDonationListByUser(page, sessionUser.getId());
		System.out.println("Donation List by user :"+donationList.size());
		int totalItems=donationDAO.getTotalDonationByUser(sessionUser.getId());
		int totalPages=(int)Math.ceil((double)totalItems/DonationDAO.TOTAL_ITEMS_PER_PAGE);
		List<Category> categoryList=new CategoryDAO().search("");
		request.setAttribute("categoryList",categoryList);
		request.setAttribute("totalPages", totalPages);
        request.setAttribute("donationList", donationList);
        request.getRequestDispatcher("client/donationhistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
