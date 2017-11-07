package edu.umsl.java.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.Instructor;
import edu.umsl.java.dao.InstructorDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int loginAttempt = 1;
		String sso_id = request.getParameter("sso_id");
		String pswd = request.getParameter("password");
		HttpSession session = request.getSession();
		if (session.getAttribute("loginCount") == null) {
			session.setAttribute("loginCount", loginAttempt);
		} else {
			loginAttempt = (Integer) session.getAttribute("loginCount") + 1;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");

		if (loginAttempt > 3) {
			request.setAttribute("errorMessage",
					"Login attempt maxed out." + " Please contact admin to activate your account.");
			dispatcher.forward(request, response);
		} else {

			try {
				InstructorDao instructorlogindao = new InstructorDao();
				ArrayList<Instructor> list = null;
				if (!(list = instructorlogindao.checkinstlogin(sso_id, pswd)).isEmpty()) {
					session.setAttribute("username", list.get(0).getFname());
					session.setAttribute("userLastName", list.get(0).getLname());
					session.setAttribute("userId", list.get(0).getSsoid());
					session.setAttribute("userRole", list.get(0).getRole());
					
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				} else {
					session.setAttribute("loginCount", loginAttempt);
					request.setAttribute("errorMessage", "Invalid Username or Password. Attempt: " + loginAttempt);

					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				request.setAttribute("errorMessage", "Server error.");
				e.printStackTrace();
			}
		}
	}

}
