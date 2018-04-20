package org.scut.service.impl.studentImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.swing.Spring;

import org.apache.ibatis.annotations.Param;
import org.scut.dao.IClass_paperDao;
import org.scut.dao.IPaperDao;
import org.scut.dao.IQuestionDao;
import org.scut.dao.IQuestion_paperDao;
import org.scut.dao.IScheduleDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.IStudent_paperDao;
import org.scut.dao.ITitleDao;
import org.scut.model.Question;
import org.scut.model.Student;
import org.scut.model.Title;
import org.scut.service.studentService.IStudentService;
import org.springframework.scheduling.config.ScheduledTasksBeanDefinitionParser;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements IStudentService{
		
		@Resource
		private IStudent_paperDao student_paperDao;
		@Resource
		private IStudentDao studentDao;
		@Resource
		private IClass_paperDao class_paperDao;
		@Resource
		private IPaperDao paperDao;
		@Resource
		private IQuestion_paperDao question_paperDao;
		@Resource
		private ITitleDao titleDao;
		@Resource
		private IQuestionDao questionDao;
		@Resource
		private IScheduleDao scheduleDao;
		
	    

		@Override
		public Map<String, Object> getPaperList(String studentId,String submit) {
			
			Map<String, Object> responseBody = new HashMap<String, Object>();
			
		try {	
			List<Map<String, Object>>result = student_paperDao.getStudentPaperBySId(studentId,submit);
					
			String classId = (String) studentDao.getClassIDBySId(studentId).get("class_id");
			
			List<Map<String, Object>>cpList = class_paperDao.getClassPaperByCId(classId);
		
								
			for(Map<String,Object> x:cpList) {
				
				for(Map<String,Object> y:result) {
					if(x.get("paper_id").equals(y.get("paper_id"))) {
						y.put("assign_time" , x.get("assigh_time") );
						y.put("dead_line" , x.get("dead_line") );
						}				
				}
				
			}
	
			for(Map<String,Object> y:result) {
				org.scut.model.Paper ptemp = paperDao.selectByPrimaryKey((String) y.get("paper_id"));
				y.put("paper_name", ptemp.getPaperName());
				y.put("subject_id", ptemp.getSubjectId());	
				
			}
			
			if(result == null || result.isEmpty()) {
			    responseBody.put("status", "-1");
			}
			else {
				responseBody.put("status", "1");
				responseBody.put("result", result);
			}
			return responseBody ;
			
		}catch (Exception e) {
			responseBody.put("status", "-2");
			return responseBody ;
		}
		
		}
		
		@Override
		public Map<String, Object> getPaperQuestions(String paperId) {
			
			Map<String, Object> responseBody = new HashMap<String, Object>();
			
			try {
			List<Map<String, Object>>questionIdList = question_paperDao.getQuestionIds(paperId);
						
			List<Map<String, Object>>optionsList = new ArrayList<>();
										
			for(Map<String,Object> x:questionIdList) {
				
				Map<String, Object> eachQuestion = questionDao.getQuestion((String) x.get("question_id"));
				optionsList.add(eachQuestion);

				}
			
			List<Map<String, Object>>titleList = new ArrayList<>();
			
			for(Map<String,Object> x:optionsList) {
				
				Map<String, Object> title = titleDao.getTitle((String) x.get("title_id"));
				if(titleList.contains(title)!=true){
					titleList.add(title);
					}
			}
			

			responseBody.put("titleList",titleList);
			

			if (optionsList.isEmpty()) {
				responseBody.put("status", "-1");
			}else {
				responseBody.put("status", "1");
				responseBody.put("result", optionsList);
				}
			
			return responseBody;
		}catch (Exception e) {
			responseBody.put("status", "-1");
			return responseBody;
		}
		}

		@Override
		public Map<String, Object> getSchedules(String studentId) {
			
			Map<String, Object> responseBody = new HashMap<String, Object>();
			
			try {
			
			List<Map<String, Object>> sourceDataResult = new ArrayList<>();
			
			List<Map<String, Object>> result = new ArrayList<>();
						
			sourceDataResult = scheduleDao.getSchedules(studentId);			
			
			for (Map<String, Object> map : sourceDataResult) {
				
				int year = (int) map.get("year");
				int month = (int) map.get("month");
				int day = (int) map.get("day");
				int dayOfWeek = (int) map.get("day_of_week");
				Map<String, Object> dateMap = new HashMap<>();
				dateMap.put("year",year);
				dateMap.put("month",month);
				dateMap.put("day",day);
				dateMap.put("day_of_week",dayOfWeek);
				
				if(!result.contains(dateMap)) {
					result.add(dateMap);
				}
			}
				
			for (Map<String, Object> map2 : result) {
				List<Map<String, Object>> ScheduleDailyList = new ArrayList<>();
				ScheduleDailyList = scheduleDao.getSchedulesByDate(studentId,(int)map2.get("year"),(int)map2.get("month"),(int)map2.get("day"));
				map2.put("ScheduleDailyList",ScheduleDailyList);
			}
					
			
			if (result.isEmpty()) {
				responseBody.put("status", "-1");
			}
			else {
				responseBody.put("status", "1");
			}
		
			responseBody.put("result", result);
			
			return responseBody;
		}catch (Exception e) {
			responseBody.put("status", "-1");
			return responseBody;
		}
		}

		@Override
		public Map<String, Object> addSchedule(Map<String, Object> m)throws IOException {
			
			Map<String, Object> responseBody = new HashMap<String, Object>();
			
			try {
				
				String studentId = (String) m.get("studentId");
				int year = Integer.parseInt( (String) m.get("year"));
				int month = Integer.parseInt( (String) m.get("month"));
				int day = Integer.parseInt( (String) m.get("day"));
				String startTime = (String) m.get("startTime");
				String endTime = (String) m.get("endTime");
				String scheduleContext = (String) m.get("scheduleContext");				
				String scheduleId = UUID.randomUUID().toString();

				Calendar calendar = Calendar.getInstance();
				calendar.set(year,month,day);
				int dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 4) % 7;
				
				scheduleDao.addSchedule(scheduleId,studentId,startTime,endTime,scheduleContext,year,month,day,dayOfWeek);

				responseBody.put("status", "1");
				return responseBody;
				
			} catch (Exception e) {
				responseBody.put("status", "-2");
				return responseBody;
			}
		}
		
		

}
