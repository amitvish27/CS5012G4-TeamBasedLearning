package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		try {
			JsonObject jsonObject = getTopicJson(session, request);
			int count = jsonObject.getInt("countRecord");
			int pg = jsonObject.getInt("pg");
			int totalpg = (int) Math.ceil(count / 10.0);
			request.setAttribute("maxpg", totalpg);
			if (pg < 1) {
				pg = 1;
			} else if (pg > totalpg) {
				pg = totalpg;
			}
			request.setAttribute("crtpg", pg);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("topicslist.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userFirstName") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		String task = ((String) request.getParameter("task")!=null) ? (String) request.getParameter("task"): "importCourse";
		String instssoid = (String) session.getAttribute("userId");
		JsonObject jsonObject = null;
		switch(task) {
		case "importTopic":
			try {
				int topicid = Integer.parseInt(request.getParameter("topicid"));
				if(topicid>0) {
					new TopicDao().importTopic(topicid, instssoid);
					jsonObject = Json.createObjectBuilder()
							.add("success","success")
							.build();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			break;
		default:
			jsonObject = getTopicJson(session, request);
			break;
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}

	protected JsonObject getTopicJson(HttpSession session, HttpServletRequest request) {
		String instssoid = (String) session.getAttribute("userId");
		
		int pg = 0, pgSize = 10;
		String initpg = (request.getParameter("pg") != null) ? request.getParameter("pg") : "1";
		String userId;
		String sortColName = (request.getParameter("sortColName") != null) ? request.getParameter("sortColName")
				: "created";
		String sortDir = (request.getParameter("sortDir") != null) ? request.getParameter("sortDir") : "ASC";
		sortDir = (sortColName.equals("created"))?"DESC":sortDir;
		String s_course_year = (request.getParameter("s_course_year") != null) ? request.getParameter("s_course_year")
				: "";
		String s_course_semester = (request.getParameter("s_course_semester") != null)
				? request.getParameter("s_course_semester")
				: "";
		String s_course = (request.getParameter("s_course") != null) ? request.getParameter("s_course") : "";
		String searchText = (request.getParameter("searchText") != null) ? request.getParameter("searchText") : "";
		
		if (initpg != null) {
			try {
				pg = Integer.parseInt(initpg);
				pgSize = Integer.parseInt(request.getParameter("pgSize"));
			} catch (NumberFormatException ex) {
				pg = 1;
			}
		}

		JsonObject jsonObject = null;
		
		try {
			TopicDao topicDao = new TopicDao();
			userId = String.valueOf(session.getAttribute("userId"));
			String[] searchColumn = {""};
			String[] searchValue = {""};
			
			boolean checkcreatedbyme = Boolean.parseBoolean(request.getParameter("s_createdbyme"));
			if ((checkcreatedbyme)) {
				searchColumn[0] = "instructorid";
				searchValue[0] = userId;
			}
			if(!s_course.equals("")) {
				searchColumn[0] = "courseid";
				searchValue[0] = s_course;
			} 
			if(!searchText.isEmpty()) {
				searchColumn[0] = "title";
				searchValue[0] = searchText;
			}
			
			jsonObject = topicDao.getTopicJson(instssoid, sortColName, sortDir, pg, pgSize, searchColumn, searchValue, s_course_year, s_course_semester);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
