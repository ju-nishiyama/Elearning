package com.zdrv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.Member;

@Mapper
public interface MemberDao {
	
	List<Member> selectAllMembers();
	
	void add(Member member);
	
	Member selectById(int memberId);

	void memberDeleteById(int memberId);
	
	void memberEdit(Member member);
	
	List<Member> selectByName(String name);
	
	List<Member> selectByPosition(String positionName);
	
	List<Member> selectByStore(String workStore);
	
	List<Member> selectByName_Position_Store(String name,String positionName,String workStore);
	
	List<Member> selectByName_Position(String name,String positionName);
	
	List<Member> selectByName_Store(String name,String workStore);
	
	List<Member> selectByPosition_Store(String positionName,String workStore);
	
}
