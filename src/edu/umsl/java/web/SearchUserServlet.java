package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUser")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao usrdao = null;

		String ssoid = request.getParameter("ssoid");
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String dept = request.getParameter("dept");

		try {
			usrdao = new UserDao();

			// TODO in the view set stuff
			// "SELECT `id`, `ssoid`, `fname`, `lname`, `deleted`, `active`, `role`,
			// `email`, `dept` FROM `user` WHERE `ssoid` LIKE ? OR `fname` LIKE ? OR `lname`
			// LIKE ? OR `email` LIKE ? OR `dept` LIKE ?");
			List<UserBean> usrlist = usrdao.getUserSearchList(ssoid, fname, lname, email, dept);
			request.setAttribute("usrlist", usrlist);
			request.setAttribute("usrdao", usrdao);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("searchUserResult.jsp").forward(request, response);
	}

}
