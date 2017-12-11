package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.java.beans.QuesQuizBean;
import edu.umsl.java.beans.QuestionBean;
import edu.umsl.java.beans.QuizSetupBean;
import edu.umsl.java.util.ReadProperties;

public class QuizesDao {
	private Connection connection;

	private PreparedStatement getQuizNumUnderCourse;
	private PreparedStatement addQuiz;
	private PreparedStatement delQuizById;
	private PreparedStatement getQuizInfo;
	private PreparedStatement getAllQuestions;
	private PreparedStatement getQuesQuizInfo;
	private PreparedStatement delRelationshipById;
	private PreparedStatement createRelationship;

	public QuizesDao() throws Exception
	{
		try
		{
			ReadProperties.loadPropertiesFile();
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());

			getQuizNumUnderCourse = connection.prepareStatement("SELECT courseid, number, time_limit, start_time, end_time, created FROM quiz WHERE courseid = ? AND deleted = 0 ");
			addQuiz = connection.prepareStatement("INSERT INTO quiz(courseid, number, time_limit, start_time, end_time,instructorid ) VALUES (?, ?, ?, ?, ?, ?)");
			delQuizById = connection.prepareStatement("UPDATE quiz SET deleted = 1 WHERE id = ?");
			getQuizInfo = connection.prepareStatement("SELECT * FROM quiz WHERE id = ? AND deleted = 0");
			getAllQuestions = connection.prepareStatement("SELECT * FROM question WHERE deleted = 0");
			getQuesQuizInfo = connection.prepareStatement("SELECT * FROM quest_quiz WHERE quizid = ? AND deleted = 0");
			delRelationshipById = connection.prepareStatement("UPDATE quest_quiz SET deleted = 1 WHERE relnid = ?");
			createRelationship = connection.prepareStatement("INSERT INTO quest_quiz(questid, quizid, questnumber, deleted) VALUES (?, ?, 1, 0)");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new UnavailableException(exception.getMessage());
		}
	}

	public List<QuizSetupBean> getQuizNumUnderCourse(int cid) {
		List<QuizSetupBean> quizList = new ArrayList<QuizSetupBean>();
		try {
			getQuizNumUnderCourse.setInt(1, cid);
			ResultSet resultsRs = getQuizNumUnderCourse.executeQuery();

			while (resultsRs.next()) {
				QuizSetupBean qb = new QuizSetupBean();
				// qb.setQuiz_ID(resultsRs.getInt(1));
				qb.setCourse_ID(resultsRs.getInt(1));
				qb.setQuiz_number(resultsRs.getInt(2));
				qb.setTime_limit(resultsRs.getInt(3));
				qb.setStart_time(resultsRs.getString(4));
				System.out.println(resultsRs.getString(4));
				qb.setEnd_time(resultsRs.getString(5));
				qb.setCreated_time(resultsRs.getString(6));
				// qb.setInstructor_ID(resultsRs.getString(8));
				quizList.add(qb);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return quizList;
	}

	public void addQuiz(int cid, int qNum, int timLim, String starTim, String endTim, String instrId) {

		try {
			addQuiz.setInt(1, cid);
			addQuiz.setInt(2, qNum);
			addQuiz.setInt(3, timLim);
			addQuiz.setString(4, starTim);
			addQuiz.setString(5, endTim);
			addQuiz.setString(6, instrId);
			addQuiz.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public void delQuizById(int pid) {
		try {
			delQuizById.setInt(1, pid);
			delQuizById.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public String[] getQuizInfoById(int qid) {
		String[] quizInfoArr = new String[7];

		try {
			getQuizInfo.setInt(1, qid);
			ResultSet rs = getQuizInfo.executeQuery();
			rs.next();

			quizInfoArr[0] = String.valueOf(rs.getInt(2)); // Course ID
			quizInfoArr[1] = String.valueOf(rs.getInt(3)); // quiz number
			quizInfoArr[2] = String.valueOf(rs.getInt(4)); // time limit
			quizInfoArr[3] = rs.getString(5); // start time
			quizInfoArr[4] = rs.getString(6); // end time
			quizInfoArr[5] = rs.getString(7); // create time
			quizInfoArr[6] = String.valueOf(rs.getInt(8)); // instructor ID

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizInfoArr;
	}

	public List<QuestionBean> getAllQuestions() {
		List<QuestionBean> QuestionBeanList = new ArrayList<QuestionBean>();
		try {
			ResultSet resultsRs = getAllQuestions.executeQuery();

			while (resultsRs.next()) {
				QuestionBean qb = new QuestionBean();
				qb.setId(resultsRs.getInt(1));
				qb.setContent(resultsRs.getString(2));
				qb.setKywd(resultsRs.getString(8));
				qb.setInsID(resultsRs.getString(10));

				QuestionBeanList.add(qb);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return QuestionBeanList;
	}

	public List<QuesQuizBean> getQuesQuizInfo(int qid) {

		List<QuesQuizBean> quesQuizBeanList = new ArrayList<QuesQuizBean>();

		try {
			System.out.println("QQB DAO:" + qid);
			getQuesQuizInfo.setInt(1, qid);
			ResultSet resultsRs = getQuesQuizInfo.executeQuery();
			while (resultsRs.next()) {
				QuesQuizBean qqb = new QuesQuizBean();

				qqb.setRelnid(resultsRs.getInt(1));
				qqb.setQuestid(resultsRs.getInt(2));
				qqb.setQuizid(resultsRs.getInt(3));

				quesQuizBeanList.add(qqb);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		System.out.println("QQB DAO:" + quesQuizBeanList.size());
		return quesQuizBeanList;

	}

	public void delRelationshipById(int relnid) {
		try {
			System.out.println("relnid" + relnid);
			delRelationshipById.setInt(1, relnid);
			delRelationshipById.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public void createRelationship(int questionId, int currQuizId) {
		try {
			createRelationship.setInt(1, questionId);
			createRelationship.setInt(2, currQuizId);
			createRelationship.executeUpdate();

			createRelationship.executeQuery();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

}
