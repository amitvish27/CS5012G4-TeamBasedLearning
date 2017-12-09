package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import edu.umsl.java.util.ReadProperties;

public class TakeQuizDao {

private Connection connection;
	
	public TakeQuizDao() {
		ReadProperties.loadPropertiesFile();
		try {
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//getQuizWithId -authenticate with token - details total questions, total time
	//nextQuestion and previousQuestion with answer for previous if answered -- getQuestion() 
	//finish quiz -- 
	
	public JsonArrayBuilder getActiveQuizList(String timestamp) {
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();
		String query = "SELECT id, courseid, number, time_limit "
				+ "FROM quiz "
				+ "WHERE deleted=0 AND (start_time <= '"+timestamp+"' and end_time >='"+timestamp+"')";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				jsonArry.add(Json.createObjectBuilder()
						.add("quizid", res.getInt("id"))
						.add("courseid", res.getInt("courseid"))
						.add("quiznumber", res.getInt("number"))
						.add("time_limit", res.getInt("time_limit")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return jsonArry;
	}
	
	public JsonObjectBuilder getQuizWithId(int id) {
		JsonObjectBuilder jsonObject = Json.createObjectBuilder();
		String query = "SELECT id, courseid, number, time_limit "
				+ "FROM quiz "
				+ "WHERE deleted=0 AND id='"+id+"'";

		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			res.next();
			jsonObject.add("quizid", res.getInt("id"))
				.add("time_limit", res.getInt("time_limit"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public boolean checkTokenValidation(int quizId, String token) {
		if (token.equals("quiz"+quizId))
			return true;
		return false;
	}
	
	
}
