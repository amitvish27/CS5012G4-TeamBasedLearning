package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.PageBean;
import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class ManageUserServlet
 */
@WebServlet("/ManageUser")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((int) session.getAttribute("userRole") != 2) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		String errMsg = (String) session.getAttribute("message");
		if(errMsg!=null)
		{
			request.setAttribute("errMsg", errMsg);
			session.removeAttribute("message");
		}
		int role = (session.getAttribute("userRole") != null) ? (int) (session.getAttribute("userRole")) : 0;

		UserBean user = new UserBean();
		user.setRole(role);
		int recPerPage = 10;

		String pageString = request.getParameter("page");

		int pageInteger = 0;

		if (pageString != null && !pageString.isEmpty()) {

			pageInteger = Integer.parseInt(pageString);
		}
		UserDao usrdao = null;

		try {
			usrdao = new UserDao();
			
			PageBean pb = usrdao.getCount(user.getView1(), user.getView2());
			
			
			// Entries per page
			String recPerPgStr = (String) request.getParameter("ent");
			if (recPerPgStr != null) {
				recPerPage = Integer.parseInt(recPerPgStr);
				pb.setRecordsPerPage(recPerPage);
			} else {
				pb.setRecordsPerPage(recPerPage);
			}
			
			// Sorting page by
			String sortBy= (String) request.getParameter("sort");
			String sortDir= (String) request.getParameter("dir");
			if(sortBy!=null) {
				if(sortDir==null) {
					sortDir="ASC";
				}
				pb.setSortBy(sortBy);
			}
			else{
				sortBy="created"; //default
				sortDir="ASC"; // default
				pb.setSortBy("created"); // default
			}
			System.out.println("Sort in Manage " + sortBy + "_"+sortDir);
			// Pagination
			if (pageInteger <= 1) {
				pb.setCurrentPage(0);
				pb.setNextPage(recPerPage);
				pb.setPreviousPage(0);
				pageInteger = 1;
			} else {
				pb.setCurrentPage((pageInteger - 1) * recPerPage);
				pb.setNextPage(pb.getCurrentPage() + recPerPage);

				pb.setPreviousPage(pb.getCurrentPage() - recPerPage);
				if (pb.getTotalRecords() > pb.getNextPage())
					pb.setNextPage(pb.getCurrentPage());
			}
			
			List<UserBean> usrlist = usrdao.getUserListSorted(pageInteger,
					recPerPage,sortBy, sortDir);
			
			int totalpg = (int) Math.ceil((double)pb.getTotalRecords() / (double)pb.getRecordsPerPage());
			request.setAttribute("maxpg", totalpg);
			request.setAttribute("usrlist", usrlist);
			request.setAttribute("pb", pb);
			request.setAttribute("crtpg", pageInteger);
			request.setAttribute("sortBy", sortBy);
			request.setAttribute("sortDir", sortDir);
			request.setAttribute("recPerPgStr", pb.getRecordsPerPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("usersList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
