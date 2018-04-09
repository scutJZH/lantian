package org.scut.model;

public class T_Student_DetailsVO {
private String student_id;
private String student_name;
private int class_id;
private int class_number;
private int grade;
private String teacher_id;
private String studentAnalyse;
//get
public String getStudent_id() {
	return this.student_id;
}
public String getStudent_name()	{
	return this.student_name;
}
public int getClass_id() {
	return this.class_id;
}
public int getClass_number() {
	return this.class_number;
}
public int getGrade() {
	return this.grade;
}
public String getTeacher_id() {
	return this.teacher_id;
}
public String getStudentAnalyse() {
	return this.studentAnalyse;
}

//set
public void setStudent_id(String student_id) {
	this.student_id=student_id;
}
public void setstudent_name(String student_name) {
	this.student_name=student_name;
}
public void setClass_id(int class_id) {
	this.class_id=class_id;
}
public void setClass_number(int class_number) {
	this.class_number=class_number;
}
public void setGrade(int grade) {
	this.grade=grade;
}
public void setTeacher_id(String teacher_id) {
	this.teacher_id=teacher_id;
}
public void setStudentAnalyse(String studentAnalyse) {
	this.studentAnalyse=studentAnalyse;
}
}
