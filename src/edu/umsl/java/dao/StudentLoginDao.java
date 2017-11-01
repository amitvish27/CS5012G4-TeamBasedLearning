package edu.umsl.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.UnavailableException;


import edu.umsl.java.beans.StudentLogin;

public class StudentLoginDao {

	private Connection connection;
	private PreparedStatement results;
	
	public StudentLoginDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs5012g4db", "root", "");

			results = connection.prepareStatement(
					"SELECT SSO_ID, password " + "FROM student WHERE SSO_ID=? and password=?");
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new UnavailableException(exception.getMessage());
		}

	}
	
	public ArrayList<StudentLogin> checkstulogin(String id, String password) throws SQLException {
		ArrayList<StudentLogin> list = new ArrayList<StudentLogin>();
		try {
			results.setString(1, id);
			results.setString(2, password);

			ResultSet resultsRS = results.executeQuery();
			while (resultsRS.next()) {
				StudentLogin student = new StudentLogin();
				student.getSSO_ID();
				student.getPassword();
				list.add(student);			
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return list;
	}
}
