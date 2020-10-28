package com.sub.s4.member.memberFile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sub.s4.MyTestCase;

public class MemberFileDAOTest extends MyTestCase{

	@Autowired
	private MemberFileDAO memberFileDAO;
	
	@Test(expected = RuntimeException.class)
	public void setInsertTest() throws Exception{
		MemberFileDTO memberFileDTO = new MemberFileDTO();
		memberFileDTO.setId("Id4");
		memberFileDTO.setFileName("fileName");
		memberFileDTO.setOriName("oriName");
		int result = memberFileDAO.setInsert(memberFileDTO);
		
		assertEquals(1, result);
		
	}
}
