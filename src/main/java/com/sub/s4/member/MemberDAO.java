package com.sub.s4.member;

public interface MemberDAO {
	
	//LOGIN
	public MemberDTO getMemberLogin(MemberDTO memberDTO) throws Exception;
	
	//UPDATE
	public int setMemberUpdate(MemberDTO memberDTO) throws Exception;
	
	//DELETE
	public int setMemberDelete(MemberDTO memberDTO) throws Exception;
}
