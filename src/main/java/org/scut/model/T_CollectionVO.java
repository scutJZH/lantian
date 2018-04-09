package org.scut.model;

public class T_CollectionVO {
	private int collection_id;
	private String teacher_id;

	//get
	public int getCollection_id() {
		return collection_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	//set
	public void setCollection_id(int collection_id) {
		this.collection_id=collection_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id=teacher_id;
	}
}
