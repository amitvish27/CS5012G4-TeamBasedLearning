package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.CourseDao;

/**
 * Servlet implementation class DelCourseServlet
 */
@WebServlet("/DelCourse")
public class DelCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseDao cous_dao = null;
		try
		{
			int cid = Integer.parseInt(request.getParameter("cid"));
			int curpag = Integer.parseInt(request.getParameter("curpg"));
			cous_dao = new CourseDao();
			cous_dao.delCourseById(cid);
			response.sendRedirect("Course?pg=" + curpag);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			response.sendRedirect("Course");
		}
	}

}
