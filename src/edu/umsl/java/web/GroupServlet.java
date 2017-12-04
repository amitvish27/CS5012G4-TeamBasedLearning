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
import javax.servlet.http.HttpSession;

import edu.umsl.java.dao.GroupDao;

/**
 * Servlet implementation class GroupServlet
 */
@WebServlet("/Group")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("group.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String task = ((String) request.getParameter("task")!=null) ? (String) request.getParameter("task"): "getCourse";
		String instssoid = (String) session.getAttribute("userId");
		int courseId, groupId, groupNumber;
		JsonObject jsonObject = null;
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();

		GroupDao groupDao = new GroupDao();
		switch(task) {
		case "getCourse":
			try {
				jsonArry = groupDao.getCourseList();
				jsonObject = Json.createObjectBuilder().add("courseList", jsonArry).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "getGroupsInCourse":
			String courseIdStr = request.getParameter("courseid");
			if (!courseIdStr.isEmpty()) {
				courseId = Integer.parseInt(courseIdStr);
				jsonArry = groupDao.getGroupsInCourse(courseId);
				jsonObject = Json.createObjectBuilder().add("groupList", jsonArry).build();	
			}
			break;
		case "addGroupsInCourse":
			groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
			courseId = Integer.parseInt(request.getParameter("courseid"));
			groupId = groupDao.addGroupInCourse(courseId, groupNumber, instssoid);
			
			jsonObject = Json.createObjectBuilder()
						.add("groupId", groupId)
						.add("groupNumber", groupNumber).build();
			
			break;
		case "updateGroupsInCourse":
			groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
			groupId = Integer.parseInt(request.getParameter("groupId"));
			courseId = Integer.parseInt(request.getParameter("courseid"));
			groupDao.updateGroupInCourse(courseId, groupId, groupNumber, instssoid);
			break;
		case "delGroupsInCourse":
			groupId = Integer.parseInt(request.getParameter("groupId"));
			groupDao.delGroupInCourse(groupId);
			break;
		case "getStudentsInGroups":
			groupId=Integer.parseInt(request.getParameter("groupId"));
			jsonArry = groupDao.getStudentsInGroup(groupId);
			jsonObject = Json.createObjectBuilder().add("studentList", jsonArry).build();
			break;
		case "getStudentsNotInGroups":
			groupId=Integer.parseInt(request.getParameter("groupId"));
			jsonArry = groupDao.getStudentsNotInGroup(groupId);
			jsonObject = Json.createObjectBuilder().add("studentList", jsonArry).build();
			break;
		case "addStudentsInGroup":
			groupId=Integer.parseInt(request.getParameter("groupId"));
			String[] listStudents = request.getParameterValues("studentList[]");
			if( listStudents!=null)
			{
				groupDao.addStudentsInGroup(groupId, listStudents);
			}
			jsonArry = groupDao.getStudentsInGroup(groupId);
			jsonObject = Json.createObjectBuilder().add("studentList", jsonArry).build();
			break;
		case "delStudentsInGroup":
			int s_relnid = Integer.parseInt(request.getParameter("s_relnid"));
			groupDao.delStudentsInGroup(s_relnid);
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
