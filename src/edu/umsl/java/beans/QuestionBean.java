package edu.umsl.java.beans;

public class QuestionBean {
	private int id;
	private String content;
	private String optA;
	private String optB;
	private String optC;
	private String optD;
	private String answer;
	private String kywd;
	private String insID; // instructor's ID
	
	public String getKywd() {
		return kywd;
	}
	public void setKywd(String kywd) {
		this.kywd = kywd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOptA() {
		return optA;
	}
	public void setOptA(String optA) {
		this.optA = optA;
	}
	public String getOptB() {
		return optB;
	}
	public void setOptB(String optB) {
		this.optB = optB;
	}
	public String getOptC() {
		return optC;
	}
	public void setOptC(String optC) {
		this.optC = optC;
	}
	public String getOptD() {
		return optD;
	}
	public void setOptD(String optD) {
		this.optD = optD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getInsID() {
		return insID;
	}
	public void setInsID(String insID) {
		this.insID = insID;
	}
	

}
