package com.zdrv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.User;

@Mapper
public interface UserDao {
	
	
	List<User>administrators();
	

	void add(User user);
	
	User selectByUser(String loginId,String LoginPass);
	
	User selectById(int id);
	
	User selectByLoginId(String loginId);
	
	void userDeleteById(int id);
	
	void userEdit(User user) ;

	List<User> selectAllUsers();

}
