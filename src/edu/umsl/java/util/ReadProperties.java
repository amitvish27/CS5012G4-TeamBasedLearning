package edu.umsl.java.util;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	private static Properties prop ;
	
	public ReadProperties() {
		loadPropertiesFile();
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
	
	public String getDbDriver() {
		String temp = null;
		temp = prop.getProperty("DbDriver");
		return temp;
	}
	
	public String getDbUrl() {
		String temp = null;
		temp = prop.getProperty("DbUrl");
		return temp;
	}	

	public String getDbUser() {
		String temp = null;
		temp = prop.getProperty("DbUser");
		return temp;
	}	
	
	public String getDbPswd() {
		String temp = null;
		temp = prop.getProperty("DbPswd");
		return temp;
	}
}
