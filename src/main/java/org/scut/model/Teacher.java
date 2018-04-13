package org.scut.model;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * @author jzh
 * @category老师实体类
 *
 */

@Component
public class Teacher extends User{
	
	private String name;
	private String school_name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	

	
	
	
	

}
