package com.zdrv.service;

import java.util.List;

import com.zdrv.domain.Store;

public interface StoreService {
	
	List<Store> selectAll();
	
	void addStore(Store store);
	
	Store selectById(int storeId);
	
	void update(Store store);
	
	void delete(Integer storeId);

}
