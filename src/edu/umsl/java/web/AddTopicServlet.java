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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicDao topicDao = null;
		int courseId = 0;
		String topicCont = request.getParameter("topicCont");
		String courseIdString = request.getParameter("s_course");
		
		//System.out.print("in do Post method");
		//System.out.println("topicCont: " + topicCont);
		//System.out.println("courseIdString: " + courseIdString);
		
		int err = 0;
		if (topicCont == null || topicCont.length() == 0 || courseIdString == null || courseIdString.length() == 0) {
			err = 1;
			//System.out.print("ERROR");
		} else {
			try {
				topicDao = new TopicDao();
				courseId = Integer.parseInt(courseIdString);
				//System.out.print("SUCCESS");
				topicDao.addTopic(topicCont, courseId);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		response.sendRedirect("Topic?err=" + err);
	}

}
