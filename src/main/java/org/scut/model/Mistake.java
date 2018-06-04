package org.scut.model;

import java.util.Date;

import org.springframework.stereotype.Component;



@Component
public class Mistake  {
		private String studentId;
		private String questionId;
		private String titleContent;
		private Date  createTime;
		private Date changeTime;
		private Integer questionType;
		private String picPath;
		private	String optionA;
		private	String optionB;
		private	String optionC;
		private	String optionD;
		private String lastAnswer;
		private String answer;
		private String note;
		
		public String getQuestionId() {
			return questionId;
		}
		public void setQuestionId(String questionId) {
			this.questionId = questionId;
		}
		public String getTitleContent() {
			return titleContent;
		}
		public void setTitleContent(String titleContent) {
			this.titleContent = titleContent;
		}
		public Integer getQuestionType() {
			return questionType;
		}
		public void setQuestionType(Integer questionType) {
			this.questionType = questionType;
		}
		public String getoptionA() {
			return optionA;
		}
		public void setoptionA(String optionA) {
			this.optionA = optionA;
		}
		public String getPicPath() {
			return picPath;
		}
		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}
		public String getoptionB() {
			return optionB;
		}
		public void setoptionB(String optionB) {
			this.optionB = optionB;
		}
		public String getoptionC() {
			return optionC;
		}
		public void setoptionC(String optionC) {
			this.optionC = optionC;
		}
		public String getoptionD() {
			return optionD;
		}
		public void setoptionD(String optionD) {
			this.optionD = optionD;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getLastAnswer() {
			return lastAnswer;
		}
		public void setLastAnswer(String lastAnswer) {
			this.lastAnswer = lastAnswer;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public Date getChangeTime() {
			return changeTime;
		}
		public void setChangeTime(Date changeTime) {
			this.changeTime = changeTime;
		}
		public String getStudentId() {
			return studentId;
		}
		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		

}
