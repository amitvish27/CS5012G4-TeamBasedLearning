package edu.umsl.java.beans;

public class UserBean {
	private int id;
	private String ssoid;
	private String pswd;
	private String fname;
	private String lname;
	private String email;
	private String dept;
	private String createdby;
	private String modifiedby;
	private int role;
	private int active;
	private int deleted;
	private int view1;
	private int view2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSsoid() {
		return ssoid;
	}

	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getView1() {
		if (this.role == 0)
			this.view1 = 0;
		else if (this.role == 1)
			this.view1 = 2;

		return view1;
	}

	public int getView2() {
		if (this.role == 2) // admin
			this.view2 = 1;
		else if (this.role == 1) // instructor
			this.view2 = 2;

		return view2;
	}
}
