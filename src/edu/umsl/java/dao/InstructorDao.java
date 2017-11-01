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
	private PreparedStatement instListRS;
	private PreparedStatement loginInstRS;

	public InstructorDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs5012g4db", "root", "");

			instListRS = connection.prepareStatement(
					"SELECT  `id`, `SSO_ID`, `password`, `first_name`, `last_name`, `department`, `time_created`, `del` "
							+ "FROM instructor WHERE del=0 ORDER BY time_created DESC ");
			loginInstRS = connection
					.prepareStatement("SELECT `id`, `SSO_ID`, `password`, `first_name`, `last_name`, `department`, `time_created`, `del`"
							 + "FROM instructor WHERE SSO_ID=? and password=?");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}

	public List<Instructor> getInstructorList() {
		List<Instructor> instructorList = new ArrayList<Instructor>();

		try {
			ResultSet res = instListRS.executeQuery();

			while (res.next()) {
				Instructor instructor = new Instructor();
				instructor.setId(res.getInt(1));
				instructor.setSSO_ID(res.getString(2));
				instructor.setPassword(res.getString(3));
				instructor.setFirst_name(res.getString(4));
				instructor.setLast_name(res.getString(5));
				instructor.setDepartment(res.getString(6));
				instructor.setTime_created(res.getString(7));
				instructor.setDel(res.getInt(8));
				
				instructorList.add(instructor);
			}

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

		return instructorList;

	}

	public ArrayList<Instructor> checkinstlogin(String id, String password) throws SQLException {
		ArrayList<Instructor> list = new ArrayList<Instructor>();
		try {
			loginInstRS.setString(1, id);
			loginInstRS.setString(2, password);

			ResultSet resultsRS = loginInstRS.executeQuery();
			while (resultsRS.next()) {
				Instructor instructor = new Instructor();
				
				instructor.setId(resultsRS.getInt(1));
				instructor.setSSO_ID(resultsRS.getString(2));
				instructor.setPassword(resultsRS.getString(3));
				instructor.setFirst_name(resultsRS.getString(4));
				instructor.setLast_name(resultsRS.getString(5));
				instructor.setDepartment(resultsRS.getString(6));
				instructor.setTime_created(resultsRS.getString(7));
				instructor.setDel(resultsRS.getInt(8));
				
				list.add(instructor);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return list;
	}

	protected void finalize() {
		/*try {
			instListRS.close();
			loginInstRS.close();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}*/
	}
}
