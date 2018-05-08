package com.wine.person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class MaxSell {
	public static final Logger logs=Logger.getLogger("MaxSell.class");
	public static void main(String [] args) {
		String filePath="";
		CSVReader csvReader=null;
		if(args.length==0) {
			logs.info("Argument lenth is invalid");
			System.exit(0);
		}else {
			filePath=args[0];
		}
		
		
		try {
			csvReader=new CSVReader(new FileReader(filePath)); // Read CSV files
			 String[] line;
			 ArrayList<Person> persons=new ArrayList<Person>();
			 ArrayList<Wine> wines=new ArrayList<Wine>();
	            while ((line = csvReader.readNext()) != null) {
	            	String str[]=line[0].split("\t");
	            Person person=new Person(str[0]);
	            persons.add(person);
	            Wine wine=new Wine(str[1]);
	            wines.add(wine);
	            }
	            HashMap<String,ArrayList<String>> winePersonPairs =maxCombinationOfWinePerson(persons, wines);
	            printWinePersonPair(winePersonPairs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// max combination of sells count
	public static HashMap<String,ArrayList<String>> maxCombinationOfWinePerson(ArrayList<Person> persons,ArrayList<Wine> wines) {
		HashMap<String,ArrayList<String>> winePersonPairs=new HashMap<>();
		  HashSet<String> set=new HashSet<String>();  		
		for(Wine w:wines) {
			set.add(w.getWineId());
		}
		int i=1;
		int j=1;
		int count=0;
		Iterator<String> it=set.iterator();
		while(it.hasNext()) {
			
			ArrayList<String> ps=new ArrayList<String>();
			for(j=i;j<persons.size();j++) {
				ps.add(persons.get(j).getPersonId());
				i++;
				String temp="";
				if(ps.size()==0 ||ps.size()==1 ) {
					temp=ps.get(0);
				}else {
					temp=ps.get(ps.size()-1);
				}
				if(temp.equals(persons.get(j).getPersonId())) {
					count++;
				if(j%3==0|| count==3) 
					 {
					break;
				}
				}
			}
			winePersonPairs.put(it.next(), ps);
			j=i;
		}
		
		return winePersonPairs;
		
	}
	//write to Csv files
	public static void printWinePersonPair(HashMap<String,ArrayList<String>> winePersonPairs) throws IOException {
		ArrayList<String> lines=new ArrayList<>();
		for(Map.Entry<String, ArrayList<String>> entry:winePersonPairs.entrySet()) {
			String key=entry.getKey();
			ArrayList<String> values=entry.getValue();
			lines.add(values+" "+ key );
			System.out.println( lines );
			
		}
		lines.add(0, "Max sell:"+winePersonPairs.size());
		FileWriter fwriter=new FileWriter("/home/gautam/Pictures/winepaire.csv");
		CSVWriter writer=new CSVWriter(fwriter);
		String str[]=lines.toArray(new String[lines.size()]);
		writer.writeNext(str,false);
		writer.close();
	}

}
