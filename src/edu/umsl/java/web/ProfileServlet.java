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
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userFirstName")==null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		try {
			String ssoid = (String) session.getAttribute("userId");
			InstructorDao instDao = new InstructorDao();
			Instructor inst = instDao.getInstructorBySsoId(ssoid);
			request.setAttribute("attrFname",inst.getFname());
			request.setAttribute("attrLname",inst.getLname());
			request.setAttribute("attrEmail",inst.getEmail());
			request.setAttribute("attrDept",inst.getDept());
				
		} catch( Exception ex) {
			ex.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
