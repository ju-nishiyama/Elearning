package com.zdrv.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.History;

@Mapper
public interface HistoryDao {
	
	void add(Integer membersId,String history);
	
	History selectByMembersId(Integer membersId);

}
