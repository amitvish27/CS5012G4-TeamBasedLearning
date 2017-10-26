package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.java.beans.Course;

public class CourseDao {
	private Connection connection;
	private PreparedStatement results;
	
	public CourseDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs5012g4db", "root", "");
			
			results = connection.prepareStatement(
					"SELECT id, name, year, semester, instructor " + "FROM course ORDER BY name DESC ");
			
		} catch( Exception ex )
		{
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}
	
	public List<Course> getCourseList() {
		List<Course> courseList = new ArrayList<Course>();
		
		try {
			ResultSet res = results.executeQuery();
			
			while( res.next() ) {
				Course course = new Course();
				course.setId(res.getInt(1));
				course.setName(res.getString(2));
				course.setYear(res.getInt(3));
				course.setSemester(res.getInt(4));
				course.setInstructor(res.getInt(5));
				courseList.add(course);
			}
				
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		
		return courseList;
		
	}
	
	protected void finalize() {
		try {
			results.close();
		}
		catch( SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

}
