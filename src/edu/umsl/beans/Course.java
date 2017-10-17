package edu.umsl.beans;

public class Course {
	private int id;
	private String name;
	private int instructor;
	private int year;
	private int semester;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getInstructor() {
		return instructor;
	}
	
	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
}
