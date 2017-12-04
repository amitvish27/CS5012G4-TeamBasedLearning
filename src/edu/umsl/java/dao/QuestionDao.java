package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;

import edu.umsl.java.beans.QuestionBean;
//import edu.umsl.java.beans.Topic;
import edu.umsl.java.util.ReadProperties;

public class QuestionDao {
	private Connection connection;
	private PreparedStatement setInstructor;
	private PreparedStatement questioncnt;
	private PreparedStatement questionpg;
	private PreparedStatement addQuestion;
	private PreparedStatement saveQuestion;
	private PreparedStatement getQuestion;
	private PreparedStatement delQuestion;

	private List<QuestionBean> questionList;
	private List<QuestionBean> qiList;
	private List<QuestionBean> tempList;

	public QuestionDao() throws Exception {
		try {
			ReadProperties.loadPropertiesFile();
			;
			Class.forName(ReadProperties.getDbDriver());
			connection = DriverManager.getConnection(ReadProperties.getDbUrl(), ReadProperties.getDbUser(),
					ReadProperties.getDbPswd());

			/*
			 * results = connection.prepareStatement(
			 * "SELECT id, content, opt_a, opt_b, opt_c, opt_d, answer, kywd, instructorid "
			 * + "FROM question WHERE deleted=0 " + "ORDER BY created DESC ");
			 */

			setInstructor = connection
					.prepareStatement("SELECT id, content, opt_a, opt_b, opt_c, opt_d, answer, kywd, instructorid"
							+ "FROM question WHERE instructorid=? " + "ORDER BY created DESC ");

			questioncnt = connection.prepareStatement("SELECT COUNT(id) FROM question " + "WHERE deleted=0");

			questionpg = connection.prepareStatement("SELECT id, content, opt_a, opt_b, opt_c, opt_d, answer, kywd "
					+ "FROM question WHERE deleted=0 ORDER BY created DESC LIMIT ?, ? ");

			addQuestion = connection
					.prepareStatement("INSERT INTO question (content, opt_a, opt_b, opt_c, opt_d, answer, kywd, instructorid) "
							+ "VALUES (?,?,?,?,?,?,?,?)");

			getQuestion = connection.prepareStatement(
					"SELECT content, opt_a, opt_b, opt_c, opt_d, answer, kywd FROM question WHERE id = ? AND deleted = 0");

			saveQuestion = connection.prepareStatement(
					"UPDATE question SET content = ?,opt_a = ?,opt_b = ?,opt_c = ?,opt_d = ?, answer = ?,kywd = ?,insID = ? WHERE id = ? AND deleted = 0");

			delQuestion = connection.prepareStatement("UPDATE question SET deleted = 1 WHERE id = ?");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UnavailableException(ex.getMessage());
		}
	}

	public void setQuestionInstructor(String instructor) {
		try {
			setInstructor.setString(1, instructor);
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public List<QuestionBean> getQuestionListByInstructor() {
		qiList = new ArrayList<QuestionBean>();

		try {
			ResultSet res = setInstructor.executeQuery();

			while (res.next()) {
				QuestionBean question = new QuestionBean();
				question.setId(res.getInt(1));
				question.setContent(res.getString(2));
				question.setOptA(res.getString(3));
				question.setOptB(res.getString(4));
				question.setOptC(res.getString(5));
				question.setOptD(res.getString(6));
				question.setAnswer(res.getString(7));
				question.setKywd(res.getString(8));
				// question.setInsID(res.getInt(9));
				// question.setIns(res.getString(10));
				qiList.add(question);
			}

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		return qiList;
	}

	protected void finalize() {
	}

	public List<QuestionBean> getQuestionByKeyword(String kywd) {
		tempList = new ArrayList<>();
		String[] part1 = kywd.split(",");
		int len1 = part1.length;
		for (QuestionBean c : questionList) {
			int length = 0;
			String[] part2 = c.getKywd().split(",");
			int len2 = part2.length;
			for (int i = 0; i < len1 - 1; i++) {
				for (int j = 0; j < len2 - 1; j++) {
					if (part1[i].trim() == part2[j].trim()) {
						length++;
					}
				}
			}
			if (length == len1) {
				tempList.add(c);
			}
		}
		return tempList;
	}

	public int getQuestionCount() {
		int count = 0;

		try {
			ResultSet res = questioncnt.executeQuery();
			res.next();
			count = res.getInt(1);
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

		return count;
	}

	public List<QuestionBean> getQuestionListByPage(int pg) {

		questionList = new ArrayList<QuestionBean>();

		int st = 10 * (pg - 1);

		try {
			questionpg.setInt(1, st);
			questionpg.setInt(2, 10);

			ResultSet res = questionpg.executeQuery();

			while (res.next()) {
				QuestionBean question = new QuestionBean();

				question.setId(res.getInt(1));
				question.setContent(res.getString(2));
				question.setOptA(res.getString(3));
				question.setOptB(res.getString(4));
				question.setOptC(res.getString(5));
				question.setOptD(res.getString(6));
				question.setAnswer(res.getString(7));
				question.setKywd(res.getString(8));

				questionList.add(question);
			}

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}

		return questionList;
	}

	public void saveQuestion(String content, String opta, String optb, String optc, String optd, String answer,
			String kywd, String insID, int id) {
		try {
			saveQuestion.setString(1, content);
			saveQuestion.setString(2, opta);
			saveQuestion.setString(3, optb);
			saveQuestion.setString(4, optc);
			saveQuestion.setString(5, optd);
			saveQuestion.setString(6, answer);
			saveQuestion.setString(7, kywd);
			saveQuestion.setString(8, insID);
			saveQuestion.setInt(9, id);
			saveQuestion.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public String[] getQuestionById(int id) {
		String[] question = new String[7];

		try {
			ResultSet res = getQuestion.executeQuery();

			while (res.next()) {
				question[0] = res.getString(1);
				question[1] = res.getString(2);
				question[2] = res.getString(3);
				question[3] = res.getString(4);
				question[4] = res.getString(5);
				question[5] = res.getString(6);
				question[6] = res.getString(7);
			}

		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
		return question;
	}

	public void addQuestion(String content, String opta, String optb, String optc, String optd, String answer,
			String kywd, String instructor) {
		try {
			//FIXME "INSERT INTO question (content, opt_a, opt_b, opt_c, opt_d, answer, kywd, insID) "
					//+ "VALUES (?,?,?,?,?,?,?,?)"
			addQuestion.setString(1, content);
			addQuestion.setString(2, opta);
			addQuestion.setString(3, optb);
			addQuestion.setString(4, optc);
			addQuestion.setString(5, optd);
			addQuestion.setString(6, answer);
			addQuestion.setString(7, kywd);
			addQuestion.setString(8, instructor);

			addQuestion.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}

	public void delQuestionById(int id) {
		try {
			delQuestion.setInt(1, id);

			delQuestion.executeUpdate();
		} catch (SQLException sql_ex) {
			sql_ex.printStackTrace();
		}
	}
}