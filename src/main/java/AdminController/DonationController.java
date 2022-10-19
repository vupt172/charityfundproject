package AdminController;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DonationDAO;
import Dao.FundDAO;
import Dao.UserDAO;
import Model.Donation;
import Model.Fund;
import Model.User;

/**
 * Servlet implementation class DonationController
 */
public class DonationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	DonationDAO donationDAO=new DonationDAO();
	UserDAO userDAO=new UserDAO();
	FundDAO fundDAO=new FundDAO();
	request.setCharacterEncoding("utf-8");
	//check action
	String action=request.getParameter("action");
	if(action!=null&&action.equals("add")) {
		List<User> userList=userDAO.getByRole(UserDAO.USER_ROLE);
		List<Fund> fundList=fundDAO.search("");
		request.setAttribute("userList", userList);
		request.setAttribute("fundList", fundList);
		request.getRequestDispatcher("/admin/donation/donation-form.jsp").forward(request, response);
		return;
	}
	else if(action!=null && action.equals("edit")) {
		System.out.println("get edit");
		List<User> userList=userDAO.getByRole(UserDAO.USER_ROLE);
		List<Fund> fundList=fundDAO.search("");
		int donationId=Integer.parseInt(request.getParameter("id"));
		Donation donation=donationDAO.findById(donationId);
		request.setAttribute("userList", userList);
		request.setAttribute("fundList", fundList);
		request.setAttribute("donation", donation);
		request.getRequestDispatcher("/admin/donation/donation-form.jsp").forward(request, response);
		return;
	}
	 //if no action
    String id=request.getParameter("id");
	String username=request.getParameter("username");
	String fundName=request.getParameter("fundname");
	if(id==null)id="";
	if(username==null)username="";
	if(fundName==null)fundName="";
	int page=1;
	if(request.getParameter("page")!=null) {
		page=Integer.parseInt(request.getParameter("page"));
	}
	List<Donation> donationList=donationDAO.getPage(page, id, username, fundName);
	int totalItems=donationDAO.getTotalItems(id, username, fundName);
	int totalPages=(int)Math.ceil((double)totalItems/donationDAO.TOTAL_ITEMS_PER_PAGE);
	System.out.println("totalItems :"+totalItems);
	System.out.println("totalPages :"+totalPages);
	//set attribute
	request.setAttribute("totalPages", totalPages);
	request.setAttribute("donationList", donationList);
	request.getRequestDispatcher("/admin/donation/donation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("donation post");
		UserDAO userDAO=new UserDAO();
		FundDAO fundDAO=new FundDAO();
		DonationDAO donationDAO=new DonationDAO();
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if(action!=null && action.equals("add")) {
		 Donation donation=new Donation();
		 donation.setDonationAmount(Integer.parseInt(request.getParameter("donation-amount")));
		 donation.setDonationMessage(request.getParameter("donation-message"));
		 donation.setCreatedDate(new Date(System.currentTimeMillis()));
		 donation.setUser(userDAO.findByName(request.getParameter("username")));
		 donation.setFund(fundDAO.findById(Integer.parseInt(request.getParameter("fundname"))));
		 System.out.println(donation.getUser().getId());
		 System.out.println(donation.getFund().getId());
		 Donation returnDonation=donationDAO.save(donation);
		 if(returnDonation==null) {
			 response.sendRedirect(request.getContextPath()+"/admin/donation/?action=add&result=false");
			 return;
		 }
		 else {
			 response.sendRedirect(request.getContextPath()+"/admin/donation/?status=afteradd&result=true");
			 return;
	 }
		}
		else if(action!=null && action.equals("edit")) {
		Donation donation=new Donation();
		donation.setId(Integer.parseInt(request.getParameter("id")));
		donation.setDonationAmount(Integer.parseInt(request.getParameter("donation-amount")));
		donation.setDonationMessage(request.getParameter("donation-message"));
		donation.setCreatedDate(new Date(System.currentTimeMillis()));
		donation.setUser(userDAO.findByName(request.getParameter("username")));
		donation.setFund(fundDAO.findById(Integer.parseInt(request.getParameter("fundname"))));
		boolean result=donationDAO.update(donation);
		response.sendRedirect(request.getContextPath()+"/admin/donation/?status=afteredit&result="+result);
		return;
		}
		else if(action!=null && action.equals("delete")) {
		int donationId=Integer.parseInt(request.getParameter("id"));
		boolean result=donationDAO.delete(donationId);
		response.sendRedirect(request.getContextPath()+"/admin/donation/?status=afterdelete&result="+result);
		return;
		}
		else if(action!=null && action.equals("multipledelete")) {
		try {
			 String idList[]=request.getParameterValues("donation-id");
			 boolean result=donationDAO.multipledelete(idList);
			 response.sendRedirect(request.getContextPath()+"/admin/donation?status=aftermultipledelete&result="+result);
			 return;
		}	
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		}
	}

}
