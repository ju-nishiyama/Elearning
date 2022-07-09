package com.zdrv.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdrv.dao.CourseUpdateDao;
import com.zdrv.domain.CourseUpdate;

@Service
public class CourseUpdateServiceImpl implements CourseUpdateService {
	
	
	
	@Autowired
	private CourseUpdateDao courseUpdateDao;

	@Override
	public void updateDate(int courseId, int typeId, String courseTitle, Date updateDate) {
		courseUpdateDao.updateDate(courseId,typeId,courseTitle,updateDate);
		
	}
	
	public List<CourseUpdate> selectAll(){
		return courseUpdateDao.selectAll();
	}

	

}
