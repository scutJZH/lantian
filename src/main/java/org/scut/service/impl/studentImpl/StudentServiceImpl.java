package org.scut.service.impl.studentImpl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;

import org.scut.dao.IClassDao;
import org.scut.dao.IPaperDao;
import org.scut.dao.IQuestionDao;
import org.scut.dao.IQuestion_paperDao;
import org.scut.dao.IScheduleDao;
import org.scut.dao.ISolutionDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.IStudent_studyDao;
import org.scut.dao.IStudyDao;
import org.scut.dao.ITitleDao;
import org.scut.model.Class;
import org.scut.model.Question;
import org.scut.model.Student;
import org.scut.model.Title;
import org.scut.service.studentService.IStudentService;
import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;

import org.springframework.stereotype.Service;


@Service("studentService")
public class StudentServiceImpl implements IStudentService{
		
		@Resource
		private IStudent_studyDao student_studyDao;
		@Resource
		private IStudentDao studentDao;
		@Resource
		private IStudyDao studyDao;
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
		@Resource
		private IClassDao classDao;
		
	    

		@Override
		public Map<String, Object> getPaperList(String studentId,String submit) {
			
			Map<String, Object> responseBody = new HashMap<String, Object>();
			
		try {	
			List<Map<String, Object>>result = student_studyDao.getStudentStudyBySId(studentId,submit);
																
			for(Map<String,Object> y:result) {	
				
				Map<String,Object> x  = studyDao.getStudyById((String) y.get("studyId"));
				y.put("assignTime" , x.get("assighTime") );
				y.put("deadLine" , x.get("deadLine") );
				y.put("paperId" , x.get("paperId") );
				y.put("paperType" , x.get("paperType") );
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
			
		    System.out.println(e);
		    System.out.println();
		    
			responseBody.put("status", "-2");
			return responseBody ;
		}
		
		}
		
		@Override
		public Map<String, Object> getPaperQuestions(String paperId) {
			
			Map<String, Object> responseBody = new HashMap<String, Object>();
			
			Map<String,Object> result = new HashMap<>();
			
			try {
			List<Map<String, Object>>questionIdList = question_paperDao.getQuestions(paperId);
						
			List<Question>optionsList = new ArrayList<>();
										
			for(Map<String,Object> x:questionIdList) {
				
				Question eachQuestion = questionDao.selectByPrimaryKey((String) x.get("questionId"));
				optionsList.add(eachQuestion);

				}
			
			List<Map<String, Object>>titleList = new ArrayList<>();
			
			for(Question x:optionsList) {
				
				Title title = titleDao.selectByPrimaryKey(x.getTitleId());
				Map<String,Object> titleMap = new HashMap<>();
				titleMap.put("titleId", title.getTitleId());
				
				if(title.getPicPath()==null||title.getPicPath().length()==0) {titleMap.put("picPath", title.getPicPath());}else {titleMap.put("picPath", GlobalVar.titlePicPath+title.getPicPath());}
				
				titleMap.put("titleContent", title.getTitleContent());
								
				if(titleList.contains(titleMap)!=true){
					titleList.add(titleMap);
					}
			}
			

			result.put("titleList",titleList);
			

			if (optionsList.isEmpty()) {
				responseBody.put("status", "-1");
			}else {
				responseBody.put("status", "1");
				

				result.put("optionsList", optionsList);
				responseBody.put("result", result);
				}
			
			return responseBody;
		}catch (Exception e) {
			System.out.println(e);
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
		public Map<String, Object> uploadSolutions(String studentId, String paperId, List<Map<String, Object>> solutionList) {
			Map<String, Object> responseBody = new HashMap<String, Object>();
             
			try {
				    String picLocation = this.getClass().getClassLoader().getResource("../../").getPath();
					for (Map<String, Object> map : solutionList) {
						String questionId = (String) map.get("questionId");
						String solutionContent = (String) map.get("solutionContent");
						String img = (String) map.get("img");
						String isRight = (String) map.get("isRight");
						
						String picPath = null;						
						
						if( img!=null && img.length()!=0 )
						{
	                        picPath = UUID.randomUUID().toString();

							Base64Analysis.analysisPic(picPath, picLocation+GlobalVar.solutionPicPath, img);
							
							}
						
						solutionDao.insertSolution(studentId,paperId,questionId,solutionContent,picPath,isRight);
						}
					student_studyDao.updateSubmit(studentId,paperId);

					}catch (Exception e) {System.out.println(e.getMessage());responseBody.put("status","-1");return responseBody;}
			responseBody.put("status", "1");
			return responseBody;
		}
		
}
