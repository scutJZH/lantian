package org.scut.model;

public class T_Optional_SubjectVO {
private int id;
private int optional_subject;
private String teacher_id;
//get
public int getId() {
	return id;
}
public int getOptional_subject() {
	return optional_subject;
}
public String getTeacher_id() {
	return teacher_id;
}

//set
public void setId(int id) {
	this.id=id;
}
public void setOptional_subject(int optional_subject) {
	this.optional_subject=optional_subject;
}
public void setTeacher_id(String teacher_id) {
	this.teacher_id=teacher_id;
}
}
