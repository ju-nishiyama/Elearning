package com.zdrv.service;

import com.zdrv.domain.History;

public interface HistoryService {
	
	void add(Integer membersId,String history);
	
	History selectByMembersId(Integer membersId);

}
