package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserDao userdao = null;
		
		String firstname = request.getParameter("first_name");
		String lastname = request.getParameter("last_name");
		String ssoid = request.getParameter("ssoid");
		String [] ab = request.getParameterValues("role");
		
		if (firstname.length()>0 && lastname.length()>0 && ssoid.length()>0) {
			
			UserBean userbean = new UserBean();
			userbean.setFname(firstname);
			userbean.setLname(lastname);
			userbean.setSsoid(ssoid);
		
			try {
				userdao = new UserDao();
				
				userdao.addUser(userbean);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		response.sendRedirect("listuser");
	
	}

}