package com.zdrv.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.CourseUpdate;

@Mapper
public interface CourseUpdateDao {
	
	void updateDate(int courseId,int typeId,String courseTitle,Date updateDate);
	
	List<CourseUpdate> selectAll();

}
