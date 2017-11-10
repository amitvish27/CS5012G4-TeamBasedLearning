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
import edu.umsl.java.util.ReadProperties;

public class InstructorDao {
	private Connection connection;
	private PreparedStatement instListRS;
	private PreparedStatement SsoIdInstRS;
	private PreparedStatement saveInstRS;
	private PreparedStatement saveInstPswdRS;
	private PreparedStatement setActive;
	private ReadProperties rp;

	public InstructorDao() throws Exception {
		try {
			rp = new ReadProperties();
			Class.forName(rp.getDbDriver());
			connection = DriverManager.getConnection(rp.getDbUrl(), rp.getDbUser(), rp.getDbPswd());

			instListRS = connection.prepareStatement(
					"SELECT  `id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `createdby`, `modifiedby`, `role`, `active` "
							+ "FROM user WHERE deleted=0 ORDER BY created DESC ");
			
			SsoIdInstRS = connection.prepareStatement(
					"SELECT  `id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `createdby`, `modifiedby`, `role`, `active` "
							+ "FROM user WHERE ssoid=?");
			
			saveInstRS = connection.prepareStatement(
					"UPDATE `user` SET `fname`=?,`lname`=?,`email`=?,`dept`=?,`modifiedby`=?"
					+ "WHERE ssoid=?");
			
			saveInstPswdRS = connection.prepareStatement(
					"UPDATE `user` SET `pswd`=?,`modifiedby`=?"
					+ "WHERE ssoid=?");
			
			setActive = connection.prepareStatement(
					"UPDATE `user` SET `active`=?,`modifiedby`=?"
							+ "WHERE ssoid=?");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}
	
	public void setInstActive(String ssoid, int active, String modifiedby) {
		try {
			setActive.setInt(1, active);
			setActive.setString(2, modifiedby);
			setActive.setString(3, ssoid);
			
			setActive.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}
	
	public void saveInstPswd(String ssoid, String pswd, String modifiedby) {
		try {
			saveInstPswdRS.setString(1, pswd);
			saveInstPswdRS.setString(2, modifiedby);
			saveInstPswdRS.setString(3, ssoid);
			
			saveInstPswdRS.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}
	
	public void saveInstProfile(String ssoid, String fname, String lname, String email, String dept, String modifiedby) {
		try {
			saveInstRS.setString(1, fname);
			saveInstRS.setString(2, lname);
			saveInstRS.setString(3, email);
			saveInstRS.setString(4, dept);
			saveInstRS.setString(5, modifiedby);
			saveInstRS.setString(6, ssoid);
			saveInstRS.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public List<Instructor> getInstructorList() {
		List<Instructor> instructorList = new ArrayList<Instructor>();

		try {
			ResultSet res = instListRS.executeQuery();

			while (res.next()) {
				Instructor instructor = new Instructor();

				instructor.setId(res.getInt(1));
				instructor.setSsoid(res.getString(2));
				instructor.setPswd(res.getString(3));
				instructor.setFname(res.getString(4));
				instructor.setLname(res.getString(5));
				instructor.setEmail(res.getString(6));
				instructor.setDept(res.getString(7));
				instructor.setCreatedby(res.getString(8));
				instructor.setModifiedby(res.getString(9));
				instructor.setRole(res.getInt(10));
				instructor.setActive(res.getInt(11));

				instructorList.add(instructor);
			}

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

		return instructorList;

	}

	public Instructor getInstructorBySsoId(String ssoid) throws SQLException {
		Instructor instructor = null;
		try {
			SsoIdInstRS.setString(1, ssoid);

			ResultSet res = SsoIdInstRS.executeQuery();
			res.next();
			instructor = new Instructor();
			instructor.setId(res.getInt(1));
			instructor.setSsoid(res.getString(2));
			instructor.setPswd(res.getString(3));
			instructor.setFname(res.getString(4));
			instructor.setLname(res.getString(5));
			instructor.setEmail(res.getString(6));
			instructor.setDept(res.getString(7));
			instructor.setCreatedby(res.getString(8));
			instructor.setModifiedby(res.getString(9));
			instructor.setRole(res.getInt(10));
			instructor.setActive(res.getInt(11));
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return instructor;
	}

	protected void finalize() {
		/*
		 * try { instListRS.close(); loginInstRS.close(); } catch (SQLException sql_ex)
		 * { sql_ex.printStackTrace(); }
		 */
	}
}
