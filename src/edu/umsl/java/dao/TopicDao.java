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

public class TopicDao {
	private Connection connection;
	private PreparedStatement results;
	private PreparedStatement probcnt;
	private PreparedStatement addTopic;
	
	public TopicDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs5012g4db", "root", "");
			
			results = connection.prepareStatement(
					"SELECT id, title, course, creation_time, isdelete " + "FROM topic WHERE isdelete=0 ORDER BY creation_time DESC LIMIT ?, ? ");
			
			probcnt = connection.prepareStatement(
					"SELECT COUNT(id) FROM topic " + "WHERE isdelete=0");
			
			addTopic = connection.prepareStatement(
					"INSERT INTO topic (title, course) "+"VALUES (?,?)");
		} catch( Exception ex )
		{
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}
	
/*	public List<Topic> getTopicList() {
		List<Topic> topicList = new ArrayList<Topic>();
		
		try {
			ResultSet res = results.executeQuery();
			
			while( res.next() ) {
				Topic topic = new Topic();
				topic.setId(res.getInt(1));
				topic.setTitle(res.getString(2));
				topic.setCourse(res.getInt(3));
				topic.setTimestamp(res.getString(4));
				topic.setIsDelete(res.getInt(5));
				topicList.add(topic);
			}
				
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		
		return topicList;
		
	}*/
	
	protected void finalize() {
		try {
			results.close();
			connection.close();
		}
		catch( SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public int getTopicCount() {
		int count=0;
		
		try {
			ResultSet res = probcnt.executeQuery();
			res.next();
			count = res.getInt(1);
		}catch(SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		
		return count;
	}

	public List<Topic> getTopicListByPage(int pg) {
		List<Topic> topicList = new ArrayList<Topic>();
		
		int st = 10* (pg-1);
		
		try {
			results.setInt(1, st) ;
			results.setInt(2, 10);
			
			ResultSet res = results.executeQuery();
			
			while( res.next()) {
				Topic topic = new Topic();
				topic.setId(res.getInt(1));
				topic.setTitle(res.getString(2));
				topic.setCourse(res.getInt(3));
				topic.setTimestamp(res.getString(4));
				topic.setIsDelete(res.getInt(5));
				topicList.add(topic);
			}
			
		} catch(SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		
		return topicList;
	}

	public void addTopic(String topicCont, int courseId) {
		try {
			addTopic.setString(1, topicCont);
			addTopic.setInt(2, courseId);
			
			addTopic.executeUpdate();
		} catch( SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		
	}

}
