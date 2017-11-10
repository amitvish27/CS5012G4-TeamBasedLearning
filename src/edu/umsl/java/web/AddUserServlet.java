package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String dept = request.getParameter("dept");
		
		
		if (firstname.length()>0 && lastname.length()>0 && ssoid.length()>0) {
			
			UserBean userbean = new UserBean();
			userbean.setFname(firstname);
			userbean.setLname(lastname);
			userbean.setSsoid(ssoid);
			userbean.setEmail(email);
			userbean.setRole(Integer.parseInt(role));
			userbean.setDept(dept);
		
			try {
				userdao = new UserDao();
				
				userdao.addUser(userbean);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				HttpSession session = request.getSession();
				session.setAttribute("message", "Add record failed. Duplicate entry.");
				
				//e.printStackTrace();
			}
		
		}
		
		response.sendRedirect("listuser");
	
	}

}
