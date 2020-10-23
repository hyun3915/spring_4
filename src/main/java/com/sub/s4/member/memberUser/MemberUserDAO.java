package com.sub.s4.member.memberUser;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sub.s4.member.MemberDAO;
import com.sub.s4.member.MemberDTO;

@Repository
public class MemberUserDAO implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String namespace="com.sub.s4.member.memberUser.MemberUserDAO.";

	@Override
	public int setMemberUpdate(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+"setMemberUpdate", memberDTO);
	}
	
	@Override
	public int setMemberDelete(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(namespace+"setMemberDelete", memberDTO);
	}
	
	@Override
	public MemberDTO getMemberLogin(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		//id는 중복금지이기 때문에 1개만 출력
		return sqlSession.selectOne(namespace+"getMemberLogin", memberDTO);
	}

}
