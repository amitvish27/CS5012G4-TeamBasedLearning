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
import edu.umsl.java.beans.StudentLogin;
import edu.umsl.java.dao.InstructorDao;
import edu.umsl.java.dao.StudentLoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String radio = request.getParameter("kind");
		String uname = request.getParameter("sso_id");
		String pwd = request.getParameter("password");
		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");

		try {

			if (radio.equals("tea")) {
				InstructorDao instructorlogindao = new InstructorDao();
				ArrayList<Instructor> list=null;
				if (!(list = instructorlogindao.checkinstlogin(uname, pwd)).isEmpty()) {
					session.setAttribute("username", list.get(0).getFirst_name());
					session.setAttribute("userId", list.get(0).getId());
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				} 
				else {
					request.setAttribute("errorMessage", "Invalid Username or Password");
					dispatcher.forward(request, response);
				}
			} 
			else {
				StudentLoginDao studentlogindao = new StudentLoginDao();
				ArrayList<StudentLogin> list=null;
				if (!(list=studentlogindao.checkstulogin(uname, pwd)).isEmpty()) {
					session.setAttribute("username", list.get(0).getSSO_ID());
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				} 
				else {
					request.setAttribute("errorMessage", "Invalid Username or Password");
					dispatcher.forward(request, response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Invalid Username or Password");
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
