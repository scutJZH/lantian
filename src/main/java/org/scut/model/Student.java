package org.scut.model;

import org.springframework.stereotype.Component;

@Component
public class Student extends User{
    
	private String classIdList;
	private String schoolId;
	
 
	public String getClassIdList() {
		return classIdList;
	}
	public void setClassIdList(String classIdList) {
		this.classIdList = classIdList;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId){
		this.schoolId = schoolId;
	}
}