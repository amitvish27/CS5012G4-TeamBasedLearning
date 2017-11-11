package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.Course;
import edu.umsl.java.beans.Topic;
import edu.umsl.java.dao.CourseDao;
import edu.umsl.java.dao.TopicDao;

/**
 * Servlet implementation class TopicServlet
 */
@WebServlet("/Topic")
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("userFirstName") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		TopicDao topicDao = null;
		CourseDao courseDao = null;

		int pg = 0;
		String initpg = request.getParameter("pg");
		String userId;
		
		if (initpg != null) {
			try {
				pg = Integer.parseInt(initpg);
			} catch (NumberFormatException ex) {
				pg = 1;
			}
		}

		try {
			int count;
			userId = String.valueOf(session.getAttribute("userId"));
			boolean checkcreatedbyme = request.getParameter("s_createdbyme")!=null;
			
			topicDao = new TopicDao();
			courseDao = new CourseDao();
			
			if(checkcreatedbyme)
			{
				courseDao.setCourseInstructor(userId);
				List<Course> courseListByInstructor = courseDao.getCourseListByInstructor();
				request.setAttribute("courseList", courseListByInstructor);
			}
			else {
				List<Course> courseList = courseDao.getCourseList();
				request.setAttribute("courseList", courseList);
				count = topicDao.getTopicCount();

				int totalpg = (int) Math.ceil(count / 10.0);
				request.setAttribute("maxpg", totalpg);

				if (pg < 1) {
					pg = 1;
				} else if (pg > totalpg) {
					pg = totalpg;
				}

				request.setAttribute("crtpg", pg);

				List<Topic> topicList = topicDao.getTopicListByPage(pg);
				request.setAttribute("topicList", topicList);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("topicslist.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
