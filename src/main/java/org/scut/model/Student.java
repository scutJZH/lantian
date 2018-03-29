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
public class Student {
	
	private String id;
	private String phone;
	private String password;
	private String nickname;
	private Date birthday;
	private String sex;
	private String pic_path;
	private String school;
	private Date createTime;
	private String token;
	
	
	public Student() {
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id2) {
		this.id = id2;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getPic_path() {
		return pic_path;
	}


	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public Date getCreate_time() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	
	

}
