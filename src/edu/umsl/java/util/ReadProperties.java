package edu.umsl.java.util;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	private static Properties prop ;
		
	public static Properties getProp() {
		return prop;
	}
	
	public static void loadPropertiesFile() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		prop = new Properties();
		
		try {
			prop.load(classLoader.getResourceAsStream("config.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
	}
	
	public static String getDbDriver() {
		String temp = null;
		temp = prop.getProperty("DbDriver");
		return temp;
	}
	
	public static String getDbUrl() {
		String temp = null;
		temp = prop.getProperty("DbUrl");
		return temp;
	}	

	public static String getDbUser() {
		String temp = null;
		temp = prop.getProperty("DbUser");
		return temp;
	}	
	
	public static String getDbPswd() {
		String temp = null;
		temp = prop.getProperty("DbPswd");
		return temp;
	}
	public static String getMailUser() {
		String temp = null;
		temp = prop.getProperty("mail.user");
		return temp;
	}
	public static String getMailPswd() {
		String temp = null;
		temp = prop.getProperty("mail.pswd");
		return temp;
	}
	
}

