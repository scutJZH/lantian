package org.scut.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface IClass_paperDao {
	//注意Object可能要改
	public ArrayList<HashMap<String,Object>> selectList(String teacher,String class_id);
}
