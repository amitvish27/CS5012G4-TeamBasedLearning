package edu.umsl.java.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.TopicDao;

/**
 * Servlet implementation class AddTopicServlet
 */
@WebServlet("/AddTopic")
public class AddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicDao topicDao = null;
		int courseId = 0;
		String topicCont = request.getParameter("topicCont");
		String courseIdString = request.getParameter("s_course");
		
		int err = 0;
		if (topicCont == null || topicCont.length() == 0 || courseIdString == null || courseIdString.length() == 0) {
			err = 1;
		} else {
			try {
				topicDao = new TopicDao();
				courseId = Integer.parseInt(courseIdString);
				topicDao.addTopic(topicCont, courseId);

			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("Topic?err=" + 2 );
			}
		}
	
		response.sendRedirect("Topic?err=" + err );
	}

}
