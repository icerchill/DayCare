package edu.neu.csye6200;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Filter {
	public static Student selectStudent(Vector<Student> StudentData, String s ){
		List<Student> result=new ArrayList<Student>();
        result = StudentData.stream()
                .filter(b-> b.getName().equals(s))
                .collect(Collectors.toList());
		return result.get(0);       
	}
	public static Course selectCourse(Vector<Course> Coursedata, String s) {
		List<Course> tmp=new ArrayList<Course>();
		tmp = Coursedata.stream()
				.filter(b-> b.getCouresName().equals(s)).collect(Collectors.toList());
		return tmp.get(0);       

	}
	@SuppressWarnings("deprecation")
	public static ArrayList<Student> selectStudentAnni(Vector<Student> StudentData,Date date) {
		ArrayList<Student> result=new ArrayList<Student>();
		for (Student s : StudentData) {
			Date re=FileIO.String2Date(Rules.reRegisterTime(s.getRegTime()));
			if (date.getTime()<re.getTime()) {
				result.add(s);
			}
			else if (date.getTime()-re.getTime()<=(long)183*24*3600*1000) {
				result.add(s);
			}
			
		}
		return result;
	}
	
}

