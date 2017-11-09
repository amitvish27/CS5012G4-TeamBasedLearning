package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
			List<UserBean> usrlist = usrdao.getUserListLname(0, 1, pb.getCurrentPage(),recPerPage);
			request.setAttribute("usrlist", usrlist);
			
			request.setAttribute("pb", pb);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dispatcher.forward(request, response);
	}

}
