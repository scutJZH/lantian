package org.scut.model;

public class T_ClassVO {
private int id;
private int optional_class;
private String teacher_id;
//get
public int getId() {
	return id;
}
public int getOptional_class() {
	return optional_class;
}
public String getTeacher_id() {
	return teacher_id;
}

//set
public void setId(int id) {
	this.id=id;
}
public void setOptional_class(int optional_class) {
	this.optional_class=optional_class;
}
public void setTeacher_id(String teacher_id) {
	this.teacher_id=teacher_id;
}
}
