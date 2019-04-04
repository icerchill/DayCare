package edu.neu.csye6200;

import java.time.LocalDate;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Student extends Person implements Comparable<Student>{
	private Date regTime;
        private LocalDate registrationTime;
	private double GPA;
	private String ParentName;
	private Map<String, Date> immTime=new Hashtable<String, Date>();
	private String studentID;
	//getters and setters
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public double getGPA() {
		return GPA;
	}
	public void setGPA(double gPA) {
		GPA = gPA;
	}
	public String getParentName() {
		return ParentName;
	}
	public void setParentName(String parentName) {
		ParentName = parentName;
	}
	public Map<String, Date> getImmTime() {
		return immTime;
	}
	public void setImmTime(Map<String, Date> immTime) {
		this.immTime = immTime;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	// constructor
	public Student() {
		super();
	}
	/**
	 * use this constructor to read CSV string
	 * @param s:id,name,parentName,age,GPA,regTime,immTime
	 */
	public Student(String s) {
		Scanner in=new Scanner(s);
		in.useDelimiter(",");
		this.studentID=in.next();
		this.setName(in.next());
		this.setParentName(in.next());
		this.setAge(in.nextInt());
		this.GPA=in.nextDouble();
		this.setRegTime(FileIO.String2Date(in.next()));
		String immunization=in.next();
		Scanner im=new Scanner(immunization);
		im.useDelimiter(";");
		while (im.hasNext()) {
			this.immTime.put(im.next(),FileIO.String2Date(im.next()));
		}
		im.close();	
		in.close();
	}
	@Override
	public String toString() {
		return "age"+this.getAge()+" Student ["+"Name  "+getName()+",regTime="+regTime + ", GPA=" + GPA + ", ParentName=" + ParentName + ", immTime=" + immTime
				+ ", studentID=" + studentID + "]";
	}
	@Override
	public int compareTo(Student o) {
		return Integer.compare(this.getAge(), o.getAge());
	}
	
	

}
