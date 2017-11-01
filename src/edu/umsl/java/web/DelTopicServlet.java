package edu.umsl.java.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.TopicDao;

/**
 * Servlet implementation class DelTopicServlet
 */
@WebServlet("/DelTopic")
public class DelTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicDao topicDao = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			topicDao = new TopicDao();
			topicDao.delTopicById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("Topic?err=" + 2);
		}
		response.sendRedirect("Topic");
	}
}
