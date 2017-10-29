package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class ListUserServlet
 */
@WebServlet("/listuser")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao usrdao = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		
		
		try {
			usrdao = new UserDao();
			
			//create a list of UserBean with the values of usrdao getUserList
			List<UserBean> usrlist = usrdao.getUserList();
			request.setAttribute("usrlist", usrlist);
			
			String id = request.getParameter("id");
			UserBean user = usrdao.getUser(id);
			request.setAttribute("user", user);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dispatcher.forward(request, response);
		
		
		
		
		
	}

}
