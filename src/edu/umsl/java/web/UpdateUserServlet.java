package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userdao = null;
		
		String id = request.getParameter("mod_user_id");
		String firstname = request.getParameter("mod_user_fname");
		String lastname = request.getParameter("mod_user_lname");
		String ssoid = request.getParameter("mod_user_ssoId");
		String role = request.getParameter("mod_user_role");
		String email = request.getParameter("mod_user_email");
		String dept = request.getParameter("mod_user_dept");
		String active = request.getParameter("mod_user_active");
		String deleted = request.getParameter("mod_user_deleted");
		
		if (firstname.length() > 0 && lastname.length() > 0 && ssoid.length() > 0 && role.length()>0) {
			try {
				userdao = new UserDao();
				userdao.updateUser(ssoid, firstname, lastname, id, role, email, dept, deleted, active);
			} catch (Exception e) {
				HttpSession session = request.getSession();
				session.setAttribute("message", e.getMessage());
			}
		}
		response.sendRedirect("ManageUser");
	}

}
