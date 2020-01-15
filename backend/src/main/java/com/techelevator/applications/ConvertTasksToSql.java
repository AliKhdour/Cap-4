package com.techelevator.applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConvertTasksToSql {

	public static void main(String[] args) throws IOException {
		FileReader aFile = null; // instantiates sets it to null
		List<String[]> allLines = new ArrayList<String[]>(); // instantiates an array list 
		String[] aLineArray = null;
		String row;
		
		try {
			aFile = new FileReader("/Users/ryanokey/Downloads/Tasks.tsv");
		}
		catch(Exception e) {
			System.out.println("Error: " +  e);
		}
		BufferedReader csvReader = new BufferedReader(aFile);

		try {
            while ((row = csvReader.readLine()) != null) {
            	aLineArray = row.split("\t");
            	int indexCount = 0;
            	for(String anElement : aLineArray) {
            		indexCount++;
            		if(anElement.contains("'")){
            			System.out.println(anElement);
            			anElement = anElement.replace("'", "''");
            			System.out.println(anElement);
            			
            			aLineArray[indexCount - 1] = anElement;
            		}
            	}
            	
            	allLines.add(aLineArray);
            }
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		FileWriter aWriter = new FileWriter("/Users/ryanokey/Downloads/outputSQL.txt");
		
		for(String[] anArray : allLines) {
			String dataString = "";
			int indexCount = 0;
			
			for(String data : anArray) {
				indexCount++;
				if(anArray.length == indexCount) {
					dataString = dataString + "'" + data + "'";
				}
				else {
				dataString = dataString + "'" + data + "',";
				}
			}
			
			aWriter.append("INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES (" + dataString + ");\n");
		}
		
		csvReader.close();
		aWriter.close();
	}
}
