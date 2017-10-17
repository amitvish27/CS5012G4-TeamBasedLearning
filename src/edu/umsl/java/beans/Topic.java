package edu.umsl.java.beans;

public class Topic {
	private int id;
	private String title;
	private int course;
	private String timestamp;
	private int isDelete;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getCourse() {
		return course;
	}
	
	public void setCourse(int course) {
		this.course = course;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
