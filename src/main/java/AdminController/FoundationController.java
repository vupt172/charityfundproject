package AdminController;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.FoundationDAO;
import Dao.FundDAO;
import Model.Foundation;

/**
 * Servlet implementation class FoundationController
 */
public class FoundationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoundationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check action
		String action = request.getParameter("action");
		if (action != null && action.equals("add")) {
			List<String> statusList = Arrays.asList("Enable", "Disable");
			request.setAttribute("statusList", statusList);
			request.getRequestDispatcher("/admin/foundation/foundation-form.jsp").forward(request, response);
		} else if (action != null && action.equals("edit")) {
			int foundationId = Integer.parseInt(request.getParameter("id"));
			List<String> statusList = Arrays.asList("Enable", "Disable");
			request.setAttribute("statusList", statusList);
			Foundation f = new FoundationDAO().findById(foundationId);
			request.setAttribute("foundation", f);
			request.getRequestDispatcher("/admin/foundation/foundation-form.jsp").forward(request, response);
		}
		// if no action
		FoundationDAO fDAO = new FoundationDAO();
		String pageS = request.getParameter("page");
		String id = "";
		String name = "";
		String email = "";
		if (request.getParameter("id") != null)
			id = request.getParameter("id");
		if (request.getParameter("name") != null)
			name = request.getParameter("name");
		if (request.getParameter("email") != null)
			email = request.getParameter("email");

		int page = 1;
		if (pageS != null && !pageS.isEmpty()) {
			page = Integer.parseInt(pageS);
		}
		List<Foundation> foundationList = fDAO.getPage(page, id, name, email);
		request.setAttribute("foundationList", foundationList);
		int totalItems = fDAO.getTotalItems(id, name, email);
		int totalItemsPerPage = 8;
		int totalPage = (int) Math.ceil((double) totalItems / totalItemsPerPage);
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/admin/foundation/foundation.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (action != null && action.equals("add")) {
			Foundation f = new Foundation();
			f.setName(request.getParameter("name"));
			f.setDescription(request.getParameter("description"));
			f.setEmail(request.getParameter("email"));
			f.setStatus(request.getParameter("status"));
			Foundation returnFoundation = new FoundationDAO().save(f);
			if (returnFoundation != null) {
				response.sendRedirect(request.getContextPath() + "/admin/foundation/?action=add&result=false");
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/foundation");
			}
		} else if (action != null && action.equals("edit")) {
			Foundation f = new Foundation();
			f.setId(Integer.parseInt(request.getParameter("id")));
			f.setName(request.getParameter("name"));
			f.setDescription(request.getParameter("description"));
			f.setEmail(request.getParameter("email"));
			f.setStatus(request.getParameter("status"));
			boolean result = new FoundationDAO().update(f);
			response.sendRedirect(request.getContextPath() + "/admin/foundation/?status=afteredit&result=" + result);
		} else if (action != null && action.equals("delete")) {
			FoundationDAO foundationDAO=new FoundationDAO();
			int foundationId = Integer.parseInt(request.getParameter("foundationId"));
			boolean isExistFundByFoundation =new FundDAO().isExistByFoundation(foundationId);
			if(isExistFundByFoundation) {
				boolean result=foundationDAO.updateStatus(foundationId, FoundationDAO.STATUS_DISABLE);
				response.sendRedirect(request.getContextPath() + "/admin/foundation/?status=afterdeletehasdata&result=" + result);
				return;
			}
			boolean result = new FoundationDAO().delete(foundationId);
			response.sendRedirect(request.getContextPath() + "/admin/foundation/?status=afterdelete&result=" + result);
		} else if (action != null && action.equals("multipledelete")) {
			String selectedIdList[] = request.getParameterValues("foundationId");
			for(String foundationId:selectedIdList) {
				boolean isExistFundByFoundation=new FundDAO().isExistByFoundation(Integer.parseInt(foundationId));
				if(isExistFundByFoundation) {
					response.sendRedirect(
							request.getContextPath() + "/admin/foundation/?status=aftermultipledelete&result=false");
					return;
				}
			}
			try {
				boolean result = new FoundationDAO().multipleDelete(selectedIdList);
				response.sendRedirect(
						request.getContextPath() + "/admin/foundation/?status=aftermultipledelete&result=" + result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
