package edu.neu.csye6200;

import java.util.Vector;

public class Database {
	private Vector<Student> studentData=new Vector<Student>();
	private Vector<Teacher> teacherData=new Vector<Teacher>();
	private Vector<String> classrommRules=new Vector<String>();
	private Vector<String> immunizationRule=new Vector<String>();
	private Vector<Course>  course= new Vector<Course>();
	//getters and setters
	public Vector<Student> getStudentData() {
		return studentData;
	}
	public void setStudentData(Vector<Student> studentData) {
		this.studentData = studentData;
	}
	public Vector<Teacher> getTeacherData() {
		return teacherData;
	}
	public void setTeacherData(Vector<Teacher> teacherData) {
		this.teacherData = teacherData;
	}
	public Vector<String> getClassrommRules() {
		return classrommRules;
	}
	public void setClassrommRules(Vector<String> classrommRules) {
		this.classrommRules = classrommRules;
	}
	public Vector<String> getImmunizationRule() {
		return immunizationRule;
	}
	public void setImmunizationRule(Vector<String> immunizationRule) {
		this.immunizationRule = immunizationRule;
	}
	public Vector<Course> getCourse() {
		return course;
	}
	public void setCourse(Vector<Course> course) {
		this.course = course;
	}
	//add student
	public void addStudent(Student s) {
		this.studentData.add(s);
	}
	//add teacher
	public void addTeacher(Teacher t) {
		this.teacherData.add(t);
	}
	

}
