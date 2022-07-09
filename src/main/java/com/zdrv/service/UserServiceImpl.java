package com.zdrv.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdrv.dao.MemberDao;
import com.zdrv.dao.UserDao;
import com.zdrv.domain.Member;
import com.zdrv.domain.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private MemberDao memberDao;
	
	
	@Override
	public void add(User user) {
		String pass=user.getLoginPass();
		String hashed=BCrypt.hashpw(pass, BCrypt.gensalt());
		user.setLoginPass(hashed);
		userDao.add(user);
		
		Member member=user.getMember();
		member.setMemberId(user.getId());
		memberDao.add(member);
			
	}

	@Override
	public List<User> selectAllUsers() {
		
		
		return userDao.selectAllUsers();
		
	}

	@Override
	public User selectByUser(String loginId,String loginPass) {
		User user =userDao.selectByLoginId(loginId);
		System.out.println(user);
		
		if(user == null) {
			return null;
		}
		
		if(!BCrypt.checkpw(loginPass,user.getLoginPass())) {
			return null;
		}
		
		
		return user;
		
		
	}
	
	

	@Override
	public void userDeleteById(int id) {
		memberDao.memberDeleteById(id);
		userDao.userDeleteById(id);
		
	}

	@Override
	public void userEdit(User user) {
		String pass=user.getLoginPass();
		String hashed=BCrypt.hashpw(pass, BCrypt.gensalt());
		user.setLoginPass(hashed);
		Member member = user.getMember();
		member.setMemberId(user.getId());
		System.out.println(user);
		System.out.println(member);
		userDao.userEdit(user);
		memberDao.memberEdit(member);
		
	}

	@Override
	public User selectById(int id) {
		return userDao.selectById(id);
	}

	@Override
	public User selectByLoginId(String loginId) {
		
		return null;
	}

}
