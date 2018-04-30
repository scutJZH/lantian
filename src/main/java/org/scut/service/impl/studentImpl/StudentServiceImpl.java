package org.scut.service.impl.studentImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import org.apache.coyote.Request;
import org.apache.ibatis.annotations.Param;
import org.scut.dao.IClass_paperDao;
import org.scut.dao.IPaperDao;
import org.scut.dao.IQuestionDao;
import org.scut.dao.IQuestion_paperDao;
import org.scut.dao.IScheduleDao;
import org.scut.dao.ISolutionDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.IStudent_paperDao;
import org.scut.dao.ITitleDao;
import org.scut.model.Question;
import org.scut.model.Student;
import org.scut.model.Title;
import org.scut.service.studentService.IStudentService;
import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;
import org.springframework.scheduling.config.ScheduledTasksBeanDefinitionParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		@Resource
		private ISolutionDao solutionDao;
		
	    

		@Override
		public Map<String, Object> getPaperList(String studentId,String submit) {
			
			Map<String, Object> responseBody = new HashMap<String, Object>();
			
		try {	
			List<Map<String, Object>>result = student_paperDao.getStudentPaperBySId(studentId,submit);
					
			String classId = (String) studentDao.getClassIDBySId(studentId).get("classId");
			
			List<Map<String, Object>>cpList = class_paperDao.getClassPaperByCId(classId);
		
								
			for(Map<String,Object> x:cpList) {
				
				for(Map<String,Object> y:result) {
					if(x.get("paperId").equals(y.get("paperId"))) {
						y.put("assignTime" , x.get("assighTime") );
						y.put("deadLine" , x.get("deadLine") );
						}				
				}
				
			}
	
			for(Map<String,Object> y:result) {
				org.scut.model.Paper ptemp = paperDao.selectByPrimaryKey((String) y.get("paperId"));
				y.put("paperName", ptemp.getPaperName());
				y.put("subjectId", ptemp.getSubjectId());	
				
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
				
				Map<String, Object> eachQuestion = questionDao.getQuestion((String) x.get("questionId"));
				optionsList.add(eachQuestion);

				}
			
			List<Map<String, Object>>titleList = new ArrayList<>();
			
			for(Map<String,Object> x:optionsList) {
				
				Map<String, Object> title = titleDao.getTitle((String) x.get("titleId"));
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
				int dayOfWeek = (int) map.get("dayOfWeek");
				Map<String, Object> dateMap = new HashMap<>();
				dateMap.put("year",year);
				dateMap.put("month",month);
				dateMap.put("day",day);
				dateMap.put("dayOfWeek",dayOfWeek);
				
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

		@Override
		public Map<String, Object> uploadSolutions(String studentId, String paperId, List<Map<String, Object>> solutionList,String reqLoacation) {
			Map<String, Object> responseBody = new HashMap<String, Object>();
             
			try {
					for (Map<String, Object> map : solutionList) {
						String questionId = (String) map.get("questionId");
						String solutionContent = (String) map.get("solutionContent");
						String img = (String) map.get("img");
						
						System.out.println(map);
						System.out.println();
						
						String picPath = null;
						
						System.out.println(img!=null && img.length()!=0);
						System.out.println();
						
						if( img!=null && img.length()!=0 )
						{
	                        picPath = UUID.randomUUID().toString();
							
							System.out.print(picPath);
							System.out.println();
                       							
							System.out.print(reqLoacation);
							System.out.println();

							String result  = Base64Analysis.analysisPic(picPath, reqLoacation+GlobalVar.solutionPicPath, img);
							
							System.out.print(result);
							System.out.println();
							
							}
						
						solutionDao.insertSolution(studentId,paperId,questionId,solutionContent,picPath);
						}
					student_paperDao.updateSubmit(studentId,paperId);

					}catch (Exception e) {System.out.println(e.getMessage());responseBody.put("status","-1");return responseBody;}
			responseBody.put("status", "1");
			return responseBody;
		}
		
}
