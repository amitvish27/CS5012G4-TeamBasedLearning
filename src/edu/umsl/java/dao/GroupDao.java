package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import edu.umsl.java.beans.CourseBean;
import edu.umsl.java.util.ReadProperties;

public class GroupDao {
	private Connection connection;
	
	public GroupDao() {
		ReadProperties.loadPropertiesFile();
		try {
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JsonArrayBuilder getCourseList() throws Exception {
		JsonArrayBuilder jsonCourseAry = Json.createArrayBuilder();
		for(CourseBean c: new CourseDao().getCourseList()) {
			jsonCourseAry.add(Json.createObjectBuilder().add("id", c.getId()).add("code", c.getCode()).add("title",
					c.getTitle()));
		}
		return jsonCourseAry;
	}

	public JsonArrayBuilder getGroupsInCourse(int courseId) {
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();
		String query = "SELECT `groupid`, `courseid`, `groupnumber`, `instructorid`, `deleted` "
				+ "FROM `sgroup` WHERE `courseid`="+courseId+" AND deleted=0 "
						+ "ORDER BY `groupid` DESC";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				jsonArry.add(Json.createObjectBuilder()
								.add("groupid", res.getInt("groupid"))
								.add("courseid", res.getInt("courseid"))
								.add("groupnumber", res.getInt("groupnumber"))
								.add("instructorid", res.getString("instructorid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArry;
	}

	public int addGroupInCourse(int courseId, int groupNumber, String instssoid) {
		
		int groupId =-1;
		String insertQuery = "INSERT INTO `sgroup` (`courseid`, `groupnumber`, `instructorid`) VALUES ( '"+courseId+"', '"+groupNumber+"', '"+instssoid+"');" ; 
		String getQuery = "SELECT `groupid` FROM `sgroup` "
				+ "WHERE `courseid`='"+courseId+"' AND `groupnumber`='"+groupNumber+"' AND `instructorid`='"+instssoid+"' AND `deleted`=0";
		try {
			PreparedStatement query = connection.prepareStatement(insertQuery);
			query.executeUpdate();
			query = connection.prepareStatement(getQuery);
			ResultSet res = query.executeQuery();
			if( res.next() ) {
				groupId = res.getInt("groupid");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupId;
	}

	public void updateGroupInCourse(int courseId, int groupid, int groupNumber, String instssoid) {
		String updateQuery = "UPDATE `sgroup` SET `groupnumber`="+groupNumber+" WHERE `courseid`="+courseId+" AND `groupid`="+groupid+" AND `instructorid`='"+instssoid+"'";
		PreparedStatement query;
		try {
			query = connection.prepareStatement(updateQuery);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delGroupInCourse(int groupId) {
		String delQuery = "UPDATE `sgroup` SET `deleted`=1 WHERE `groupid`="+groupId;
		PreparedStatement query;
		try {
			query = connection.prepareStatement(delQuery);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JsonArrayBuilder getStudentsInGroup(int groupId) {
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();
		
		String query = "SELECT `relnid`, `studentid`, `fname`, `lname` "
				+ "FROM `student_sgroup` as s, `user` as u "
				+ "WHERE s.groupid="+groupId+" AND s.studentid=u.ssoid AND u.role=0 AND s.deleted=0";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				jsonArry.add(Json.createObjectBuilder()
							.add("relnid", res.getString("relnid"))
							.add("studentid", res.getString("studentid"))
							.add("fname", res.getString("fname"))
							.add("lname", res.getString("lname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jsonArry;
	}
	
	public JsonArrayBuilder getStudentsNotInGroup(int groupId) {
		JsonArrayBuilder jsonArry = Json.createArrayBuilder();
		
		String query = "SELECT u.ssoid, u.fname, u.lname "
				+ "FROM user AS u WHERE u.ssoid NOT IN "
				+ "(SELECT g.studentid FROM student_sgroup g WHERE g.groupid="+groupId+") AND u.role=0 "
						+ "ORDER BY u.ssoid ASC";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				jsonArry.add(Json.createObjectBuilder()
							.add("studentid", res.getString("ssoid"))
							.add("fname", res.getString("fname"))
							.add("lname", res.getString("lname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jsonArry;
	}
	
	public void addStudentsInGroup(int groupId, String[] listStudents) {
		String insertQuery = "";
		PreparedStatement query ;
		try {
			for(String stdid : listStudents) {
				insertQuery = "INSERT INTO `student_sgroup` (`studentid`, `groupid`) VALUES ('"+stdid+"', '"+groupId+"');";
				query = connection.prepareStatement(insertQuery);
				query.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void delStudentsInGroup(int s_relnid) {
		String delQuery = "UPDATE `student_sgroup` SET `deleted`=1 WHERE `relnid`="+s_relnid;
		PreparedStatement query;
		try {
			query = connection.prepareStatement(delQuery);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
