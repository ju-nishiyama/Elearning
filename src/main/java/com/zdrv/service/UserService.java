package com.zdrv.service;

import java.util.List;

import com.zdrv.domain.User;

public interface UserService {
	
	void add(User user);
	
	List<User> selectAllUsers();
	
	User selectByUser(String loginPass,String loginId);
	
	void userDeleteById(int id);
	
	void userEdit(User user);
	
	User selectById(int id);
	
	User selectByLoginId(String loginId);

}
