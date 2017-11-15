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
 * Servlet implementation class DelUserServlet
 */
@WebServlet("/DelUser")
public class DelUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			userDao = new UserDao();
			userDao.deleteUser(String.valueOf(id));
		} catch (Exception ex) {
			HttpSession session = request.getSession();
			session.setAttribute("message", ex.getMessage());
		}
		response.sendRedirect("ManageUser");
	}
}
