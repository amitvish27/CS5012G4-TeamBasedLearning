package edu.umsl.java.beans;

public class Instructor {
	private int id;
	private String first_name;
	private String SSO_ID;
	private String password;
	private String last_name;
	private String department;
	private String time_created;
	private int del;
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String deaprtment) {
		this.department = deaprtment;
	}

	public String getTime_created() {
		return time_created;
	}

	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSSO_ID() {
		return SSO_ID;
	}
	public void setSSO_ID(String sSO_ID) {
		SSO_ID = sSO_ID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
