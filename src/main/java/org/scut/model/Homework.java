package org.scut.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Homework {

	private String homeworkId;
	private String title;
	private Date createTime;
	private String semester;
	private String subjectId;
	private String max_score;
	private String choiceQuestion;
	
	
	public String getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSemester() { 
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getMax_score() {
		return max_score;
	}
	public void setMax_score(String max_score) {
		this.max_score = max_score;
	}
	public String getChoiceQuestion() {
		return choiceQuestion;
	}
	public void setChoiceQuestion(String choiceQuestion) {
		this.choiceQuestion = choiceQuestion;
	}
	public String getCompletion() {
		return completion;
	}
	public void setCompletion(String completion) {
		this.completion = completion;
	}
	public String getSubjectiveProblem() {
		return subjectiveProblem;
	}
	public void setSubjectiveProblem(String subjectiveProblem) {
		this.subjectiveProblem = subjectiveProblem;
	}
	private String completion;
	private String subjectiveProblem;
	
	
	

}
