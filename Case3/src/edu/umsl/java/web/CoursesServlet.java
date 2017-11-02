package edu.umsl.java.web;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.bean.Courses_bean;
import edu.umsl.java.dao.CoursesDao;

/**
 * Servlet implementation class CoursesServlet
 */
@WebServlet("/CoursesServlet")
public class CoursesServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		CoursesDao cous_dao = null;
		
		int pg = 0;
		String initpg = request.getParameter("pg");//$$$$这条语句什么意思 是从jsp(view)中拿回用户输入的页数值么？
		System.out.println(pg);
		if (initpg != null)
		{
			try 
			{
				pg = Integer.parseInt(initpg);
			} 
			catch (Exception e) 
			{
				pg = 1;
			}
		}

		if (pg == 0) 
		{
			pg = 1;
		}
		
		
		try 
		{

			cous_dao = new CoursesDao();
			int cnt = cous_dao.getCoursesCount();
			int totalpg = (int) Math.ceil(cnt / 10.0);
			
			List<Courses_bean> courses_List = cous_dao.getCoursesListByPage(pg);
			
			if (pg < 1) 
			{
				pg = 1;
			} 
			else if (pg > totalpg)
			{
				pg = totalpg;
			}
			
			
			request.setAttribute("maxpg", totalpg);//maxpg: maximum page
			request.setAttribute("courses_List", courses_List);
			request.setAttribute("crtpg", pg);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Courses_list.jsp");
		dispatcher.forward(request, response);
		

	}

	

}
