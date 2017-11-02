package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.java.bean.Courses_bean;

public class CoursesDao 
{
	private Connection connection;
	private PreparedStatement results;
	private PreparedStatement addCourse;
	private PreparedStatement delcour;
	private PreparedStatement coursesCount;
	
	
	public CoursesDao() throws Exception
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/case_3","root","");
			results = connection.prepareStatement
					("SELECT course_ID, course_code, course_title, course_year, semester, created_time, instructor_ID"
					+ " FROM Course WHERE if_deleted = 0 ORDER BY course_ID DESC LIMIT ?, ?");
			addCourse = connection.prepareStatement("INSERT INTO Course(course_code, course_title, course_year, semester, created_time, instructor_ID, if_deleted) VALUES (?, ?, ?, ?, ?, ?, 0)");
			delcour = connection.prepareStatement("UPDATE course SET if_deleted = 1 WHERE course_ID = ?");
			coursesCount = connection.prepareStatement("SELECT COUNT(course_ID) FROM course WHERE if_deleted = 0");
			
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new UnavailableException(exception.getMessage());
		}
	}
	
	
	public List<Courses_bean> getCoursesList() throws SQLException
	{
		List<Courses_bean> courses_List = new ArrayList<Courses_bean>();
			
	  	try
		{
			ResultSet resultsRs = results.executeQuery();
			
			while(resultsRs.next())
			{
				Courses_bean cb = new Courses_bean();
				
				cb.setCourse_ID(resultsRs.getInt(1));
				cb.setCourse_code(resultsRs.getString(2));
				cb.setCourse_title(resultsRs.getString(3));
				cb.setCourse_year(resultsRs.getInt(4));
				cb.setSemester(resultsRs.getString(5));
				cb.setCreated_time(resultsRs.getDate(6));
				cb.setInstructor_ID(resultsRs.getInt(7));
				
				courses_List.add(cb);
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
			
	    return courses_List;
	}
	
	
	public List<Courses_bean> getCoursesListByPage(int pg) throws SQLException 
	{
		List<Courses_bean> courses_List = new ArrayList<Courses_bean>();
		
		int st = 10 * (pg - 1); //st->starting point ; pg -> page
		
		try
		{
			results.setInt(1, st);
			results.setInt(2, 10);
					
			ResultSet resultsRs = results.executeQuery();
			
			while(resultsRs.next())
			{
				Courses_bean cb = new Courses_bean();
				
				cb.setCourse_ID(resultsRs.getInt(1));
				cb.setCourse_code(resultsRs.getString(2));
				cb.setCourse_title(resultsRs.getString(3));
				cb.setCourse_year(resultsRs.getInt(4));
				cb.setSemester(resultsRs.getString(5));
				cb.setCreated_time(resultsRs.getDate(6));
				cb.setInstructor_ID(resultsRs.getInt(7));
				
				courses_List.add(cb);
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
			
	    return courses_List;
	}
	
	
	public void addCourse(String courCode, String courTitle, int courYear, String seme, String creTime, int instID) throws SQLException 
	{
		
		try 
		{
			addCourse.setString(1, courCode);
			addCourse.setString(2, courTitle);
			addCourse.setInt(3, courYear);
			addCourse.setString(4, seme);
			addCourse.setString(5, creTime);//$$$$$ 这里没有 设置成 java.sql.Date 类型
			addCourse.setInt(6, instID);
			
			addCourse.executeUpdate();
		} 
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	
	public int getCoursesCount() 
	{
		int cnt = 0;//cnt: count
				
		try 
		{
			ResultSet rs = coursesCount.executeQuery();
			rs.next();
					
			cnt = rs.getInt(1);
		} 
		catch (SQLException sqlException) 
		{
			sqlException.printStackTrace();
		}
				
		return cnt;
	}
	
	
	public void delCourseById(int cid)
	{
		try 
		{
			delcour.setInt(1, cid);
			delcour.executeUpdate();
		} 
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	
	protected void finalize() 
	{
		try 
		{
			results.close();
		    connection.close();
		    addCourse.close();
		    delcour.close();
		    coursesCount.close();
		} 
		catch (SQLException sqlException) 
		{
			sqlException.printStackTrace();
		}
	}
}
