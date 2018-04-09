package org.scut.model;

public class T_RankVO {
	private int rank_id;
	private String student_id;
	private String student_name;
	private int homework_id;
	private int choice_score;
	private double total_score;
	private int rank;
	private int corrected_box;
	//get
	public int getRank_id() {
		return rank_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public String getStudent_name(){
		return student_name;
	}
	public int getHomework_id() {
		return homework_id;
	}
	public int getChoice_score() {
		return choice_score;
	}
	public double getTotal_score() {
		return total_score;
	}
	public int getRank() {
		return rank;
	}
	public int getCorrected_box() {
		return corrected_box;
	}
	//set
	public void setRank_id(int rank_id) {
		this.rank_id=rank_id;
	}
	public void setStudent_id(String student_id){
		this.student_id=student_id;
	}
	public void setStudent_name(String student_name) {
		this.student_name=student_name;
	}
	public void setHomework_id(int homework_id) {
		this. homework_id=homework_id;
	}
	public void setChoice_score(int choice_score) {
		this. choice_score=choice_score;
	}
	public void setTotal_score(double total_score) {
		this. total_score=total_score;
	}
	public void setRank(int rank) {
		this. rank=rank;
	}
	public void setCorrected_box(int corrected_box) {
		this. corrected_box=corrected_box;
	}
}
