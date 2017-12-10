package edu.umsl.java.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class QuerySampler {

	static String insert ="UPDATE `topic` SET `instructorid`=";
	
	static String end = " WHERE id=";
    
	public static ArrayList<ArrayList<String>> readFile(String filename) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                list.add(new ArrayList<>(Arrays.asList(tokens)));
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return list;
    }
    
    public static ArrayList<String> readListFile(String filename) {
        ArrayList<String> list = new ArrayList<>();
        try {
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
                String t[] = line.split(",");
				list.add(insert+t[0]+end+t[1]+";");
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return list;
    }
    
    public static ArrayList<String> getSSOidList(ArrayList<ArrayList<String>> superList) {
    	ArrayList<String> list = new ArrayList<>();
    	for(ArrayList<String> tempList: superList ) {
    		RandomString rs = new RandomString(4);
    		String temp = String.valueOf(tempList.get(0).charAt(0)).toLowerCase()
    					+ String.valueOf(tempList.get(1).charAt(0)).toLowerCase()
    					+ rs.nextString();
    		list.add(temp);
    	}
    	return list;
    }
    
    public static ArrayList<String> getEmailList(ArrayList<ArrayList<String>> superList) {
    	ArrayList<String> list = new ArrayList<>();
    	for(ArrayList<String> tempList: superList ) {
    		String temp = tempList.get(0) + "." + tempList.get(1) + "@mail.com";
    		list.add(temp);
    	}
    	return list;
    }
	public static void main(String[] args) {
		/*for(String temp : readListFile("listcsv")) {
			System.out.println(temp);
		}*/
		UUID uuid = UUID.randomUUID();
		
		System.out.println(uuid.toString());
	}

}
