package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.java.beans.Instructor;

public class InstructorDao {
	private Connection connection;
	private PreparedStatement results;
	
	public InstructorDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs5012g4db", "root", "");
			
			results = connection.prepareStatement(
					"SELECT id, name " + "FROM instructor ORDER BY name DESC ");
			
		} catch( Exception ex )
		{
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}
	
	public List<Instructor> getInstructorList() {
		List<Instructor> instructorList = new ArrayList<Instructor>();
		
		try {
			ResultSet res = results.executeQuery();
			
			while( res.next() ) {
				Instructor instructor = new Instructor();
				instructor.setId(res.getInt(1));
				instructor.setName(res.getString(2));
				instructorList.add(instructor);
			}
				
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		
		return instructorList;
		
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
