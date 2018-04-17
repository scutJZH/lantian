package org.scut.model;

import org.springframework.stereotype.Component;



@Component
public class Teacher extends User{
	
	private String name;
	private String schoolName;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	

	
	
	
	

}
