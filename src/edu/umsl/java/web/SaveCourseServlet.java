package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.dao.CourseDao;

/**
 * Servlet implementation class SaveCourseServlet
 */
@WebServlet("/SaveCourse")
public class SaveCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		CourseDao courseDao = null;

		try {
			String code = request.getParameter("code");
			String title = request.getParameter("title");
			int year = Integer.parseInt(request.getParameter("year"));
			String semester = request.getParameter("semester");
			String instructor = (String) session.getAttribute("userId");
			courseDao = new CourseDao();

			int id = Integer.parseInt(request.getParameter("id"));
			courseDao.saveCourse(id, code, title, year, semester, instructor);;
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("");
		out.flush();
		out.close();

	}
}
