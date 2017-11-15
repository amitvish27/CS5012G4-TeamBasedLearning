package edu.umsl.java.util;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

public class GeneratePassword {
	
	public static void generateTempPassword (String ssoid, UserBean user) throws Exception {
		ReadProperties.loadPropertiesFile();
		
		String randString = new RandomString(8).nextString();
		UserDao instDao = new UserDao();
		instDao.saveInstPswd(ssoid, randString, ssoid);

		// email the password to the user's email given
		String mailFrom = ReadProperties.getMailUser();
		String mailPswd = ReadProperties.getMailPswd();
		String mailSub = "UMSLTeamBasedLearning-DoNotReply";
		String mailMsg = "Hi " + user.getFname() + "," + "\n\nYour temporary password is: " + randString
				+ "\n\nWe advise you to change your password when you login."
				+ "\n\nThis is an auto generated mail. Please do not reply directly to this mail."
				+ "\n\nBest Regards,\nAdmin Team";
		if(ReadProperties.getSendMail()) {
			MailApi.send(mailFrom, mailPswd, user.getEmail(), mailSub, mailMsg);	
		}
		
	}
}
