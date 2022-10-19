package AdminController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CategoryDAO;
import Dao.FundDAO;
import Model.Category;

/**
 * Servlet implementation class CategoryController
 */
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
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
		// check action
		String action = request.getParameter("action");
		if (action != null && action.equals("add")) {
			List<String> statusList=Arrays.asList("Enable","Disable");
			request.setAttribute("statusList", statusList);
			request.getRequestDispatcher("/admin/category/category-form.jsp").forward(request, response);
		} else if (action != null && action.equals("edit")) {
			int categoryId = Integer.parseInt(request.getParameter("id"));
			Category c = new CategoryDAO().findById(categoryId);
			// set attribute
			request.setAttribute("category", c);
			List<String> statusList=Arrays.asList("Enable","Disable");
			request.setAttribute("statusList", statusList);
			request.getRequestDispatcher("/admin/category/category-form.jsp").forward(request, response);
		}
		// if no action
		CategoryDAO categoryDAO = new CategoryDAO();
		int page = 1;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if (id == null)
			id = "";
		if (name == null)
			name = "";
		List<Category> categoryList = categoryDAO.getPage(page, id, name);
		int totalItems = categoryDAO.getTotalItems(id, name);
		int totalPage = (int) Math.ceil((double) totalItems / CategoryDAO.TOTAL_ITEMS_PER_PAGE);
		// set attribute
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/admin/category/category.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//check action
		String action=request.getParameter("action");
		if(action!=null && action.equals("add")) {
		 String name=request.getParameter("name");
		 String description=request.getParameter("description");
		 String status=request.getParameter("status");
	     Category c=new Category();
	     c.setName(name);
	     c.setDescription(description);
	     c.setStatus(status);
		 Category returnCategory=new CategoryDAO().save(c);
		 if(returnCategory!=null) {
			 response.sendRedirect(request.getContextPath()+"/admin/category/?status=afteradd&result=true");
		 }
		 else {
			 response.sendRedirect(request.getContextPath()+"/admin/category/?action=add&result=false");
		 }
		}
		else if(action!=null&&action.equals("edit")) {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		Category c=new Category();
		c.setId(categoryId);
		c.setName(request.getParameter("name"));
		c.setDescription(request.getParameter("description"));
	    c.setStatus(request.getParameter("status"));
	    boolean result=new CategoryDAO().update(c);
	    response.sendRedirect(request.getContextPath()+"/admin/category/?status=afteredit&result="+result);
		}
		else if(action!=null && action.equals("delete")) {
			int id=Integer.parseInt(request.getParameter("category-id"));
			CategoryDAO categoryDAO=new CategoryDAO();
			boolean isExistFundByCategory=new FundDAO().isExistByCategory(id);
			if(isExistFundByCategory) {
				boolean result= categoryDAO.updateStatus(id, CategoryDAO.STATUS_DISABLE);
				response.sendRedirect(request.getContextPath()+"/admin/category/?status=afterdeletehasdata&result="+result);
				return;
			}
			boolean result=categoryDAO.delete(id);
			response.sendRedirect(request.getContextPath()+"/admin/category/?status=afterdelete&result="+result);
		}
		else if(action!=null && action.equals("multipledelete")) {
			String idList[]=request.getParameterValues("category-id");
			for(String categoryId:idList) {
				boolean isExistFundByCategory=new FundDAO().isExistByCategory(Integer.parseInt(categoryId));
				if(isExistFundByCategory) {
					response.sendRedirect(
							request.getContextPath() + "/admin/foundation/?status=aftermultipledelete&result=false");
					return;
				}
			}
			try {
				boolean result=new CategoryDAO().multipleDelete(idList);
				response.sendRedirect(request.getContextPath()+"/admin/category/?status=aftermultipledelete&result="+result);
			}
			catch(Exception e) {
				e.printStackTrace();
				return ;
			}
				
		}
	}

}
