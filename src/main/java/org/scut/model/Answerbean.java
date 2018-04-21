package org.scut.model;


import org.springframework.stereotype.Component;

@Component
public class Answerbean extends Answer {
	private String studentName;
	private String picPath;
	private boolean islike;
	
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public boolean isIslike() {
		return islike;
	}

	public void setIslike(boolean islike) {
		this.islike = islike;
	}

}
