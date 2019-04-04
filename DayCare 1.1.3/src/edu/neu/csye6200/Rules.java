package edu.neu.csye6200;


import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Rules {
	public static Vector<Group> groupStudentByAge(Vector<Student> studentData,Vector<String> rules) {
		Vector<Group> result=new Vector<Group>();
		int n=rules.size();      //get how many rules
		for (int i = 0; i < n; i++) {
			result.add(new Group());
		}
		//for each student, check each rule
		for (Student s : studentData) {
			int ruleNo=0;
			for (String r : rules) {
				Scanner s1=new Scanner(r);
				s1.useDelimiter(",");
				String ageRule=s1.next();
				s1.close();
				Scanner s2=new Scanner(ageRule);
				s2.useDelimiter("~");
				int min=s2.nextInt();
				int max=s2.nextInt();
				s2.close();
				if (min<=s.getAge()&& s.getAge()<=max) {
					Group tmp=result.get(ruleNo);
					tmp.addToGroup(s);
					result.set(ruleNo, tmp);
					break;
				}
				ruleNo++;
			}
		}
		return result;
	}
	public static Vector<Course> Student2Course(Vector<Group> ageGroup,Vector<String> rules,Vector<Teacher> teacherlist){
		Vector<Course> result=new Vector<Course>();
		int n=rules.size();	
		for (int i = 0; i < n; i++) {
			result.add(new Course("Course"+Integer.toString(i)));
		}
		//check each group(by age)
		int groupCount=0;
		int teacherCount=0;                //use for assign teacher
		for (Group g : ageGroup) {                  //handle each group
			String r=rules.get(groupCount);
			Scanner s1=new Scanner(r);
			s1.useDelimiter(",");
			s1.next();
			int groupSize=s1.nextInt();
			s1.next();
			int maxGroupRoom=s1.nextInt();
			s1.close();
			Course tmp=new Course();       
			tmp=result.get(groupCount);          //to update course.group.studentgroup
			tmp.setCourseGroup(groupDiveder(g,groupSize));   // use groupDivider
			Vector<Group> tupdate=tmp.getCourseGroup();     // to update teacher
			for (Group tg : tupdate) {
				tg.setTeacher(teacherlist.get(teacherCount));
				teacherCount++;
			}
			tmp.setCourseGroup(tupdate);           
			int groupinCourse=tmp.getCourseGroup().size();
			int classno=(int) Math.ceil((double)groupinCourse/(double)maxGroupRoom);
			for (int i = 0; i < classno; i++) {
				tmp.addClassroom(new Classroom(tmp.getCouresName()+"R"+Integer.toString(i+1), maxGroupRoom, tmp.getCouresName()));
			}
			result.set(groupCount, tmp);
						
			groupCount++;
		}
		return result;
	}
	@SuppressWarnings("deprecation")
	public static String StudentImm(Student s,Vector<String> immunizationRule) {
		String result="";
		Date now=new Date();
		for (String rules : immunizationRule) {
			Scanner s1=new Scanner(rules);
			s1.useDelimiter(",");
			String type=s1.next();
			String ttl=s1.next();
			s1.close();
			Map<String, Date> tmp=new HashMap<String, Date>();
                        FileIO.mapCopy(s.getImmTime(), tmp);
			if (tmp.get(type)!=null) {
				if (!ttl.equals("lifetime")) {
					Date dtmp=tmp.get(type);
					dtmp.setYear(dtmp.getYear()+Integer.parseInt(ttl));
					if (now.getTime()>=dtmp.getTime()) {
						result+=type+" is expired at"+ FileIO.Date2String(dtmp)+"; ";
					}
					else if (dtmp.getTime()-now.getTime()<=(long)183*3600*1000*24) {
						result+=type+ " will be expired at"+ FileIO.Date2String(dtmp) ;
					}
				}
			}
		}
		return result;
	}
	
	public static String reRegisterTime(Date d) {
                Date now=new Date();
                String yearNow=Integer.toString(now.getYear()+1900);      //死ね！       
		String tmp=FileIO.Date2String(d);
		Scanner s1=new Scanner(tmp);
		s1.useDelimiter("-");
		s1.next();
		String m=s1.next();
		String day=s1.next();
		s1.close();
		return yearNow+"-"+m+"-"+day;
	}
	
	private static Vector<Group> groupDiveder(Group g,int q){
		Vector<Group> result=new Vector<Group>();
		if (g==null||g.getStudentGroup().size()==0) {
			result.add(g);
			return result;
		}
		if (q<=0) {
			new IllegalArgumentException("wrong quantity");
		}
		int count=0;
		int groupCount=0;
		while (count<g.getStudentGroup().size()) {
			Vector<Student> tmp=new Vector<Student>();
			List<Student> tmpl=new ArrayList<Student>();
			tmpl=g.getStudentGroup().subList(count, (count+q)>g.getStudentGroup().size()?g.getStudentGroup().size():count+q);
			for (Student student : tmpl) {
				tmp.add(student);
			}
			Group gtmp=new Group();
			gtmp.setGroupID("Group"+Integer.toString(groupCount));
			gtmp.setStudentGroup(tmp);
			result.add(gtmp);
			count+=q;
			groupCount++;
		}
		return result;
	}
	

}
