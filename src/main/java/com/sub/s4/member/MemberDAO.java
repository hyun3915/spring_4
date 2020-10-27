package com.sub.s4.member;

public interface MemberDAO {
	
	//idCheck
	public MemberDTO getMemberIdCheck(MemberDTO memberDTO) throws Exception;
	
	//LOGIN
	public MemberDTO getMemberLogin(MemberDTO memberDTO) throws Exception;
	
	//UPDATE
	public int setMemberUpdate(MemberDTO memberDTO) throws Exception;
	
	//DELETE
	public int setMemberDelete(MemberDTO memberDTO) throws Exception;
	
	//JOIN
	public int setMemberJoin(MemberDTO memberDTO) throws Exception;
}
