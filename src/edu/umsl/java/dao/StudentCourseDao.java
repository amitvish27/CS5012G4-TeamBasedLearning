package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import edu.umsl.java.beans.CourseBean;
import edu.umsl.java.util.ReadProperties;

public class StudentCourseDao {
private Connection connection;
	
	public StudentCourseDao() {
		ReadProperties.loadPropertiesFile();
		try {
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JsonArrayBuilder getCourseList() throws Exception {
		JsonArrayBuilder jsonCourseAry = Json.createArrayBuilder();
		for(CourseBean c: new CourseDao().getCourseList()) {
			jsonCourseAry.add(Json.createObjectBuilder().add("id", c.getId()).add("code", c.getCode()).add("title",
					c.getTitle()));
		}
		return jsonCourseAry;
	}
	
	public JsonArrayBuilder getStudentsInCourse(int courseId) {
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();
		
		String query = "SELECT relnid, studentid, fname, lname "
				+ "FROM student_course as s, user as u "
				+ "WHERE s.courseid="+courseId+" AND s.studentid=u.ssoid AND u.role=0 AND s.deleted=0";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				jsonArry.add(Json.createObjectBuilder()
							.add("relnid", res.getString("relnid"))
							.add("studentid", res.getString("studentid"))
							.add("fname", res.getString("fname"))
							.add("lname", res.getString("lname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jsonArry;
	}
	
	public JsonArrayBuilder getStudentsNotInCourse(int courseId) {
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();
		
		String query = "SELECT u.ssoid, u.fname, u.lname "
				+ "FROM user AS u WHERE u.ssoid NOT IN "
				+ "(SELECT g.studentid FROM student_course g WHERE g.courseid="+courseId+") AND u.role=0 "
						+ "ORDER BY u.ssoid ASC";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				jsonArry.add(Json.createObjectBuilder()
							.add("studentid", res.getString("ssoid"))
							.add("fname", res.getString("fname"))
							.add("lname", res.getString("lname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jsonArry;
	}
	
	public void addStudentsInCourse(int courseId, String[] listStudents) {
		String insertQuery = "";
		PreparedStatement query ;
		try {
			for(String stdid : listStudents) {
				insertQuery = "INSERT INTO student_course (studentid, courseid) VALUES ('"+stdid+"', '"+courseId+"');";
				query = connection.prepareStatement(insertQuery);
				query.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void delStudentsInCourse(int s_relnid) {
		String delQuery = "UPDATE student_course SET deleted=1 WHERE relnid="+s_relnid;
		PreparedStatement query;
		try {
			query = connection.prepareStatement(delQuery);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
