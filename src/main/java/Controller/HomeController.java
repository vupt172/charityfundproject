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
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
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
	    DonationDAO donationDAO=new DonationDAO();
	    List<Category> categoryList=categoryDAO.search("");
	    List<Fund> openingFundList=fundDAO.getOpeningFund(6);
	    List<Fund> finishFundList=fundDAO.getFinishFund(6);
	    List<Donation> donationList=donationDAO.getDonationListSortByTime(donationDAO.ORDERBY_DESC,8);
	    System.out.println("Total category :"+categoryList.size());
	    System.out.println("Total opening fund list :"+openingFundList.size());
	    System.out.println("Total finish fund list :"+finishFundList.size());
	    System.out.println("Total donation :"+donationList.size());
	    request.setAttribute("categoryList", categoryList);
	    request.setAttribute("openingFundList", openingFundList);
	    request.setAttribute("finishFundList", finishFundList);
	    request.setAttribute("donationList", donationList);
		request.getRequestDispatcher("/client/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
