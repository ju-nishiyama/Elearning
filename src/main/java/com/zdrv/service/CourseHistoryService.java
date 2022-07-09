package com.zdrv.service;

import java.util.List;

import com.zdrv.domain.CourseHistory;

public interface CourseHistoryService {

	void add(CourseHistory ch);
	
	List<CourseHistory> historyAll();
	
}
