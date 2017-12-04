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

import edu.umsl.java.beans.CourseBean;
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
	private List<CourseBean> courseList;

	public CourseDao() throws Exception {
		try {
			ReadProperties.loadPropertiesFile();
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());

			results = connection.prepareStatement("SELECT id, code, title, year, semester, instructor "
					+ "FROM course WHERE deleted=0 " + "ORDER BY created DESC ");

			courseById = connection.prepareStatement("SELECT id, code, title, year, semester, instructor "
					+ "FROM course WHERE deleted=0 " + "AND id=?");

			pageresults = connection.prepareStatement("SELECT id, code, title, year, semester, instructor "
					+ "FROM course WHERE deleted=0 " + "ORDER BY created DESC LIMIT ?, ?");

			addCourse = connection.prepareStatement(
					"INSERT INTO Course (code, title, year, semester, instructor) " + "VALUES (?, ?, ?, ?, ?)");

			delCourse = connection.prepareStatement("UPDATE course SET deleted = 1 WHERE id = ?");

			saveCourse = connection.prepareStatement(
					"UPDATE course SET code=?, title=?, year=?, semester=?, instructor=? WHERE id = ?");

			countCourse = connection.prepareStatement("SELECT COUNT(id) FROM course WHERE deleted = 0");

			setInstructor = connection.prepareStatement("SELECT id, code, title, year, semester "
					+ "FROM course WHERE instructor=? " + "ORDER BY created DESC");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}

	public List<CourseBean> getCourseList() {
		List<CourseBean> courseList = new ArrayList<CourseBean>();

		try {
			ResultSet res = results.executeQuery();

			while (res.next()) {
				CourseBean course = new CourseBean();
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

	public CourseBean getCourseById(int id) {

		CourseBean course = null;
		try {
			courseById.setInt(0, id);
			ResultSet res = courseById.executeQuery();
			res.next();

			course = new CourseBean();
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

	public List<CourseBean> getCoursesListByPage(int pg) throws SQLException {
		List<CourseBean> courses_List = new ArrayList<CourseBean>();
		int st = 10 * (pg - 1); // st->starting point ; pg -> page
		try {
			pageresults.setInt(1, st);
			pageresults.setInt(2, 10);
			ResultSet resultsRs = pageresults.executeQuery();
			while (resultsRs.next()) {
				CourseBean course = new CourseBean();
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
			// UPDATE course SET code=?, title=?, year=?, semester=?, instructor=? WHERE id
			// = ?
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

	/*
	 * public Course getCourseById(int id) { for (Course course : courseList) { if
	 * (course.getId() == id) return course; } return null; }
	 */

	public void setCourseInstructor(String instructor) {
		try {
			setInstructor.setString(1, instructor);
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public List<CourseBean> getCourseListByInstructor() {
		courseList = new ArrayList<CourseBean>();

		try {
			ResultSet res = setInstructor.executeQuery();

			while (res.next()) {
				CourseBean course = new CourseBean();
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

		try {
			results.close();
			setInstructor.close();
			connection.close();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

	}

	public List<Integer> getDistinctYear(List<CourseBean> list) {
		List<Integer> tempList = new ArrayList<>();
		for (CourseBean c : list) {
			tempList.add(c.getYear());
		}
		Set<Integer> set = new HashSet<>();
		set.addAll(tempList);
		tempList.clear();
		tempList.addAll(set);
		return tempList;
	}

	public List<String> getDistinctSemester(List<CourseBean> list) {
		List<String> tempList = new ArrayList<>();
		for (CourseBean c : list) {
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
		for (CourseBean c : courseList) {
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

	public List<CourseBean> getCourseByInstructor(int year, String semester) {
		List<CourseBean> tempList = new ArrayList<>();
		for (CourseBean c : courseList) {
			if (c.getYear() == year && c.getSemester() == semester) {
				tempList.add(c);
			}
		}
		return tempList;
	}

	//TODO: wip
	public List<CourseBean> getCourseList(String col, String dir, String searchText, int start, int end) {
		List<CourseBean> courseList = new ArrayList<CourseBean>();
		col = (col.equals("")) ? "created" : col;
		dir = (dir.equals("")) ? "ASC" : dir;
		searchText = "'%" + searchText + "%'";
		//"SELECT id, code, title, year, semester, instructor "
		//+ "FROM course WHERE deleted=0 " + "ORDER BY created DESC LIMIT ?, ?"
		String query = "SELECT id, code, title, year, semester, instructor FROM course "
						+ "WHERE deleted=0 AND (id LIKE "+searchText+" OR code LIKE "+searchText+" OR title LIKE "+searchText+" "
						+ "OR year LIKE "+searchText+" OR semester LIKE "+searchText+" OR instructor LIKE "+searchText+") "
						+ "ORDER BY "+col+" "+dir+"  LIMIT "+start+", "+end;
		
		try {
			PreparedStatement getRecords_sorted = connection.prepareStatement(query);
			ResultSet results = getRecords_sorted.executeQuery();
			while (results.next()) {
				CourseBean course = new CourseBean();
				course.setId(results.getInt(1));
				course.setCode(results.getString(2));
				course.setTitle(results.getString(3));
				course.setYear(results.getInt(4));
				course.setSemester(results.getString(5));
				course.setInstructor(results.getString(6));
				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

}