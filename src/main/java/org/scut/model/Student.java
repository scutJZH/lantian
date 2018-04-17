package org.scut.model;

import org.springframework.stereotype.Component;

@Component
public class Student extends User{
    
	private String classId;
	private String schoolName;
	
    public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	


    

}