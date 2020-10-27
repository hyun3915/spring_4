package com.sub.s4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sub.s4.member.MemberDTO;
import com.sub.s4.member.memberUser.MemberUserDAO;

public class MemberUserDAOTest extends MyTestCase{
	
	@Autowired
	private MemberUserDAO memberUserDAO;
	
	@Test
	public void getMemberIdCheckTest() throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("Id1");
		memberDTO = memberUserDAO.getMemberIdCheck(memberDTO);
		assertNull(memberDTO);
	}
	
	//@Test
	public void getMemberLoginTest() throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("Id1");
		memberDTO.setPw("Pw1");
		memberDTO = memberUserDAO.getMemberLogin(memberDTO);

		assertNotNull(memberDTO);
		
	}

}
