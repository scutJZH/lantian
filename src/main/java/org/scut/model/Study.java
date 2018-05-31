package org.scut.model;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

@Component
public class Study{
	private String studyId;
	public String getStudyId() {
		return studyId;
	}
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	private String paperId;
	private String classId;
	private int aveTime;
	private int aveScore;
	private String assignTeacherId;
	private Date deadLine;
	private int submitNumber;
	private Date assignTime;
	private String paperType;
	private int examTime;
	private String rank;
	public String getPaperId() {
		return this.paperId;
	}
	public String getClassId() {
		return this.classId;
	}
	public int getAveTime() {
		return this.aveTime;
	}
	public int getAveScore() {
		return this.aveScore;
	}
	public String getAssignTeacherId() {
		return this.assignTeacherId;
	}
	public Date getDeadLine() {
		return this.deadLine;
	}
	public int getSubmitNumber() {
		return this.submitNumber;
	}
	public Date getAssignTime() {
		return this.assignTime;
	}
	public String getPaperType() {
		return this.paperType;
	}
	public int getExamTime() {
		return this.examTime;
	}
	public String getRank(){
		return this.rank;
	}
	//set
	public void setPaperId(String paperId) {
		this.paperId=paperId;
	}
	public void setClassId(String classId) {
		this.classId=classId;
	}
	public void setAveTime(int aveTime) {
		this.aveTime=aveTime;
	}
	public void setAveScore(int aveScore) {
		this.aveScore=aveScore;
	}
	public void setAssignTeacherId(String assignTeacherId) {
		this.assignTeacherId=assignTeacherId;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine=deadLine;
	}
	public void setSubmitNumber(int submitNumber) {
		this.submitNumber=submitNumber;
	}
	public void setAssignTime(Date assignTime) {
		this.assignTime=assignTime;
	}
	public void setPaperType(String paperType) {
		this.paperType=paperType;
	}
	public void setExamTime(int examTime) {
		this.examTime=examTime;
	}
	public void setRank(String rank) {
		this.rank=rank;
	}
}