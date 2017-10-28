package edu.umsl.java.beans;

public class UserBean {

	private String id;
	private String ssoid;
	private String pwd;
	private int role;
	private String dept;
	private String fname;
	private String lname;
	private String snumber;
	private boolean active;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSsoid() {
		return ssoid;
	}
	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	 
}
