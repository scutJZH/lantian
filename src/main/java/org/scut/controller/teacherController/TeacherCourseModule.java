package org.scut.controller.teacherController;
import java.util.Date;
import java.util.*;

import javax.annotation.Resource;

import org.scut.model.Answer;
import org.scut.model.Post;
import org.scut.service.IAnserService;
import org.scut.service.IPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TeacherCourseModule {
@RequestMapping("/TeacherCourseModule.do")
@ResponseBody
public Map<String,Object> test(@RequestParam(value="teacher_id",required=false)String teacher_id,@RequestParam(value="class_id",required=false)String class_id) {
	String teacherid=teacher_id;
	String classid=class_id;
	Map<String,Object> m=new HashMap<String,Object>();
	m.put("status", "123232");
	m.put("data", "213123");
	return m;
}
}
