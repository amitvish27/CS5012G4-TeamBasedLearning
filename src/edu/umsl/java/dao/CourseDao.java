package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.UnavailableException;

import edu.umsl.java.beans.Course;

public class CourseDao {
	private Connection connection;
	private PreparedStatement results;
	private PreparedStatement setInstructor;
	private List<Course> courseList;
	
	public CourseDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs5012g4db", "root", "");

			results = connection.prepareStatement(
					"SELECT id, name, year, semester, instructor " + "FROM course ORDER BY name DESC ");

			setInstructor = connection.prepareStatement(
					"SELECT id, name, year, semester " + "FROM course WHERE instructor=? " + "ORDER BY name DESC");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}
	
	public List<Course> getCourseList() {
		List<Course> courseList = new ArrayList<Course>();

		try {
			ResultSet res = results.executeQuery();

			while (res.next()) {
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
	
	public Course getCourseById(int id) {
		for (Course course : courseList) {
			if(course.getId() == id)
				return course;
		}
		return null;
	}

	public void setCourseInstructor(String instructor) {
		try {
			setInstructor.setString(1, instructor);
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public List<Course> getCourseListByInstructor() {
		courseList = new ArrayList<Course>();

		try {
			ResultSet res = setInstructor.executeQuery();

			while (res.next()) {
				Course course = new Course();
				course.setId(res.getInt(1));
				course.setName(res.getString(2));
				course.setYear(res.getInt(3));
				course.setSemester(res.getInt(4));
				courseList.add(course);
			}

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

		return courseList;

	}

	protected void finalize() {
		/*try {
			results.close();
			setInstructor.close();
			connection.close();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}*/
	}
	
	public List<Integer> getYearByInstructor() {
		List<Integer> tempList = new ArrayList<>();
		for (Course c: courseList) {
			tempList.add(c.getYear());
		}
		Set<Integer> set = new HashSet<>();
		set.addAll(tempList);
		tempList.clear();
		tempList.addAll(set);
		return tempList;
	}
	
	public List<Integer> getSemesterByInstructor(int year) {
		List<Integer> tempList = new ArrayList<>();
		for (Course c: courseList) {
			if(c.getYear()==year) {
				tempList.add(c.getSemester());
			}
		}
		Set<Integer> set = new HashSet<>();
		set.addAll(tempList);
		tempList.clear();
		tempList.addAll(set);
		return tempList;
	}
	
	public List<Course> getCourseByInstructor(int year, int semester) {
		List<Course> tempList = new ArrayList<>();
		for (Course c: courseList) {
			if(c.getYear()==year && c.getSemester()==semester) {
				tempList.add(c);
			}
		}
		return tempList;
	}
}
