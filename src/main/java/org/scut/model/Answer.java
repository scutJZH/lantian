package org.scut.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Answer {
    private String answerId;

    private String studentId;

    private String postId;

    private String answerContent;

    private Date answerTime;

    private Integer likes;

    private String atStudentId;

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public String getAtStudentId() {
		return atStudentId;
	}

	public void setAtStudentId(String atStudentId) {
		this.atStudentId = atStudentId;
	}

    
}