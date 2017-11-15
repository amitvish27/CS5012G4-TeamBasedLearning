package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ListUserFnameServlet
 */
@WebServlet("/ListUserFname")
public class ListUserFnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession(true);
	    String role_string = (String) session.getAttribute("userRole");
	    int role = Integer.parseInt(role_string);
	    UserBean user = new UserBean();
	    user.setRole(role);
	    
	    int recPerPage=10;
		String pageString=request.getParameter("page");  
		
		//pageString = "1";
		int pageInteger=0;
		
		if(pageString != null && !pageString.isEmpty()) {
			
				pageInteger=Integer.parseInt(pageString);  
		}
		
		UserDao usrdao = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("listUsers.jsp");
		
		try {
			usrdao = new UserDao();
			
			PageBean pb = usrdao.getCount(user.getView1(), user.getView2());
			pb.setSortBy("fname");
			if (pageInteger<=1) {
				pb.setCurrentPage(0);
				pb.setNextPage(recPerPage);
				pb.setPreviousPage(0);
			}
			else {
				pb.setCurrentPage((pageInteger-1)*recPerPage);
				pb.setNextPage(pb.getCurrentPage()+recPerPage);
				
				pb.setPreviousPage(pb.getCurrentPage()-recPerPage);
				if (pb.getTotalRecords()> pb.getNextPage())
					pb.setNextPage(pb.getCurrentPage());
			}
			
			//TODO in the view add stuff for deleted and others 
			//"SELECT `id`, `ssoid`, `fname`, `lname`, `deleted`, `active` FROM `user` WHERE `deleted`=0 AND `role`=? OR `role`=? ORDER BY `id` ASC  LIMIT ?, ?");
			
			List<UserBean> usrlist = usrdao.getUserListFname(user.getView1(), user.getView2(), pb.getCurrentPage(), recPerPage);
			request.setAttribute("usrlist", usrlist);
			
			request.setAttribute("pb", pb);

		} catch (Exception e) {
			e.printStackTrace();
		}

		dispatcher.forward(request, response);
	}

}
