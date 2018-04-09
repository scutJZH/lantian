package org.scut.controller.teacherController;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.scut.model.*;
import org.scut.service.teacherService.*;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
@Controller
@RequestMapping("/teacher")//改成这个可以直接该类下全部方法在调用前都会调用intercepter包下的拦截方法处理token。
public class Teacher_module_course {
	@Resource
	private IT_Homework_Details_Service t_Homework_Details_Service;
	@Resource
	private IT_Class_Service t_Class_Service;
	@Resource
	private IT_Optional_Subject_Service t_Optional_Subject_Service;
	@Resource
	private IT_Optional_Exercise_Service t_Optional_Exercise_Service;
	@Resource
	private IT_Selected_Exercise_Service t_Selected_Exercise_Service;
	@Resource
	private IT_Ppt_Service t_Ppt_Service;
	@Resource
	private IT_Collection_Service t_Collection_Service;
	@Resource
	private IT_Rank_Service t_Rank_Service;
	@Resource
	private IT_Student_Details_Service t_Student_Details_Service;
	
	public void get_Homework_List(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*下面的teacher_id类型可能有变动，需注意*/
		String teacher_id=request.getParameter("teacher_id");
		ArrayList<T_Homework_DetailsVO> t_Homework_DetailsVO=this.t_Homework_Details_Service.get_Homework_List(teacher_id);
		ObjectMapper mapper=new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(t_Homework_DetailsVO));
		response.getWriter().close();
	}
	@RequestMapping("/delete_Homework_List.do")
	public @ResponseBody boolean delete_Homework_List(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*下面的teacher_id类型可能有变动，需注意*/
		String teacher_id=request.getParameter("teacher_id");
		boolean tag=this.t_Homework_Details_Service.delete_Homework_List(teacher_id);
		return tag;
	}	
	@RequestMapping("/get_Assignments.do")
	public @ResponseBody HashMap<String,Integer> get_Assignments(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*下面的teacher_id类型可能有变动，需注意*/
		String teacher_id=request.getParameter("teacher_id");
		//下面的还需加上可选科目，可选题目。
		ArrayList<T_ClassVO> t_ClassVO=this.t_Class_Service.get_Class(teacher_id);
		ArrayList<T_Optional_SubjectVO> t_Optional_SubjectVO=this.t_Optional_Subject_Service.get_Optional_Subject(teacher_id);
		ArrayList<T_Optional_ExerciseVO> t_Optional_ExerciseVO=this.t_Optional_Exercise_Service.get_Optional_Exercise(hashmap_Subject);
//需要更改
		hashmap.putAll(hashmap_Subject);
		hashmap.putAll(hashmap_Exercise);
		return hashmap;
	}
	public @ResponseBody boolean assign_Homework(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*下面的teacher_id类型可能有变动，需注意*/
		String teacher_id=request.getParameter("teacher_id");
		int selected_class=Integer.parseInt(request.getParameter("selected_class"));
		int selected_subject=Integer.parseInt(request.getParameter("selected_subject"));
		boolean tag1=this.t_Homework_Details_Service.insert(teacher_id, selected_class, selected_subject);
		
		/*下面这句是否输入t_Selected_ExerciseVO待考量*/
		ArrayList<T_Selected_ExerciseVO> arr=new ArrayList<T_Selected_ExerciseVO>();
		for(int i=0;i<=arr.size();i++) {
			//这个J就是请求中的selected_exercise
			int j=Integer.parseInt(request.getParameter("selected_exercise"));
			//这个homework_id还需想清楚是什么生成的,初步是使用额外加入的方法来获取最新插入数据（上一句刚插入）的homework_id
			int homework_id=t_Homework_Details_Service.getLatestHomeworkId();
			arr.get(i).setHomework_id(homework_id);
			arr.get(i).setSelected_exercise(j);
		}
		boolean tag2=this.t_Selected_Exercise_Service.insert(arr);
		//两个表都插入成功，说明布置作业成功。
		return tag1&&tag2;
	}
	
	/*module5获取备课资料列表功能*/
	public @ResponseBody ArrayList<Integer> getPptList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*下面的teacher_id类型可能有变动，需注意*/
		String teacher_id=request.getParameter("teacher_id");
		return this.t_Ppt_Service.getPptList(teacher_id); 
	}
	
	/*module6获取我的收藏列表功能*/
	public @ResponseBody ArrayList<Integer> getCollectionList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*下面的teacher_id类型可能有变动，需注意*/
		String teacher_id=request.getParameter("teacher_id");
		return this.t_Collection_Service.getCollectionList(teacher_id); 
	}
	
	/*module9获取统计排名列表,和this.get_Homework_List是同一个东西*/
	public void getRankList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.get_Homework_List(request, response);
	}
	
	/*module10获取统计排名详情*/
	public @ResponseBody ArrayList<T_RankVO> getRankDetails(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*下面的类型可能有变动，需注意*/
		int homework_id=Integer.parseInt(request.getParameter("homework_id"));
		return this.t_Rank_Service.getRankDetails(homework_id);
	}
	
	/***下面开始为班级模块***/
	/*1.获取班级列表*/
	public @ResponseBody  ArrayList<T_Student_DetailsVO> getClassList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String teacher_id=request.getParameter("teacher_id");
		return this.t_Student_Details_Service.getClassList(teacher_id);
	}
	/*2.添加班级*/
	public @ResponseBody boolean addClass(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String teacher_id=request.getParameter("teacher_id");
		int class_id=Integer.parseInt(request.getParameter("class_id"));
		int grade=Integer.parseInt(request.getParameter("grade"));
		int class_number=Integer.parseInt(request.getParameter("class_nunmber"));
		return this.t_Class_Service.addClass(teacher_id, class_id, grade, class_number);
	}
	
}
