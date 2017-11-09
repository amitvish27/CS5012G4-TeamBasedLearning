package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.TopicDao;

/**
 * Servlet implementation class SaveTopicServlet
 */
@WebServlet("/SaveTopic")
public class SaveTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");

		TopicDao topicDao= null;

		try {
			topicDao = new TopicDao();
			
			int id = Integer.parseInt(request.getParameter("id"));

			topicDao.saveTopic(title, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.println("");
		out.flush();
		out.close();

	}

}
