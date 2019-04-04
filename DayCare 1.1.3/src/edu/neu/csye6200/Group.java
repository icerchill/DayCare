package edu.neu.csye6200;

import java.util.Vector;

public class Group {
	private Vector<Student> studentGroup=new Vector<Student>();
	private Teacher teacher;
	private String groupID;

	public Vector<Student> getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(Vector<Student> studentGroup) {
		this.studentGroup = studentGroup;
	}
	public void addToGroup(Student s) {
		this.studentGroup.add(s);
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	



}
