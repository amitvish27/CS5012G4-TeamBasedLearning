package edu.umsl.java.beans;

public class TopicBean {
	private int id;
	private String title;
	private int courseid;
	private String instructorid;

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

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getInstructorid() {
		return instructorid;
	}

	public void setInstructorid(String instructorid) {
		this.instructorid = instructorid;
	}

}
