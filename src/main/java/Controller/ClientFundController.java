package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CategoryDAO;
import Dao.DonationDAO;
import Dao.FundDAO;
import Model.Category;
import Model.Donation;
import Model.Fund;

/**
 * Servlet implementation class ClientFundController
 */
public class ClientFundController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientFundController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	FundDAO fundDAO=new FundDAO();
	DonationDAO donationDAO=new DonationDAO();
	int fundId=Integer.parseInt(request.getParameter("id"));
	Fund fund=fundDAO.findById(fundId);
	List<Donation> donationList=donationDAO.getDonationListByFund(fundId,20);
	List<Category> categoryList=new CategoryDAO().search("");
	request.setAttribute("categoryList", categoryList);
	request.setAttribute("fund", fund);
	request.setAttribute("donationList", donationList);
	request.getRequestDispatcher("client/fundDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
