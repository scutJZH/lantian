package org.scut.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IClass_paperDao {
	//注意Object可能要改
	public ArrayList<HashMap<String,Object>> selectList(String teacherId,String classId);
	public int deleteList(List<String> paperId);
}
