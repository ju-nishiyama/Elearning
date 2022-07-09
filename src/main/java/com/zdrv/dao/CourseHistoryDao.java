package com.zdrv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.CourseHistory;

@Mapper
public interface CourseHistoryDao {
	
	void add(CourseHistory ch);
	
	List<CourseHistory> historyAll();
	
}
