package com.zdrv.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.Director;

@Mapper
public interface DirectorDao {
	
	void add(Director director);

}
