package org.scut.model;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Class {
	private String classId;

	private String picPath;

    private Integer studentNumber;

    private String grade;

    private String className;

    private Date createTime;
    
    private String createTeacherId;
    
    private String schoolId;
    
    private int teacherNumber;
    
    
    public int getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(int teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCreateTeacherId() {
		return createTeacherId;
	}

	public void setCreateTeacherId(String createTeacherId) {
		this.createTeacherId = createTeacherId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

   
}