package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.QuestionBean;
import edu.umsl.java.dao.QuestionDao;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/Question")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		QuestionDao questionDao = null;
		String idsearchtext = request.getParameter("search");
		idsearchtext = (idsearchtext==null)?"":idsearchtext ; 
		
		int pg = 0;
		String initpg = request.getParameter("pg");

		if (initpg != null) {
			try {
				pg = Integer.parseInt(initpg);
			} catch (NumberFormatException ex) {
				pg = 1;
			}
		}

		if (pg == 0) {
			pg = 1;
		}

		try {
			questionDao = new QuestionDao();
			
			int count = questionDao.getQuestionCount();
			int totalpg = (int) Math.ceil(count / 10.0);
			
			//Entries per page
			String recPerPgStr = (String) request.getParameter("ent");
			int entries = (recPerPgStr!=null)? Integer.parseInt(recPerPgStr):10;
			
			if (pg < 1) {
				pg = 1;
			} else if (pg > totalpg) {
				pg = totalpg;
			}
			
			List<QuestionBean> questionList = questionDao.getQuestionListByPage(idsearchtext, pg, entries);
			request.setAttribute("pcrtpg", pg);
			request.setAttribute("pmaxpg", totalpg);
			request.setAttribute("questionList", questionList);
			request.setAttribute("recPerPgStr", entries);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("questionbank.jsp");
		dispatcher.forward(request, response);
	}

}
