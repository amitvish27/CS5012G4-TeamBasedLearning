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
	private int active;
	private String email;
	private int view1;
	private int view2;
	
	
	public int getView1() {
		if (this.role == 0) 
			this.view1 =0;
		else if (this.role==1)
			this.view1=2;
		
		return view1;
	}
	public int getView2() {
		if (this.role == 0) 
			this.view2 =1;
		else if (this.role==1)
			this.view2=2;
		
		return view2;
	}
	
	
	
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
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	 
}
