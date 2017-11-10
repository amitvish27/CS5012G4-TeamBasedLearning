package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.CourseDao;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourse")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CourseDao courdao = null;

		String courCode = request.getParameter("course_code");
		String courTitle = request.getParameter("course_title");
		String seme = request.getParameter("course_semester");
		int courYear = Integer.parseInt(request.getParameter("course_year"));
		String instr_ID = request.getParameter("instructor_id");

		int err = 0;

		if (courCode == null || courTitle == null || instr_ID == null) {
			err = 1;
		} else {
			try {
				courdao = new CourseDao();
				courdao.addCourse(courCode, courTitle, courYear, seme, instr_ID);
			} catch (Exception e) {
				err = 1;
				e.printStackTrace();
			}
		}

		response.sendRedirect("Course?err=" + err);
	}

}
