package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;
import edu.umsl.java.util.GeneratePassword;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/ResetPassword")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ssoid = request.getParameter("ssoid");
		
		PrintWriter out = response.getWriter();
		String data="";
		try {
			UserDao userDao = new UserDao();
			System.out.println("Reset password successful" + ssoid);
			UserBean user = userDao.getInstructorBySsoId(ssoid);
			
			GeneratePassword.generateTempPassword(ssoid, user);
			data = "<div id='message'>Success</div>";
			System.out.println("Reset password successful");
		
		} catch (Exception e) {
			data = "<div id='message'>Failed</div>";
			
		}
		out.print(data);
		out.flush();
		out.close();
		
	}

}
