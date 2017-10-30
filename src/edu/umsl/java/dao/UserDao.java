package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.UnavailableException;

import edu.umsl.java.beans.UserBean;

public class UserDao {

	private Connection connection;
	private PreparedStatement results;
	private PreparedStatement getRecords;
	private PreparedStatement getRecords2;
	private PreparedStatement addRecord;
	private PreparedStatement deleteRecord;
	private PreparedStatement updateRecord;
	private PreparedStatement getRecords3;
	private PreparedStatement getRecords4;
	private PreparedStatement getRecords_lname;
	private PreparedStatement getRecords_fname;

	public UserDao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

			getRecords = connection.prepareStatement("Select id, ssoid, fname, lname, active from user where active = 1 order by id ASC");
			getRecords2 = connection.prepareStatement("Select id, ssoid, fname, lname, active from user where id = ?");
			getRecords3 = connection.prepareStatement("select id, ssoid, fname, lname, active from user where ssoid like ? or fname like ? or lname like ?");
			
			getRecords_lname = connection.prepareStatement("Select id, ssoid, fname, lname, active from user where active = 1 order by lname ASC");
			getRecords_fname = connection.prepareStatement("Select id, ssoid, fname, lname, active from user where active = 1 order by fname ASC");
			
			addRecord = connection.prepareStatement(
					"insert into user (" + "ssoid, fname, lname, email, role)" + "values (?,?,?,?,?)");
			deleteRecord = connection.prepareStatement("update user set active = 0 where id = ?");
			updateRecord = connection.prepareStatement("update user	set ssoid= ?, fname=?, lname=? where id = ?");

	
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new UnavailableException(exception.getMessage());
		}

	}

	public List<String> getNameList() throws SQLException {
		List<String> nmlist = new ArrayList<String>();
		
		try {
			ResultSet resultsRS = getRecords4.executeQuery();

			while (resultsRS.next()) {
				nmlist.add(resultsRS.getString(1));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return nmlist;
	}
	
	
	public void updateUser(String ssoid, String fname, String lname, String id) throws SQLException {

		try {
			updateRecord.setString(1, ssoid);
			updateRecord.setString(2, fname);
			updateRecord.setString(3, lname);
			updateRecord.setInt(4, Integer.parseInt(id));

			updateRecord.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteUser(String id) throws SQLException {

		try {
			deleteRecord.setString(1, id);
			deleteRecord.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserBean getUserSearch (String ssoid, String fname, String lname) throws SQLException {
		
		UserBean user = new UserBean();
		getRecords3.setString(1,  ssoid);
		getRecords3.setString(2,  fname);
		getRecords3.setString(3,  lname);
		ResultSet rs = getRecords3.executeQuery();
		rs.next();
		user.setSsoid(rs.getString(2));
		user.setFname(rs.getString(3));
		user.setLname(rs.getString(4));
		
		return user;
	}
	public UserBean getUser(String id) throws SQLException {

		UserBean user = new UserBean();
		getRecords2.setInt(1, Integer.parseInt(id));
		ResultSet rs = getRecords2.executeQuery();
		rs.next();
		user.setSsoid(rs.getString(2));
		user.setFname(rs.getString(3));
		user.setLname(rs.getString(4));

		return user;
	}

	public List<UserBean> getUserList() throws SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();
		ResultSet results = getRecords.executeQuery();

		while (results.next()) {
			UserBean user = new UserBean();
			user.setId(results.getString(1));
			user.setSsoid(results.getString(2));
			user.setFname(results.getString(3));
			user.setLname(results.getString(4));

			userList.add(user);

		}
		return userList;
	}
	
	
	public List<UserBean> getUserListLname() throws SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();
		ResultSet results = getRecords_lname.executeQuery();

		while (results.next()) {
			UserBean user = new UserBean();
			user.setId(results.getString(1));
			user.setSsoid(results.getString(2));
			user.setFname(results.getString(3));
			user.setLname(results.getString(4));

			userList.add(user);

		}
		return userList;
	}
	
	public List<UserBean> getUserListFname() throws SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();
		ResultSet results = getRecords_fname.executeQuery();

		while (results.next()) {
			UserBean user = new UserBean();
			user.setId(results.getString(1));
			user.setSsoid(results.getString(2));
			user.setFname(results.getString(3));
			user.setLname(results.getString(4));

			userList.add(user);

		}
		return userList;
	}
	

	public void addUser(UserBean user) throws SQLException {
		addRecord.setString(1, user.getSsoid());
		addRecord.setString(2, user.getFname());
		addRecord.setString(3, user.getLname());
		addRecord.setString(4, user.getEmail());
		addRecord.setInt(5, user.getRole());
		addRecord.executeUpdate();
	}

	protected void finalize() {
		try {
			getRecords.close();
			getRecords2.close();
			getRecords3.close();
			getRecords4.close();
			addRecord.close();
			deleteRecord.close();
			updateRecord.close();
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

}
