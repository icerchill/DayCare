package edu.neu.csye6200;

import java.util.Vector;

public class Course {
	private String couresName;
	private Vector<Group> CourseGroup=new Vector<Group>();
	private Vector<Classroom> classRoom=new Vector<Classroom>();
	//getters and setters
	public String getCouresName() {
		return couresName;
	}
	public void setCouresName(String couresName) {
		this.couresName = couresName;
	}
	public Vector<Group> getCourseGroup() {
		return CourseGroup;
	}
	public void setCourseGroup(Vector<Group> courseGroup) {
		CourseGroup = courseGroup;
	}
	public Vector<Classroom> getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(Vector<Classroom> classRoom) {
		this.classRoom = classRoom;
	}
	
	//add group 
	public void addGroup(Group g) {
		this.CourseGroup.add(g);
	}
	//add classroom
	public void addClassroom(Classroom c) {
		this.classRoom.add(c);
	}
	public Course(String couresName) {
		super();
		this.couresName = couresName;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
