package com.zdrv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdrv.dao.MemberDao;
import com.zdrv.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void add(Member member) {
		memberDao.add(member);
		
	}

	@Override
	public List<Member> selectAllMembers() {
		 return memberDao.selectAllMembers();
		
	}

	@Override
	public Member selectById(int memberId) {
		return  memberDao.selectById(memberId);
	}

	@Override
	public void memberDeleteById(int memberId) {
		memberDao.memberDeleteById(memberId);
		
	}

	@Override
	public void memberEdit(Member member) {
		memberDao.memberEdit(member);
		
	}

	@Override
	public List<Member> selectByName(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return memberDao.selectByName(name);
	}

	@Override
	public List<Member> selectByPosition(String positionName) {
		// TODO 自動生成されたメソッド・スタブ
		return memberDao.selectByPosition(positionName);
	}

	@Override
	public List<Member> selectByStore(String workStore) {
		// TODO 自動生成されたメソッド・スタブ
		return memberDao.selectByStore(workStore);
	}

	@Override
	public List<Member> selectByName_Position_Store(String name, String positionName, String workStore) {
		// TODO 自動生成されたメソッド・スタブ
		return memberDao.selectByName_Position_Store(name, positionName, workStore);
	}

	@Override
	public List<Member> selectByName_Position(String name, String positionName) {
		// TODO 自動生成されたメソッド・スタブ
		return memberDao.selectByName_Position(name, positionName);
	}

	@Override
	public List<Member> selectByName_Store(String name, String workStore) {
		// TODO 自動生成されたメソッド・スタブ
		return memberDao.selectByName_Store(name,workStore);
	}

	@Override
	public List<Member> selectByPosition_Store(String positionName, String workStore) {
		// TODO 自動生成されたメソッド・スタブ
		return memberDao.selectByPosition_Store(positionName, workStore);
	}

}
