package org.scut.model;

import java.util.Date;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jzh
 * @category家长实体类
 *
 */
@Component
public class Parent {
	
	private String id;
	private String phone;
	private String password;
	private String nickname;
	private Date birthday;
	private String sex;
	private String pic_path;
	private Date createTime;
	private String token;
	
	
	

	public Parent() {

	}


	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
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




	public Date getCreateTime() {
		return createTime;
	}




	public void setCreateTime(Date create_time) {
		this.createTime = create_time;
	}




	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}
	
	


	

	
	
	
	
}
