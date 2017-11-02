package edu.umsl.java.bean;

public class Courses_bean 
{
	private int course_ID;
	private String course_code;
	private String course_title;
	private int course_year;
	private String semester;
	private java.sql.Date created_time;
	private int instructor_ID;
	
	
	public int getCourse_ID() {
		return course_ID;
	}
	public void setCourse_ID(int course_ID) {
		this.course_ID = course_ID;
	}
	
	
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	
	
	public String getCourse_title() {
		return course_title;
	}
	public void setCourse_title(String course_title) {
		this.course_title = course_title;
	}
	
	
	public int getCourse_year() {
		return course_year;
	}
	public void setCourse_year(int course_year) {
		this.course_year = course_year;
	}
	
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	
	public java.sql.Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(java.sql.Date created_time) {
		this.created_time = created_time;
	}
	
	
	public int getInstructor_ID() {
		return instructor_ID;
	}
	public void setInstructor_ID(int instructor_ID) {
		this.instructor_ID = instructor_ID;
	}
}
