package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import edu.umsl.java.dao.StudentCourseDao;

/**
 * Servlet implementation class StudentCourseServlet
 */
@WebServlet("/StudentCourse")
public class StudentCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentcourse.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//String instssoid = (String) session.getAttribute("userId");
		
		String task = ((String) request.getParameter("task")!=null) ? (String) request.getParameter("task"): "getCourse";
		int courseId;
		JsonObject jsonObject = null;
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();

		StudentCourseDao studentcourseDao = new StudentCourseDao();
		switch(task) {
		case "getCourse":
			try {
				jsonArry = studentcourseDao.getCourseList();
				jsonObject = Json.createObjectBuilder().add("courseList", jsonArry).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "getStudentsInCourse":
			courseId=Integer.parseInt(request.getParameter("courseid"));
			jsonArry = studentcourseDao.getStudentsInCourse(courseId);
			jsonObject = Json.createObjectBuilder().add("studentList", jsonArry).build();
			break;
		case "getStudentsNotInCourse":
			courseId=Integer.parseInt(request.getParameter("courseid"));
			jsonArry = studentcourseDao.getStudentsNotInCourse(courseId);
			jsonObject = Json.createObjectBuilder().add("studentList", jsonArry).build();
			break;
		case "addStudentsInCourse":
			courseId=Integer.parseInt(request.getParameter("courseid"));
			String[] listStudents = request.getParameterValues("studentList[]");
			if( listStudents!=null)
			{
				studentcourseDao.addStudentsInCourse(courseId, listStudents);
			}
			jsonArry = studentcourseDao.getStudentsInCourse(courseId);
			jsonObject = Json.createObjectBuilder().add("studentList", jsonArry).build();
			break;
		case "delStudentsInCourse":
			int s_relnid = Integer.parseInt(request.getParameter("s_relnid"));
			studentcourseDao.delStudentsInCourse(s_relnid);
			break;
		default:
			System.out.println("default in task : " + task);
			break;
		}
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
		
	}
    

}
