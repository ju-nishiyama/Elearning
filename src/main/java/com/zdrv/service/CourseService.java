package com.zdrv.service;

import java.util.List;

import com.zdrv.domain.Course;

public interface CourseService {
	
	List<Course> selectAllCourse(int memberId);
	
	List<Course>selectAllById(int courseId);
	
	void add(Course course);
	
	List <Course> selectRequiredCourse(String positionName);
	
	Course selectById(int id);

	void deleteById(int id);
	
	void courseEdit(Course course);
	
	List<Course> courseHistoryById(int id);
	
	List<Course> courseHistory();
	
	List<Course> allCourse();
	
}
