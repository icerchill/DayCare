package edu.neu.csye6200;

import java.time.LocalDate;
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
			if (date.getTime()>re.getTime()) {
				result.add(s);
			}
			else if (re.getTime()-date.getTime()<=(long)60*24*3600*1000) {
				result.add(s);
			}
			
		}
		return result;
	}
        
        public static ArrayList<Student> filterByDate(Vector<Student> studentDate) {
            ArrayList<Student> result=new ArrayList<Student>();
            studentDate.stream().filter((s) -> (s.getRegistrationTime().getMonth()== LocalDate.now().getMonth() 
                    && s.getRegistrationTime().getDayOfMonth() > LocalDate.now().getDayOfMonth())
                    ||s.getRegistrationTime().getDayOfYear()-LocalDate.now().getDayOfYear()<0).forEach((s) -> {
                        result.add(s);
                    });
            return result;
        }
        
        
	
}

