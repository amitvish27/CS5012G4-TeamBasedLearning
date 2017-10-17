package edu.umsl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.beans.Topic;

public class TopicDao {
	private Connection connection;
	private PreparedStatement results;
	
	public TopicDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs5012g4db", "root", "");
			
			results = connection.prepareStatement(
					"SELECT id, title, course, creation_time, isdelete " + "FROM topic ORDER BY id ASC ");
			
		} catch( Exception ex )
		{
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}
	
	public List<Topic> getTopicList() {
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
