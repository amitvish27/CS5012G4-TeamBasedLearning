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
	private PreparedStatement loginInstRS;
	private ReadProperties rp;
	
	public InstructorDao() throws Exception {
		try {
			rp = new ReadProperties();
			Class.forName(rp.getDbDriver());
			connection = DriverManager.getConnection(rp.getDbUrl(), rp.getDbUser(), rp.getDbPswd());

			instListRS = connection.prepareStatement(
					"SELECT  `id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `createdby`, `modifiedby`, `role`, `active` "
							+ "FROM user WHERE deleted=0 ORDER BY created DESC ");
			loginInstRS = connection.prepareStatement(
					"SELECT  `id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `createdby`, `modifiedby`, `role`, `active` "
							 + "FROM user WHERE ssoid=? and pswd=?");

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

	public ArrayList<Instructor> checkinstlogin(String ssoid, String pswd) throws SQLException {
		ArrayList<Instructor> list = new ArrayList<Instructor>();
		try {
			loginInstRS.setString(1, ssoid);
			loginInstRS.setString(2, pswd);

			ResultSet res = loginInstRS.executeQuery();
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
