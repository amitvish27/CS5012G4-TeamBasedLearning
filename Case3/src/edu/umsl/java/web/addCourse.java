package edu.umsl.java.web;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.CoursesDao;

/**
 * Servlet implementation class addCourse
 */
@WebServlet("/addCourse")
public class addCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
		
		CoursesDao courdao = null;
		
		String courCode = request.getParameter("course_code");//$$$$
		String courTitle = request.getParameter("course_title");
		String seme = request.getParameter("addCourseSeletor");
		int instr_ID = Integer.parseInt(request.getParameter("instructor_id"));
		
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String modifiedYear = new SimpleDateFormat("yyyy").format(date);
		String createdTime = modifiedDate;
		int courYear = Integer.parseInt(modifiedYear);
		
		int err = 0;
		
		if (courCode == null || courTitle == null || instr_ID == 0 ) 
		{
			err = 1;			
		}
		else 
		{
			try 
			{
				courdao = new CoursesDao();
				courdao.addCourse(courCode, courTitle, courYear, seme, createdTime, instr_ID);	
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("CoursesServlet?err=" + err);
	}

}
