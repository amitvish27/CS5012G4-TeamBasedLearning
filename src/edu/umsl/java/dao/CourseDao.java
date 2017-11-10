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
	private PreparedStatement courseById;
	private PreparedStatement pageresults;
	private PreparedStatement addCourse;
	private PreparedStatement delCourse;
	private PreparedStatement saveCourse;
	private PreparedStatement countCourse;
	private PreparedStatement setInstructor;
	private List<Course> courseList;

	public CourseDao() throws Exception {
		try {
			ReadProperties.loadPropertiesFile();
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(), ReadProperties.getDbPswd());

			results = connection.prepareStatement("SELECT id, code, title, year, semester, instructor "
					+ "FROM course WHERE deleted=0 " + "ORDER BY created DESC ");

			courseById = connection.prepareStatement("SELECT id, code, title, year, semester, instructor "
					+ "FROM course WHERE deleted=0 " + "AND id=?");

			pageresults = connection.prepareStatement("SELECT id, code, title, year, semester, instructor "
					+ "FROM course WHERE deleted=0 " + "ORDER BY created DESC LIMIT ?, ?");

			addCourse = connection.prepareStatement(
					"INSERT INTO Course (code, title, year, semester, instructor) " + "VALUES (?, ?, ?, ?, ?)");

			delCourse = connection.prepareStatement("UPDATE course SET deleted = 1 WHERE id = ?");
			
			saveCourse = connection.prepareStatement("UPDATE course SET code=?, title=?, year=?, semester=?, instructor=? WHERE id = ?");

			countCourse = connection.prepareStatement("SELECT COUNT(id) FROM course WHERE deleted = 0");

			setInstructor = connection.prepareStatement("SELECT id, code, title, year, semester "
					+ "FROM course WHERE instructor=? " + "ORDER BY created DESC");

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
		
		Course course = null;
		try {
			courseById.setInt(0, id);
			ResultSet res = courseById.executeQuery();
			res.next();
			
			course = new Course();
			course.setId(res.getInt(1));
			course.setCode(res.getString(2));
			course.setTitle(res.getString(3));
			course.setYear(res.getInt(4));
			course.setSemester(res.getString(5));
			course.setInstructor(res.getString(6));
			
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		return course;
	}

	public List<Course> getCoursesListByPage(int pg) throws SQLException {
		List<Course> courses_List = new ArrayList<Course>();
		int st = 10 * (pg - 1); // st->starting point ; pg -> page
		try {
			pageresults.setInt(1, st);
			pageresults.setInt(2, 10);
			ResultSet resultsRs = pageresults.executeQuery();
			while (resultsRs.next()) {
				Course course = new Course();
				// id, code, title, year, semester, instructor
				course.setId(resultsRs.getInt(1));
				course.setCode(resultsRs.getString(2));
				course.setTitle(resultsRs.getString(3));
				course.setYear(resultsRs.getInt(4));
				course.setSemester(resultsRs.getString(5));
				course.setInstructor(resultsRs.getString(6));
				courses_List.add(course);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return courses_List;
	}

	public void saveCourse(int id, String courCode, String courTitle, int courYear, String seme, String instID)
			throws SQLException {
		try {
			// UPDATE course SET code=?, title=?, year=?, semester=?, instructor=? WHERE id = ?
			saveCourse.setString(1, courCode);
			saveCourse.setString(2, courTitle);
			saveCourse.setInt(3, courYear);
			saveCourse.setString(4, seme);
			saveCourse.setString(5, instID);
			saveCourse.setInt(6, id);
			saveCourse.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public void addCourse(String courCode, String courTitle, int courYear, String seme, String instID)
			throws SQLException {
		try {
			// code, title, year, semester, instructor
			addCourse.setString(1, courCode);
			addCourse.setString(2, courTitle);
			addCourse.setInt(3, courYear);
			addCourse.setString(4, seme);
			addCourse.setString(5, instID);
			addCourse.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public int getCoursesCount() {
		int cnt = 0;// cnt: count
		try {
			ResultSet rs = countCourse.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return cnt;
	}

	public void delCourseById(int cid) {
		try {
			delCourse.setInt(1, cid);
			delCourse.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	/*public Course getCourseById(int id) {
		for (Course course : courseList) {
			if (course.getId() == id)
				return course;
		}
		return null;
	}*/

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
		/*
		 * try { results.close(); setInstructor.close(); connection.close(); } catch
		 * (SQLException sql_ex) { sql_ex.printStackTrace(); }
		 */
	}

	public List<Integer> getDistinctYear(List<Course> list) {
		List<Integer> tempList = new ArrayList<>();
		for (Course c : list) {
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
		for (Course c : list) {
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
		for (Course c : courseList) {
			if (c.getYear() == year) {
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
		for (Course c : courseList) {
			if (c.getYear() == year && c.getSemester() == semester) {
				tempList.add(c);
			}
		}
		return tempList;
	}
}
