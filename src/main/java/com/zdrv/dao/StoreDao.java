package com.zdrv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.Store;

@Mapper
public interface StoreDao {
	
	List<Store> selectAll();
	
	void addStore(Store store);
	
	Store selectById(int storeId);
	
	void update(Store store);

	void delete(Integer storeId);
	
}
