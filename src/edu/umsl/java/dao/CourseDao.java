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
import edu.umsl.java.util.ReadProperties;

public class CourseDao {
	private Connection connection;
	private PreparedStatement results;
	private PreparedStatement setInstructor;
	private List<Course> courseList;
	private ReadProperties rp;
	
	public CourseDao() throws Exception {
		try {
			rp = new ReadProperties();
			Class.forName(rp.getDbDriver());
			connection = DriverManager.getConnection(rp.getDbUrl(), rp.getDbUser(), rp.getDbPswd());

			results = connection.prepareStatement(
					"SELECT id, code, title, year, semester, instructor " 
							+ "FROM course WHERE deleted=0 "
							+ "ORDER BY created DESC ");

			setInstructor = connection.prepareStatement(
					"SELECT id, code, title, year, semester " + "FROM course WHERE instructor=? " + "ORDER BY created DESC");
			
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
				course.setCode(res.getString(2));
				course.setTitle(res.getString(3));
				course.setYear(res.getInt(4));
				course.setSemester(res.getString(5));
				course.setInstructor(res.getString(6));
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
				course.setCode(res.getString(2));
				course.setTitle(res.getString(3));
				course.setYear(res.getInt(4));
				course.setSemester(res.getString(5));
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
	
	public List<Integer> getDistinctYear(List<Course> list) {
		List<Integer> tempList = new ArrayList<>();
		for (Course c: list) {
			tempList.add(c.getYear());
		}
		Set<Integer> set = new HashSet<>();
		set.addAll(tempList);
		tempList.clear();
		tempList.addAll(set);
		return tempList;
	}
	
	public List<String> getDistinctSemester(List<Course> list) {
		List<String> tempList = new ArrayList<>();
		for (Course c: list) {
			tempList.add(c.getSemester());
		}
		Set<String> set = new HashSet<>();
		set.addAll(tempList);
		tempList.clear();
		tempList.addAll(set);
		return tempList;
	}
	
	public List<String> getSemesterByInstructor(int year) {
		List<String> tempList = new ArrayList<>();
		for (Course c: courseList) {
			if(c.getYear()==year) {
				tempList.add(c.getSemester());
			}
		}
		Set<String> set = new HashSet<>();
		set.addAll(tempList);
		tempList.clear();
		tempList.addAll(set);
		return tempList;
	}
	
	public List<Course> getCourseByInstructor(int year, String semester) {
		List<Course> tempList = new ArrayList<>();
		for (Course c: courseList) {
			if(c.getYear()==year && c.getSemester()==semester) {
				tempList.add(c);
			}
		}
		return tempList;
	}
}
