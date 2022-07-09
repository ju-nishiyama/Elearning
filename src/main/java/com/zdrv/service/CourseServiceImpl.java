package com.zdrv.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdrv.dao.CourseDao;
import com.zdrv.dao.CourseUpdateDao;
import com.zdrv.domain.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private CourseUpdateDao courseUpdateDao;
	
	

	@Override
	public List<Course> selectAllCourse(int memberId) {
		return courseDao.selectAllCourse(memberId);
	}


	@Override
	public void add(Course course) {
		courseDao.add(course);
		courseUpdateDao.updateDate(course.getCourseId(),1,course.getTitle(),new Date());
	}


	@Override
	public List<Course> selectRequiredCourse(String positionName) {
		return courseDao.selectRequiredCourse(positionName);
	}


	@Override
	public Course selectById(int id) {
		return courseDao.selectById(id);
	}


	@Override
	public void deleteById(int id) {
		courseDao.deleteById(id);
		
	}


	@Override
	public void courseEdit(Course course) {
		courseDao.courseEdit(course);
		courseUpdateDao.updateDate(course.getCourseId(),2,course.getTitle(),new Date());
	}


	@Override
	public List<Course> selectAllById(int courseId) {
		return courseDao.selectAllById(courseId);
	}


	@Override
	public List<Course> courseHistoryById(int id) {
		return courseDao.courseHistoryById(id);
	}


	@Override
	public List<Course> courseHistory() {
		return courseDao.courseHistory();
	}
	
	@Override
	public List<Course> allCourse(){
		return courseDao.allCourse();
	}




}
