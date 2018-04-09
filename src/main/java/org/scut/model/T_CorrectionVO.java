package org.scut.model;

public class T_CorrectionVO {
	private int exercise_id;
	private String student_id;
	private String exercise_content;
	private String student_answer;
	//get
	public int getExercise_id() {
		return exercise_id;
	}
	public String student_answer() {
		return student_id;
	}
	public String getExercise_content() {
		return exercise_content;
	}
	public String getStudent_answer() {
		return student_answer;
	}
	//set
	public void setExercise_id(int exercise_id) {
		this.exercise_id=exercise_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id=student_id;
	}
	public void setExercise_content(String exercise_content) {
		this.exercise_content=exercise_content;
	}
	public void setStudent_answer(String student_answer) {
		this.student_answer=student_answer;
	}
}
