package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.Instructor;
import edu.umsl.java.dao.InstructorDao;
import edu.umsl.java.util.RandomString;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String divclass = "modal-dialog";
		String msgText = "";
		
		String ssoid = request.getParameter("mod_ssoid");
		String email = request.getParameter("mod_email");
		
		try {
			if (ssoid==null || email==null || ssoid.equals("") || email.equals(""))
			{
				//if field check failed
				divclass = "modal-dialog alert alert-danger";
				msgText = "Fields cannot be blank or NULL.";
			}
			else {
				Instructor inst = new InstructorDao().getInstructorBySsoId(ssoid);
				
				if(inst.getSsoid()!=null && inst.getEmail().equals(email)) {
					// Success, create a random password and set the db
					RandomString randString = new RandomString(6);
					InstructorDao instDao = new InstructorDao();
					instDao.saveInstPswd(ssoid, randString.nextString(), ssoid);
					
					//email the password to the user's email given 
					
					divclass = "modal-dialog alert alert-success";
					msgText = "An Email has been sent out with a temporary password. Please check your email.";
				}
				else {
					//say that email and ssoid did not match system
					divclass = "modal-dialog alert alert-danger";
					msgText = "SSO ID and Email did not match our system.";
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String htmlData = "<div class=\""+divclass+"\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n" + 
				"							aria-label=\"Close\">\r\n" + 
				"							<span aria-hidden=\"true\">&times;</span>\r\n" + 
				"						</button><p>"+msgText+"</p></div>";
		
		out.println(htmlData);
		out.flush();
		out.close();
	}

}
