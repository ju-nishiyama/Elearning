package com.zdrv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdrv.dao.CourseHistoryDao;
import com.zdrv.domain.CourseHistory;

@Service
public class CourseHistoryServiceImpl implements CourseHistoryService{
	
	@Autowired 
	private CourseHistoryDao courseHistoryDao;

	@Override
	public void add(CourseHistory ch) {
		courseHistoryDao.add(ch);
		
	}

	@Override
	public List<CourseHistory> historyAll() {
		// TODO 自動生成されたメソッド・スタブ
		return courseHistoryDao.historyAll();
	}


}
