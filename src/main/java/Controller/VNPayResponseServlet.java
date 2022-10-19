package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DonationDAO;
import Model.Donation;

/**
 * Servlet implementation class VNPayResponseServlet
 */
public class VNPayResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VNPayResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DonationDAO donationDAO=new DonationDAO();
		// if success
		if("00".equals(request.getParameter("vnp_TransactionStatus"))) {
		HttpSession session=request.getSession();
		Donation donation=(Donation)session.getAttribute("donation");
		Donation donationResult=donationDAO.save(donation);
		if(donationResult!=null) {
			response.sendRedirect(request.getContextPath()+"/home?status=afteradddonate&result=true");
		}
		else { //if fail
			response.sendRedirect(request.getContextPath()+"/home?status=afteradddonate&result=false");
		}
		}
		else {
			response.sendRedirect(request.getContextPath()+"/home?status=afterdonate&result=false");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
