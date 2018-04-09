package org.scut.model;

public class T_PptVO {
	private int ppt_id;
	private String teacher_id;

	//get
	public int getPpt_id() {
		return ppt_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	//set
	public void setPpt_id(int ppt_id) {
		this.ppt_id=ppt_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id=teacher_id;
	}
}
