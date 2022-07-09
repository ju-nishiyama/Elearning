package com.zdrv.service;

import java.util.Date;
import java.util.List;

import com.zdrv.domain.CourseUpdate;

public interface CourseUpdateService {
	
	void updateDate(int courseId,int typeId,String courseTitle,Date updateDate);
	
	List<CourseUpdate> selectAll();

}
