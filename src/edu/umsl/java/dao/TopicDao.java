package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.java.beans.Topic;
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
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(), ReadProperties.getDbPswd());

			results = connection.prepareStatement("SELECT id, title, courseid, instructorid "
					+ "FROM topic WHERE deleted=0 ORDER BY created DESC LIMIT ?, ? ");

			topiccnt = connection.prepareStatement("SELECT COUNT(id) FROM topic " + "WHERE deleted=0");

			addTopic = connection.prepareStatement("INSERT INTO topic (title, courseid, instructorid) " + "VALUES (?,?,?)");
			
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

	public List<Topic> getTopicListByPage(int pg) {
		List<Topic> topicList = new ArrayList<Topic>();

		int st = 10 * (pg - 1);

		try {
			results.setInt(1, st);
			results.setInt(2, 10);

			ResultSet res = results.executeQuery();

			while (res.next()) {
				Topic topic = new Topic();

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
		String str="";
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

}
