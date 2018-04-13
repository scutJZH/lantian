package org.scut.model;

import java.util.Date;

import org.springframework.stereotype.Component;
/**
 * 
 * @author jzh
 * @category学生的实体类
 *
 */

@Component
public class Student extends User{
	

	private String school_name;
	private String class_id;
	
	
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	
	
	
	
}
