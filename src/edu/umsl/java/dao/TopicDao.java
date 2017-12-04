package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.UnavailableException;

import edu.umsl.java.beans.CourseBean;
import edu.umsl.java.beans.TopicBean;
import edu.umsl.java.util.ReadProperties;

public class TopicDao {
	private Connection connection;
	private PreparedStatement results;
	private PreparedStatement topiccnt;
	private PreparedStatement addTopic;
	private PreparedStatement delTopic;
	private PreparedStatement getTopic;
	private PreparedStatement saveTopic;

	public TopicDao() throws Exception {
		try {
			ReadProperties.loadPropertiesFile();
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());

			results = connection.prepareStatement("SELECT id, title, courseid, instructorid "
					+ "FROM topic WHERE deleted=0 ORDER BY created DESC LIMIT ?, ? ");

			topiccnt = connection.prepareStatement("SELECT COUNT(id) FROM topic " + "WHERE deleted=0");

			addTopic = connection
					.prepareStatement("INSERT INTO topic (title, courseid, instructorid) " + "VALUES (?,?,?)");

			getTopic = connection.prepareStatement("SELECT title FROM topic WHERE id = ? AND deleted = 0");

			saveTopic = connection.prepareStatement("UPDATE topic SET title = ? WHERE id = ? AND deleted = 0");

			delTopic = connection.prepareStatement("UPDATE topic SET deleted = 1 WHERE id = ?");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}

	public int getTopicCount() {
		int count = 0;

		try {
			ResultSet res = topiccnt.executeQuery();
			res.next();
			count = res.getInt(1);
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

		return count;
	}

	public List<TopicBean> getTopicListByPage(int pg) {
		List<TopicBean> topicList = new ArrayList<TopicBean>();

		int st = 10 * (pg - 1);

		try {
			results.setInt(1, st);
			results.setInt(2, 10);

			ResultSet res = results.executeQuery();

			while (res.next()) {
				TopicBean topic = new TopicBean();

				topic.setId(res.getInt(1));
				topic.setTitle(res.getString(2));
				topic.setCourseid(res.getInt(3));
				topic.setInstructorid(res.getString(4));

				topicList.add(topic);
			}

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

		return topicList;
	}

	public void addTopic(String topicCont, int courseId, String ssoId) {
		try {
			addTopic.setString(1, topicCont);
			addTopic.setInt(2, courseId);
			addTopic.setString(3, ssoId);

			addTopic.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public String getTopicById(int courseId) {
		String str = "";
		try {
			getTopic.setInt(1, courseId);

			ResultSet rs = getTopic.executeQuery();
			rs.next();

			str = rs.getString(1);

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		return str;
	}

	public void saveTopic(String title, int id) {
		try {
			saveTopic.setString(1, title);
			saveTopic.setInt(2, id);

			saveTopic.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public void delTopicById(int id) {
		try {
			delTopic.setInt(1, id);

			delTopic.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public JsonObject getTopicJson(String sortColName, String sortDir, int initpg, int pgSize, String[] searchColumn,
			String[] searchValue, String s_course_year, String s_course_semester) throws Exception {
		JsonObject result = null;
		String searchSQL = "";
		int st = 10 * (initpg - 1);

		searchSQL = "SELECT id, title, courseid, instructorid " + "FROM topic WHERE deleted=0 ";
		String searchBy = "";

		String orderBy = " ORDER BY " + sortColName + " " + sortDir;
		String limit = " LIMIT " + st + ", " + pgSize;

		for (int i = 0; i < searchColumn.length; i++) {
			if (!(searchColumn[i].equals("") || searchValue[i].equals(""))) {
				searchSQL += searchBy + " AND " + searchColumn[i] + " like '%" + searchValue[i] + "%'";
			}
		}

		searchSQL += orderBy + limit;
		PreparedStatement searchStmt;
		ResultSet rs;
		JsonArrayBuilder jsonTopicAry = Json.createArrayBuilder();

		searchStmt = connection.prepareStatement(searchSQL);
		rs = searchStmt.executeQuery();
		int countRecord = 0;// count total records with the result without the limit
		while (rs.next()) {
			jsonTopicAry.add(Json.createObjectBuilder().add("id", rs.getInt("id")).add("title", rs.getString("title"))
					.add("courseid", rs.getInt("courseid")).add("instructorid", rs.getString("instructorid")));
		}
		// count total number of record for pagination;
		searchSQL = "SELECT COUNT(*) as count " + "FROM topic WHERE deleted=0 ";
		for (int i = 0; i < searchColumn.length; i++) {
			if (!(searchColumn[i].equals("") || searchValue[i].equals(""))) {
				searchSQL += searchBy + " AND " + searchColumn[i] + " like '%" + searchValue[i] + "%'";
			}
		}

		searchStmt = connection.prepareStatement(searchSQL);
		rs = searchStmt.executeQuery();
		rs.next();
		countRecord = rs.getInt("count");
		int totalpg = (int) Math.ceil(countRecord / 10.0);
		searchStmt.close();
		rs.close();

		JsonArrayBuilder jsonCourseAry = Json.createArrayBuilder();
		for (CourseBean c : new CourseDao().getCourseList()) {

			if (!s_course_year.equals("") && !s_course_semester.equals("")
					&& s_course_year.equals(String.valueOf(c.getYear())) && s_course_semester.equals(c.getSemester())) {
				jsonCourseAry.add(Json.createObjectBuilder().add("id", c.getId()).add("code", c.getCode()).add("title",
						c.getTitle()));
			} else if (!s_course_year.equals("") && s_course_year.equals(String.valueOf(c.getYear()))) {
				jsonCourseAry.add(Json.createObjectBuilder().add("id", c.getId()).add("code", c.getCode()).add("title",
						c.getTitle()));
			} else if (s_course_year.equals("") && s_course_semester.equals("")) {
				jsonCourseAry.add(Json.createObjectBuilder().add("id", c.getId()).add("code", c.getCode()).add("title",
						c.getTitle()));
			}

		}
		boolean flagCourseChanged = false;
		if (searchColumn.length>0 && searchColumn[0].equals("courseid")) {
			flagCourseChanged = true;
		}

		result = Json.createObjectBuilder().add("flagCourseChanged", flagCourseChanged).add("countRecord", countRecord)
				.add("maxpg", totalpg).add("pg", initpg).add("topics", jsonTopicAry).add("course", jsonCourseAry)
				.build();

		return result;
	}

}
