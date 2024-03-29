/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.csye6200;

import java.awt.TextArea;
import java.io.File;
import java.time.LocalDate;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
/**
 *
 * @author icer
 */
public class Controller {
    
    private Database db = new Database();
    
    public void importStudent(String fn) {
        ArrayList<String> a1= FileIO.readFile(fn);        
        for (String s : a1) {
            db.addStudent(new Student(s));
        }
    }
    
    public void importTeacher(String fn) {
        ArrayList<String> a1= FileIO.readFile(fn);
        for (String s : a1) {
            db.addTeacher(new Teacher(s));
        }
    }
    
    public void importCourseRules(String fn) {
        db.setClassrommRules(FileIO.VreadFile(fn));
    }
    public void importImmRules(String fn){
        db.setImmunizationRule(FileIO.VreadFile(fn));
    }
    
    

    public Vector<Student> getStudentData(){
        return db.getStudentData();
    }
    
    public Vector<Teacher> getTeachersData(){
        return db.getTeacherData();
    }
    
    public Vector<Course> getCourses(){
        return db.getCourse();
    }
    public int getStudentCount() {
        return db.getStudentData().size();
    }
   
    public int getTeacherCount() {
        return db.getTeacherData().size();
    }
    
    public String getClassRules() {
        String result=" ";
        result+="/Age/ /GroupSize/ /Ration/ /MaxGroupinRoom/\n";
        for (String r : db.getClassrommRules()) {
            Scanner s1=new Scanner(r);
            s1.useDelimiter(",");
            result+=s1.next()+"     ";
            result+=s1.next()+"     ";
            result+=s1.next()+":1     ";
            result+=s1.next()+"\n";
            s1.close();
        }
        return result;
    }
    public boolean dataCheck(){
        if (db.getClassrommRules()==null||db.getClassrommRules().size()==0) {
            return false;
        }
        else if (db.getStudentData()==null||db.getStudentData().size()==0) {
            return false;
        }
        else if (db.getTeacherData()==null||db.getTeacherData().size()==0) {
            return false;
        }
        else
            return true;
    }
    public void courseRulesApplyer(){
        db.setCourse(new Vector<Course>());
        Vector<Group> ageGroup=Rules.groupStudentByAge(db.getStudentData(), db.getClassrommRules());
        db.setCourse(Rules.Student2Course(ageGroup, db.getClassrommRules(), db.getTeacherData()));
    }
    public String getAllCourseInfo(){
        String result="";
        Vector<Course> schedule=db.getCourse();
        for (Course course : schedule) {
            result+="ClassRoom:"+course.getCouresName()+"\n";
            for (Classroom cr : course.getClassRoom()) {
                result+=cr.getRoomID()+"/";
            }
            result+="\n";
            for (Group g : course.getCourseGroup()) {
                result+=g.getGroupID()+"\n";
                result+="Teacher:"+g.getTeacher().getName()+"\n";
                result+="Students info:\n";
                for (Student st : g.getStudentGroup()) {
                    result+=st.getName()+";";
                }
                result+="\n";
            }
            result+="\n====================\n";
        }
        return result;
    }
    public String immInfo(Student s){
        return Rules.StudentImm(s, db.getImmunizationRule());
    }
    public Student searchStudent(String name){
        return Filter.selectStudent(db.getStudentData(), name);
    }
    public boolean checkImmrule(){
        if (db.getImmunizationRule().size()==0||db.getImmunizationRule()==null) {
            return false;
        }
        else
            return true;
    }
    
    public void insertStudent(Student s) {
        db.addStudent(s);
    }
    public void deleteStudent(String s){
        for (Student st : db.getStudentData()) {
            if (st.getName().equals(s)) {
                db.getStudentData().remove(st);
                break;
            }
        }
    }
    
  
    public long reRegisteration(LocalDate d){
        return Rules.reRegisterTime(d);
    }
    
    public ArrayList<Student> showThisMonth() {
        return Filter.filterByDate(db.getStudentData());
    }
    
    public Course courseFinder(String couresName){
        Course result=new Course();
        for (Course c : db.getCourse()) {
            if (c.getCouresName().equals(couresName)) {
                result=c;
            }
        }
        return result;
    }
    public Group groupFinder(Course course,String groupName){
        Group result=new Group();
        for (Group g : course.getCourseGroup()) {
            if (g.getGroupID().equals(groupName)) {
                result=g;
            }
        }
        return result;
    }
    
    //これから新しいファンクションです
    //123
    //use this method to update registeration time return 1=success 0=no found
    public int regDateUpate(String studentName, String date){
        int status=0;
        for (Student s : db.getStudentData()) {
            if (s.getName().equals(studentName)) {
                s.setRegistrationTime(date);
                status=1;
                break;
            }
        }
        return status;
    }
    //use this to save student info
    public void saveStudent2file(String path){
        List<String> studentInfo=new ArrayList<String>();
        for (Student s : db.getStudentData()) {
            studentInfo.add(s.toString());
        }
        FileIO.writeFile(path, studentInfo);
    }
    //use this to save course info
    public void saveCourse2file(String path){
        List<String> courseInfo=new ArrayList<String>();
        courseInfo.add(this.getAllCourseInfo());
        FileIO.writeFile(path, courseInfo);
    }
    
    
}
