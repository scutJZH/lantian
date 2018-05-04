package org.scut.model;

import org.springframework.stereotype.Component;



@Component
public class Teacher extends User{
	
	private String name;
	private String schoolId;
	private String schoolName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public void setSchoolName(String schoolName){
		this.schoolName = schoolName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	
	

	
	
	
	

}
