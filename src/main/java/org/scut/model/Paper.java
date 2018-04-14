package org.scut.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Paper {
    private String paperId;

    private String paperName;

    private Integer grade;

    private String subjectId;

    private String createTeacherId;

    private Date createTime;

    private Integer maxScore;

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getCreateTeacherId() {
		return createTeacherId;
	}

	public void setCreateTeacherId(String createTeacherId) {
		this.createTeacherId = createTeacherId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

    
}