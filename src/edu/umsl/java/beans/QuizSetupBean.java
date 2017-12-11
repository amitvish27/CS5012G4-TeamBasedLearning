package edu.umsl.java.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuizSetupBean {
	private int quiz_ID;
	private int course_ID;
	private int quiz_number;
	private int time_limit;
	private String start_time;
	private String end_time;
	private String created_time;
	private String instructor_ID;
	
	public int getQuiz_ID() {
		return quiz_ID;
	}
	public void setQuiz_ID(int quiz_ID) {
		this.quiz_ID = quiz_ID;
	}
	public int getCourse_ID() {
		return course_ID;
	}
	public void setCourse_ID(int course_ID) {
		this.course_ID = course_ID;
	}
	public int getQuiz_number() {
		return quiz_number;
	}
	public void setQuiz_number(int quiz_number) {
		this.quiz_number = quiz_number;
	}
	public int getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	public String getStart_time() {
		return start_time;
		
	}
	public void setStart_time(String start_time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
		this.start_time = dateFormat.format(date);
	}
	public String getEnd_time() {
	   return this.end_time;
	}
	public void setEnd_time(String end_time) {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
	    this.end_time=dateFormat.format(date);
	}
	public String getCreated_time() {
		 return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getInstructor_ID() {
		return instructor_ID;
	}
	public void setInstructor_ID(String instructor_ID) {
		this.instructor_ID = instructor_ID;
	}
}
