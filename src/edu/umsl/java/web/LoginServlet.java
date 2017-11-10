package edu.umsl.java.web;

import java.io.IOException;

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
		//get login attempt count if available
		if (session.getAttribute("loginCount") == null) {
			session.setAttribute("loginCount", loginAttempt);
		} else {
			loginAttempt = (Integer) session.getAttribute("loginCount") + 1;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		//check if attempt exceeds 3
		if (loginAttempt > 3) {
			request.setAttribute("errorMessage",
					"Login attempt maxed out." + " Please contact admin to activate your account.");
			try {
				new InstructorDao().setInstActive(sso_id, 0, sso_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
		} else {

			try {
				Instructor inst = new InstructorDao().getInstructorBySsoId(sso_id);
				//check if user account is active
				if(inst.getSsoid() == null) {
					request.setAttribute("errorMessage", "Account does not exists. ");
					dispatcher.forward(request, response);
				}
				else if (inst.getActive() == 1) {
					//check the password
					if (inst.getPswd().equals(pswd)) {

						session.setAttribute("username", inst.getFname());
						session.setAttribute("userLastName", inst.getLname());
						session.setAttribute("userId", inst.getSsoid());
						session.setAttribute("userRole", inst.getRole());

						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					} else {
						//password incorrect 
						session.setAttribute("loginCount", loginAttempt);
						request.setAttribute("errorMessage", "Invalid Username or Password. Attempt: " + loginAttempt);

						dispatcher.forward(request, response);
					}
					
				} else if (inst.getActive() == 0) {
					//user account deactivated 
					request.setAttribute("errorMessage", "Your account is deactivated. Please contact Admin to activate your account. ");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				request.setAttribute("errorMessage", "Server error.");
				e.printStackTrace();
			}
		}
	}

}
