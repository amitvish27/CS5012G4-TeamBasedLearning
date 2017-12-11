package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.CourseBean;
import edu.umsl.java.dao.CourseDao;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/Course")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String idsearchtext = request.getParameter("search");
		idsearchtext = (idsearchtext==null)?"":idsearchtext ; 
		
		CourseDao courseDao = null;

		int pg = 0;
		String initpg = request.getParameter("pg");

		if (initpg != null) {
			try {
				pg = Integer.parseInt(initpg);
			} catch (Exception e) {
				pg = 1;
			}
		}
		if (pg == 0) {
			pg = 1;
		}

		try {
			courseDao = new CourseDao();
			int cnt = courseDao.getCoursesCount();
			int totalpg = (int) Math.ceil(cnt / 10.0);

			//List<CourseBean> courses_List = courseDao.getCoursesListByPage(pg);
			
			//Entries per page
			String recPerPgStr = (String) request.getParameter("ent");
			int entries = (recPerPgStr!=null)? Integer.parseInt(recPerPgStr):10;
			
			// Sorting page by
			String sortBy= (String) request.getParameter("sort");
			String sortDir= (String) request.getParameter("dir");
			if(sortBy!=null) {
				if(sortDir==null) {
					sortDir="ASC";
				}
			}
			else{
				sortBy="created"; //default
				sortDir="ASC"; // default
			}
			
			if (pg < 1) {
				pg = 1;
			} else if (pg > totalpg) {
				pg = totalpg;
			}

			List<CourseBean> courses_List = courseDao.getCourseList(sortBy, sortDir, idsearchtext, pg, entries);
			
			request.setAttribute("maxpg", totalpg);
			request.setAttribute("courses_List", courses_List);
			request.setAttribute("crtpg", pg);
			request.setAttribute("sortBy", sortBy);
			request.setAttribute("sortDir", sortDir);
			request.setAttribute("recPerPgStr", entries);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("courselist.jsp");
		dispatcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonObject jsonObject = null;
		HttpSession session = request.getSession();
		
		String task = ((String) request.getParameter("task")!=null) ? (String) request.getParameter("task"): "importCourse";
		String instssoid = (String) session.getAttribute("userId");
		
		switch(task) {
		case "importCourse":
			int courseId = Integer.parseInt(request.getParameter("courseid"));
			if(courseId>0) {
				CourseDao cdao;
				try {
					cdao = new CourseDao();
					cdao.importCourse(courseId, instssoid);
					jsonObject = Json.createObjectBuilder()
							.add("success","success")
							.build();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
