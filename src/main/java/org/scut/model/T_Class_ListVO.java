package org.scut.model;

public class T_Class_ListVO {
private String student_id;
private String student_name;
private int class_id;
private String class_name;
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
public String getClass_name() {
	return this.class_name;
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
public void setClass_name(String class_name) {
	this.class_name=class_name;
}
public void setTeacher_id(String teacher_id) {
	this.teacher_id=teacher_id;
}
public void setStudentAnalyse(String studentAnalyse) {
	this.studentAnalyse=studentAnalyse;
}
}
