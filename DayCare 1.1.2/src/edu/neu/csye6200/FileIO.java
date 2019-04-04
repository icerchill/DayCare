package edu.neu.csye6200;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class FileIO {
	//write List<String> to text file
		public static void writeFile(String fileName,List<String> wData) {
			System.out.println("writeFile to "+fileName);
			try(BufferedWriter wr=new BufferedWriter(new FileWriter(fileName))){
				for (String r : wData) {
					wr.write(r);
					wr.newLine();
				}
				wr.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		//read file then return ArrayList<String>
		public static ArrayList<String> readFile(String fileName){
			System.out.println("readFile from "+fileName);
			ArrayList<String> result=new ArrayList<String>();
			try (BufferedReader br=new BufferedReader(new FileReader(fileName))){
				String line="";
				while ((line=br.readLine())!=null) {
					result.add(line);
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		//read file then return Vector<String>
		public static Vector<String> VreadFile(String fileName){
			System.out.println("readFile from "+fileName);
			Vector<String> result=new Vector<String>();
			try (BufferedReader br=new BufferedReader(new FileReader(fileName))){
				String line="";
				while ((line=br.readLine())!=null) {
					result.add(line);
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		
		// date/string convert
		public static String Date2String(Date d) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(d);
		}
		public static Date String2Date(String s) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date d=new Date();
			try {
				d=sdf.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return d;
		}
                public static void mapCopy(Map paramsMap,Map resultMap){
                    if (resultMap==null) {
                        resultMap=new HashMap();
                    }
                    if(paramsMap==null){
                        return;
                    }
                    Iterator it=paramsMap.entrySet().iterator();
                    while(it.hasNext()){
                        Map.Entry entry=(Map.Entry)it.next();
                        Object key=entry.getKey();
                        resultMap.put(key,FileIO.String2Date(FileIO.Date2String((Date)paramsMap.get(key))));
                    }
                }
}
