package org.scut.model;

import org.springframework.stereotype.Component;

@Component
public class Student extends User{
    
	private String classId;
	private String schoolId;
	
    public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId){
		this.schoolId = schoolId;
	}
}