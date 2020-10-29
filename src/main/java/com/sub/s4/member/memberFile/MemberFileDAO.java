package com.sub.s4.member.memberFile;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sub.s4.member.MemberDAO;
import com.sub.s4.member.MemberDTO;

@Repository
public class MemberFileDAO{
	
	@Autowired
	private SqlSession sqlSession;
	private final String namespace="com.sub.s4.member.memberFile.MemberFileDAO.";

	//setInsert
	public int setInsert(MemberFileDTO memberFileDTO) throws Exception{
		return sqlSession.insert(namespace+"setInsert", memberFileDTO);
	}
	
	public MemberFileDTO getOne(MemberDTO memberDTO) throws Exception{
		return sqlSession.selectOne(namespace+"getOne", memberDTO);
	}

}
