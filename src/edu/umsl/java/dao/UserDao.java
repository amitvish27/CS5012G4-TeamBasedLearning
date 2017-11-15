package edu.umsl.java.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.java.beans.PageBean;
import edu.umsl.java.beans.UserBean;
import edu.umsl.java.util.PasswordEncrypter;
import edu.umsl.java.util.ReadProperties;

public class UserDao {

	private static final String secretKey = "UMSLTEAMBASED";

	private Connection connection;
	private PreparedStatement instListRS;
	private PreparedStatement SsoIdInstRS;
	private PreparedStatement saveInstRS;
	private PreparedStatement saveInstPswdRS;
	private PreparedStatement setActive;
	private PreparedStatement getCount;
	private PreparedStatement getRecords;
	private PreparedStatement getRecords_sorted;
	private PreparedStatement getUserRecords;
	private PreparedStatement getRecords3;
	private PreparedStatement deleteRecord;
	private PreparedStatement updateRecord;
	private PreparedStatement addRecord;

	public UserDao() throws Exception {
		try {
			ReadProperties.loadPropertiesFile();
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());

			// for all individual users
			instListRS = connection.prepareStatement(
					"SELECT  `id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `createdby`, `modifiedby`, `role`, `active` "
							+ "FROM user WHERE deleted=0 ORDER BY created DESC ");

			SsoIdInstRS = connection.prepareStatement(
					"SELECT  `id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `createdby`, `modifiedby`, `role`, `active` "
							+ "FROM user WHERE ssoid=?");

			saveInstRS = connection.prepareStatement(
					"UPDATE `user` SET `fname`=?,`lname`=?,`email`=?,`dept`=?,`modifiedby`=?" + "WHERE ssoid=?");

			saveInstPswdRS = connection.prepareStatement("UPDATE `user` SET `pswd`=?,`modifiedby`=?" + "WHERE ssoid=?");

			setActive = connection.prepareStatement("UPDATE `user` SET `active`=?,`modifiedby`=?" + "WHERE ssoid=?");

			// for admin role - user management
			getCount = connection
					.prepareStatement("SELECT COUNT(*) from `user` WHERE `deleted`=0 AND `role`=? OR `role`=?");

			getRecords = connection.prepareStatement(
					"SELECT `id`, `ssoid`, `fname`, `lname`, `email`,`dept`, `role`, `deleted`, `active` FROM `user` WHERE `role`=? OR `role`=? ORDER BY `created` DESC  LIMIT ?, ?");
			//TODO
			getRecords_sorted = connection.prepareStatement(
					"SELECT `id`, `ssoid`, `fname`, `lname`, `email`,`dept`, `role`, `deleted`, `active` FROM `user` ORDER BY ? ?  LIMIT ?, ?");

			getUserRecords = connection.prepareStatement(
					"SELECT `id`, `ssoid`, `fname`, `lname`, `email`,`dept`, `role`, `deleted`, `active`, `role`, `email`, `dept` FROM `user` WHERE `id` = ?");

			getRecords3 = connection.prepareStatement(
					"SELECT `id`, `ssoid`, `fname`, `lname`, `email`,`dept`, `role`, `deleted`, `active`, `role`, `email`, `dept` FROM `user` WHERE `ssoid` LIKE ? OR `fname` LIKE ? OR `lname` LIKE ? OR `email` LIKE ? OR `dept` LIKE ?");

			addRecord = connection.prepareStatement(
					"INSERT INTO `user` (`ssoid`,`pswd`,`fname`,`lname`,`email`,`dept`,`createdby`,`modifiedby`,`role`,`active`, `deleted`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

			deleteRecord = connection.prepareStatement("UPDATE `user` SET `deleted`=1 WHERE `id`=?");

			updateRecord = connection.prepareStatement(
					"UPDATE `user` SET `ssoid`=?, `fname`=?, `lname`=? , `role`=? , `email`=?, `dept`=? ,`deleted`=?, `active`=? WHERE `id`=?");

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
			String encryptedPswd = PasswordEncrypter.encrypt(pswd, secretKey);

			saveInstPswdRS.setString(1, encryptedPswd);
			saveInstPswdRS.setString(2, modifiedby);
			saveInstPswdRS.setString(3, ssoid);

			saveInstPswdRS.executeUpdate();
		} catch (UnsupportedEncodingException | GeneralSecurityException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void saveInstProfile(String ssoid, String fname, String lname, String email, String dept,
			String modifiedby) {
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

	public List<UserBean> getInstructorList() {
		List<UserBean> instructorList = new ArrayList<UserBean>();

		try {
			ResultSet res = instListRS.executeQuery();

			while (res.next()) {
				UserBean instructor = new UserBean();
				instructor.setId(res.getInt(1));
				instructor.setSsoid(res.getString(2));

				System.out.println("paswd : " + res.getString(3));
				String decryptedPswd = PasswordEncrypter.decrypt(res.getString(3), secretKey);
				instructor.setPswd(decryptedPswd);

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

		} catch (GeneralSecurityException | IOException | SQLException ex) {
			ex.printStackTrace();
		}

		return instructorList;

	}

	public UserBean getInstructorBySsoId(String ssoid) throws SQLException {
		UserBean instructor = null;
		try {
			SsoIdInstRS.setString(1, ssoid);

			ResultSet res = SsoIdInstRS.executeQuery();
			if (res.next()) {
				instructor = new UserBean();
				instructor.setId(res.getInt(1));
				instructor.setSsoid(res.getString(2));

				String decryptedPswd = PasswordEncrypter.decrypt(res.getString(3), secretKey);
				instructor.setPswd(decryptedPswd);

				instructor.setFname(res.getString(4));
				instructor.setLname(res.getString(5));
				instructor.setEmail(res.getString(6));
				instructor.setDept(res.getString(7));
				instructor.setCreatedby(res.getString(8));
				instructor.setModifiedby(res.getString(9));
				instructor.setRole(res.getInt(10));
				instructor.setActive(res.getInt(11));
			}

		} catch (GeneralSecurityException | IOException | SQLException ex) {
			ex.printStackTrace();
		}

		return instructor;
	}

	// for admin role - user management
	public void updateUser(String ssoid, String fname, String lname, String id, String role, String email, String dept,
			String deleted, String active) throws SQLException {
		try {
			updateRecord.setString(1, ssoid);
			updateRecord.setString(2, fname);
			updateRecord.setString(3, lname);
			updateRecord.setInt(4, Integer.parseInt(role));
			updateRecord.setString(5, email);
			updateRecord.setString(6, dept);
			updateRecord.setInt(7, Integer.parseInt(deleted));
			updateRecord.setInt(8, Integer.parseInt(active));
			updateRecord.setInt(9, Integer.parseInt(id));
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

	public PageBean getCount(int role1, int role2) throws SQLException {
		PageBean pagebean = new PageBean();
		try {
			getCount.setInt(1, role1);
			getCount.setInt(2, role2);
			ResultSet rs = getCount.executeQuery();
			rs.next();
			pagebean.setTotalRecords(rs.getInt(1));

			double temp = Math.ceil((double) pagebean.getTotalRecords() / pagebean.getRecordsPerPage());
			pagebean.setTotalPages((int) temp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagebean;
	}

	public List<UserBean> getUserSearchList(String ssoid, String fname, String lname, String email, String dept)
			throws SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();
		getRecords3.setString(1, ssoid);
		getRecords3.setString(2, fname);
		getRecords3.setString(3, lname);
		getRecords3.setString(4, email);
		getRecords3.setString(5, dept);

		ResultSet rs = getRecords3.executeQuery();
		while (rs.next()) {
			UserBean user = new UserBean();
			user.setId(rs.getInt(1));
			user.setSsoid(rs.getString(2));
			user.setFname(rs.getString(3));
			user.setLname(rs.getString(4));
			user.setDeleted(rs.getInt(5));
			user.setActive(rs.getInt(6));
			user.setRole(rs.getInt(7));
			user.setEmail(rs.getString(8));
			user.setDept(rs.getString(9));
			userList.add(user);
		}
		return userList;

	}

	public UserBean getUserSearch(String ssoid, String fname, String lname, String email, String dept)
			throws SQLException {
		UserBean user = new UserBean();
		getRecords3.setString(1, ssoid);
		getRecords3.setString(2, fname);
		getRecords3.setString(3, lname);
		getRecords3.setString(4, email);
		getRecords3.setString(5, dept);

		ResultSet rs = getRecords3.executeQuery();
		rs.next();
		int row = rs.getRow();

		if (row > 0) {
			user.setId(rs.getInt(1));
			user.setSsoid(rs.getString(2));
			user.setFname(rs.getString(3));
			user.setLname(rs.getString(4));
			user.setDeleted(rs.getInt(5));
			user.setActive(rs.getInt(6));
			user.setRole(rs.getInt(7));
			user.setEmail(rs.getString(8));
			user.setDept(rs.getString(9));
		} else {
			user.setSsoid("");
			user.setFname("");
			user.setLname("");
		}

		return user;
	}

	public UserBean getUser(String id) throws SQLException {

		UserBean user = new UserBean();
		getUserRecords.setInt(1, Integer.parseInt(id));

		ResultSet rs = getUserRecords.executeQuery();
		rs.next();
		user.setId(rs.getInt(1));
		user.setSsoid(rs.getString(2));
		user.setFname(rs.getString(3));
		user.setLname(rs.getString(4));
		user.setDeleted(rs.getInt(5));
		user.setActive(rs.getInt(6));
		user.setRole(rs.getInt(7));
		user.setEmail(rs.getString(8));
		user.setDept(rs.getString(9));

		return user;
	}

	public List<UserBean> getUserList(int role1, int role2, int start, int end) throws SQLException {
		// "SELECT `id`, `ssoid`, `fname`, `lname`, `email`,`dept`, `role`, `deleted`,
		// `active`
		List<UserBean> userList = new ArrayList<UserBean>();
		getRecords.setInt(1, role1);
		getRecords.setInt(2, role2);
		getRecords.setInt(3, start);
		getRecords.setInt(4, end);
		ResultSet results = getRecords.executeQuery();

		while (results.next()) {
			UserBean user = new UserBean();
			user.setId(results.getInt(1));
			user.setSsoid(results.getString(2));
			user.setFname(results.getString(3));
			user.setLname(results.getString(4));
			user.setEmail(results.getString(5));
			user.setDept(results.getString(6));
			user.setRole(results.getInt(7));
			user.setDeleted(results.getInt(8));
			user.setActive(results.getInt(9));
			userList.add(user);
		}
		return userList;
	}

	public List<UserBean> getUserListSorted(int start, int end, String col, String dir)
			throws SQLException {
		// SELECT `id`, `ssoid`, `fname`, `lname`, `email`,`dept`, `role`, `deleted`,
		// `active` FROM `user` WHERE `role`=? OR `role`=? ORDER BY `?` ? LIMIT ?, ?"
		// TODO
		col = (col.equals(""))?"created":col;
		dir = (dir.equals(""))?"ASC":dir;
		
		List<UserBean> userList = new ArrayList<UserBean>();
		getRecords_sorted.setString(1, col);
		getRecords_sorted.setString(2, dir);
		getRecords_sorted.setInt(3, start);
		getRecords_sorted.setInt(4, end);
		
		System.out.println(">>>>>sorting by " + col +" " + dir);
		System.out.println(">>>>>paging by " + start +" " + end);
				
		ResultSet results = getRecords_sorted.executeQuery();

		while (results.next()) {
			UserBean user = new UserBean();
			user.setId(results.getInt(1));
			user.setSsoid(results.getString(2));
			user.setFname(results.getString(3));
			user.setLname(results.getString(4));
			user.setEmail(results.getString(5));
			user.setDept(results.getString(6));
			user.setRole(results.getInt(7));
			user.setDeleted(results.getInt(8));
			user.setActive(results.getInt(9));
			userList.add(user);
			System.out.println(user.getSsoid() + " - " + user.getFname());
		}
		return userList;
	}

	public void addUser(UserBean user) throws SQLException {
		addRecord.setString(1, user.getSsoid());
		addRecord.setString(2, user.getPswd());
		addRecord.setString(3, user.getFname());
		addRecord.setString(4, user.getLname());
		addRecord.setString(5, user.getEmail());
		addRecord.setString(6, user.getDept());
		addRecord.setString(7, user.getCreatedby());
		addRecord.setString(8, user.getModifiedby());
		addRecord.setInt(9, user.getRole());
		addRecord.setInt(10, user.getActive());
		addRecord.setInt(11, user.getDeleted());
		addRecord.executeUpdate();

	}

	protected void finalize() {
		try {
			instListRS.close();
			SsoIdInstRS.close();
			saveInstRS.close();
			saveInstPswdRS.close();
			setActive.close();
			getCount.close();
			getRecords.close();
			getUserRecords.close();
			getRecords3.close();
			deleteRecord.close();
			updateRecord.close();
			addRecord.close();
			connection.close();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

	}
}
