package com.zdrv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.Course;

@Mapper
public interface CourseDao {
	
	List<Course> selectAllCourse(int memberId);
	
	List<Course>selectAllById(int courseId);
	
	Course selectById(int id);
	
	
	void add(Course course);
	
	List <Course> selectRequiredCourse(String positionName);
	
	void deleteById(int id);
	
	void courseEdit(Course course);
	
	List<Course> courseHistoryById(int id);
	
	List<Course> courseHistory();
	
	List<Course> allCourse();

}
