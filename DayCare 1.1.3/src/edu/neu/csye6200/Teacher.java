package edu.neu.csye6200;

import java.util.Scanner;

public class Teacher extends Person {
	private String TeacherID;
	private int Credit;
	// getters and setters
	public String getTeacherID() {
		return TeacherID;
	}
	public void setTeacherID(String teacherID) {
		TeacherID = teacherID;
	}
	public int getCredit() {
		return Credit;
	}
	public void setCredit(int credit) {
		Credit = credit;
	}
	//constructor
	public Teacher() {
		super();
		this.TeacherID="T000";
		this.Credit=0;
	}
	/**
	 * use this constructor to read CSV string
	 * @param s : teacherID,age,name,credit
	 */
	public Teacher(String s) {
		Scanner in=new Scanner(s);
		in.useDelimiter(",");
		this.TeacherID=in.next();
		this.setAge(in.nextInt());
		this.setName(in.next());
		this.Credit=in.nextInt();
		in.close();
	}
	
	@Override
	public String toString() {
		return "Teacher [TeacherID=" + TeacherID + ", Credit=" + Credit + "]";
	}
	

}
