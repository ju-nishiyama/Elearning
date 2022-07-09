package com.zdrv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdrv.dao.StoreDao;
import com.zdrv.domain.Store;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreDao storeDao;

	@Override
	public void addStore(Store store) {
		storeDao.addStore(store);		
	}

	@Override
	public List<Store> selectAll() {
		return storeDao.selectAll();
	}

	@Override
	public Store selectById(int storeId) {
		return storeDao.selectById(storeId);
	}

	@Override
	public void update(Store store) {
	 storeDao.update(store);
	}

	@Override
	public void delete(Integer storeId) {
		storeDao.delete(storeId);
		
	}
	
	

}
