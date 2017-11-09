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

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;
import edu.umsl.java.beans.PageBean;

/**
 * Servlet implementation class ListUserServlet
 */
@WebServlet("/listuser")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession(true);
	    String role_string = (String) session.getAttribute("role");
	    int role = Integer.parseInt(role_string);
	    UserBean user = new UserBean();
	    user.setRole(role);
	    int view1 = user.getView1();
	    int view2 = user.getView2();
	    
	    
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
			
			PageBean pb = usrdao.getCount();
			pb.setSortBy("id");
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
			
			
			
		    
			List<UserBean> usrlist = usrdao.getUserList(user.getView1(), user.getView2(), pb.getCurrentPage(), recPerPage);
			request.setAttribute("usrlist", usrlist);
			
			
			request.setAttribute("pb", pb);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dispatcher.forward(request, response);
		
		
		
		
		
	}

}
