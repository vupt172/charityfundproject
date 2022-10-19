package AdminController;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CategoryDAO;
import Dao.DonationDAO;
import Dao.FoundationDAO;
import Dao.FundDAO;
import Model.Category;
import Model.Foundation;
import Model.Fund;

/**
 * Servlet implementation class FundController
 */
public class FundController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FundController() {
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
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		if (action != null && action.equals("add")) {
			List<Category> categoryList = new CategoryDAO().search("");
			List<Foundation> foundationList = new FoundationDAO().search("");
			List<String> statusList = Arrays.asList("Opening", "Waiting", "Close", "Finish", "Disable");
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("foundationList", foundationList);
			request.setAttribute("statusList", statusList);
			request.getRequestDispatcher("/admin/fund/fund-form.jsp").forward(request, response);
			return;
		} else if (action != null && action.equals("edit")) {
			int fundId = Integer.parseInt(request.getParameter("id"));
			Fund f = new FundDAO().findById(fundId);
			List<Category> categoryList = new CategoryDAO().search("");
			List<Foundation> foundationList = new FoundationDAO().search("");
			List<String> statusList = Arrays.asList("Opening", "Waiting", "Close", "Finish", "Disable");
			request.setAttribute("fund", f);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("foundationList", foundationList);
			request.setAttribute("statusList", statusList);
			request.getRequestDispatcher("/admin/fund/fund-form.jsp").forward(request, response);
			return;
		}
		// if no action
		FundDAO fDAO = new FundDAO();
		String pageS = request.getParameter("page");
		String id = "";
		String name = "";
		String foundationName = "";
		String categoryName = "";
		if (request.getParameter("id") != null)
			id = request.getParameter("id");
		if (request.getParameter("name") != null)
			name = request.getParameter("name");
		if (request.getParameter("foundation_name") != null)
			foundationName = request.getParameter("foundation_name");
		if (request.getParameter("category_name") != null)
			categoryName = request.getParameter("category_name");

		int page = 1;
		if (pageS != null && !pageS.isEmpty()) {
			page = Integer.parseInt(pageS);
		}

		List<Fund> fundList = fDAO.getPage(page, id, name, foundationName, categoryName);
		request.setAttribute("fundList", fundList);
		int totalItems = fDAO.getTotalItems(id, name, foundationName, categoryName);
		int totalItemsPerPage = 8;
		int totalPage = (int) Math.ceil((double) totalItems / totalItemsPerPage);
		// request attributes
		request.setAttribute("totalPage", totalPage);
		List<Category> categoryList = new CategoryDAO().search("");
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/admin/fund/fund.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action != null && action.equals("add")) {
			Fund f = new Fund();

			f.setName(request.getParameter("name"));
			f.setContent(request.getParameter("content"));
			f.setImage_url(request.getParameter("image_url"));
			f.setDescription(request.getParameter("description"));
			f.setExpectedResult(Integer.parseInt(request.getParameter("expected-result")));
			f.setStatus(request.getParameter("status"));
			f.setCreatedDate(new Date(System.currentTimeMillis()));
			f.setEndDate(Date.valueOf(request.getParameter("end_date")));
			Foundation foundation = new FoundationDAO().findByName(request.getParameter("foundation-name"));
			Category category = new CategoryDAO().findByName(request.getParameter("category-name"));
			f.setCategory(category);
			f.setFoundation(foundation);
			Fund returnFund = new FundDAO().save(f);
			if (returnFund != null) {
				response.sendRedirect(request.getContextPath() + "/admin/fund/?status=afteradd&result=true");
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/fund/?action=add&result=false");
			}
		} else if (action != null && action.equals("edit")) {
			// convert form data to Fund object
			Fund f = new Fund();
			f.setId(Integer.parseInt(request.getParameter("id")));
			f.setName(request.getParameter("name"));
			f.setContent(request.getParameter("content"));
			f.setImage_url(request.getParameter("image_url"));
			f.setDescription(request.getParameter("description"));
			f.setExpectedResult(Integer.parseInt(request.getParameter("expected-result")));
			f.setStatus(request.getParameter("status"));
			f.setCreatedDate(Date.valueOf(request.getParameter("created_date")));
			f.setEndDate(Date.valueOf(request.getParameter("end_date")));
			f.setCategory(new CategoryDAO().findByName(request.getParameter("category-name")));
			f.setFoundation(new FoundationDAO().findByName(request.getParameter("foundation-name")));
			boolean result = new FundDAO().update(f);
			response.sendRedirect(request.getContextPath() + "/admin/fund/?status=afteredit&result=" + result);
		} else if (action != null && action.equals("delete")) {
			int fundId = Integer.parseInt(request.getParameter("fund-id"));
			boolean isExistDonationByFund = new DonationDAO().isExistByFund(fundId);
			if (isExistDonationByFund) {
				boolean result = new FundDAO().updateStatus(fundId, FundDAO.STATUS_DISABLE);
				response.sendRedirect(
						request.getContextPath() + "/admin/fund/?status=afterdeletehasdata&result=" + result);
				return;
			}
			boolean result = new FundDAO().delete(fundId);
			response.sendRedirect(request.getContextPath() + "/admin/fund/?status=afterdelete&result=" + result);
		} else if (action != null && action.equals("multipledelete")) {
			String selectedIdList[] = request.getParameterValues("fund-id");
			for (String fundId : selectedIdList) {
				boolean isExistDonationByFund = new DonationDAO().isExistByFund(Integer.parseInt(fundId));
				if (isExistDonationByFund) {
					response.sendRedirect(
							request.getContextPath() + "/admin/foundation/?status=aftermultipledelete&result=false");
					return;
				}
			}
			try {
				boolean result = new FundDAO().multipleDelete(selectedIdList);
				response.sendRedirect(
						request.getContextPath() + "/admin/fund/?status=aftermultipledelete&result=" + result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
