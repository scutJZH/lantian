package org.scut.model;

public class T_Selected_ExerciseVO {
	private int selected_exercise;
	private int homework_id;

	//get
	public int getSelected_exercise() {
		return selected_exercise;
	}
	public int getHomework_id() {
		return homework_id;
	}
	//set
	public void setSelected_exercise(int selected_exercise) {
		this.selected_exercise=selected_exercise;
	}
	public void setHomework_id(int homework_id) {
		this.homework_id=homework_id;
	}
}
