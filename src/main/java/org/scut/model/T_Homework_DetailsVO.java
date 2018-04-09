package org.scut.model;
import java.util.*;

public class T_Homework_DetailsVO {
private int homework_ID;
private String teacher_id;
private String homework_name;
private Date homework_time;
private int selected_class;
private int selected_subject;
private int submitted_number;
private int unsubmitted_number;
private int corrected_number;
private int uncorrected_number;
private int total_number;
private int rank_id;
public int getHomework_ID() {
	return homework_ID;
}
public String getTeacher_id(){
	return teacher_id;
}
public String getHomework_name() {
	return homework_name;
}
public Date getHomework_time() {
	return homework_time;
}
public int getSelected_class() {
	return selected_class;
}
public int getSelected_subject() {
	return selected_subject;
}
public int getSubmitted_number() {
	return submitted_number;
}
public int getUnsubmitted_number() {
	return unsubmitted_number;
}
public int getCorrected_number() {
	return corrected_number;
}
public int getUncorrected_number() {
	return uncorrected_number;
}
public int getTotal_number() {
	return total_number;
}
public int getRank_id() {
	return rank_id;
}
//set
public void setHomework_ID(int homework_ID) {
	this.homework_ID=homework_ID;
}
public void setTeacher_id(String teacher_id){
	this.teacher_id=teacher_id;
}
public void setHomework_name(String homework_name) {
	this.homework_name=homework_name;
}
public void setHomework_time(Date homework_time) {
	this. homework_time=homework_time;
}
public void setSelected_class(int selected_class) {
	this. selected_class=selected_class;
}
public void setSelected_subject(int selected_subject) {
	this. selected_subject=selected_subject;
}
public void setSubmitted_number(int submitted_number) {
	this. submitted_number=submitted_number;
}
public void setUnsubmitted_number(int unsubmitted_number) {
	this. unsubmitted_number=unsubmitted_number;
}
public void setCorrected_number(int corrected_number) {
	this. corrected_number=corrected_number;
}
public void setUncorrected_number(int uncorrected_number) {
	this. uncorrected_number=uncorrected_number;
}
public void setTotal_number(int total_number) {
	this. total_number=total_number;
}
public void setRank_id(int rank_id) {
	this. rank_id=rank_id;
}
}
