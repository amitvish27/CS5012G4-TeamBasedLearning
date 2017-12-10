package edu.umsl.java.beans;

public class QuizBean {
	private int quizid;
	private int quiznumber;
	private int questionid;	
	private int time_limit;
	private int currQuest;
	private int totalQuest;
	private int answer;
	private String question;
	private String[] options;
	
	public int getQuizid() {
		return quizid;
	}
	public void setQuizid(int quizid) {
		this.quizid = quizid;
	}
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	public int getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	public int getCurrQuest() {
		return currQuest;
	}
	public void setCurrQuest(int currQuest) {
		this.currQuest = currQuest;
	}
	public int getTotalQuest() {
		return totalQuest;
	}
	public void setTotalQuest(int totalQuest) {
		this.totalQuest = totalQuest;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public int getQuiznumber() {
		return quiznumber;
	}
	public void setQuiznumber(int quiznumber) {
		this.quiznumber = quiznumber;
	}
	
	
}
