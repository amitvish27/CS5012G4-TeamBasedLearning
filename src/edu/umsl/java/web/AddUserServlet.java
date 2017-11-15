package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;
import edu.umsl.java.util.GeneratePassword;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserDao userdao = null;
		
		String adminUser = (String) session.getAttribute("userId");
		
		String ssoid = request.getParameter("mod_user_ssoId");
		String firstname = request.getParameter("mod_user_fname");
		String lastname = request.getParameter("mod_user_lname");
		String email = request.getParameter("mod_user_email");
		String dept = request.getParameter("mod_user_dept");
		String role = request.getParameter("mod_user_role");
		String active = request.getParameter("mod_user_active");
		String deleted = request.getParameter("mod_user_deleted");
		
		if (firstname.length()>0 && lastname.length()>0 && ssoid.length()>0) {
			
			UserBean userbean = new UserBean();
			userbean.setSsoid(ssoid);
			userbean.setFname(firstname);
			userbean.setLname(lastname);
			userbean.setEmail(email);
			userbean.setDept(dept);
			userbean.setRole(Integer.parseInt(role));
			userbean.setActive(Integer.parseInt(active));
			userbean.setDeleted(Integer.parseInt(deleted));
			userbean.setCreatedby(adminUser);
			userbean.setModifiedby(adminUser);

			try {
				GeneratePassword.generateTempPassword(ssoid, userbean);
				userdao = new UserDao();
				userdao.addUser(userbean);
				
			} catch (Exception e) {
				session.setAttribute("message", e.getMessage());
			}
		}
		response.sendRedirect("ManageUser");
	}

}
