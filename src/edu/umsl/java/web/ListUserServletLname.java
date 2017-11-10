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
 * Servlet implementation class ListUserServletLname
 */
@WebServlet("/ListUserServletLname")
public class ListUserServletLname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ListUserServletLname() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession(true);
	    String role_string = (String) session.getAttribute("role");
	    int role = Integer.parseInt(role_string);
	    UserBean user = new UserBean();
	    user.setRole(role);
	    
	    int recPerPage=10;
		String pageString=request.getParameter("page");  
		
		int pageInteger=0;
		
		if(pageString != null && !pageString.isEmpty()) {
			
				pageInteger=Integer.parseInt(pageString);  
		}
		
		UserDao usrdao = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("listUsers.jsp");
		
		
		try {
			usrdao = new UserDao();
			
			PageBean pb = usrdao.getCount(user.getView1(), user.getView2());
			pb.setSortBy("lname");
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
			
			List<UserBean> usrlist = usrdao.getUserListLname(user.getView1(), user.getView2(), pb.getCurrentPage(),recPerPage);
			request.setAttribute("usrlist", usrlist);
			
			request.setAttribute("pb", pb);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dispatcher.forward(request, response);
	}

}
