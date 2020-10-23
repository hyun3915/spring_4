package com.sub.s4.member.memberUser;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sub.s4.member.MemberDTO;

@Controller
@RequestMapping("/member/**")
public class MemberUserController {
	
	@Autowired
	private MemberUserService memberUserService;
	
	//getMemberLogin
	@GetMapping(value = "memberLogin")
	public ModelAndView getMemberLogin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberLogin");
		return mv;
	}
	
	@PostMapping(value = "memberLogin")
	public ModelAndView getMemberLogin(MemberDTO memberDTO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberDTO = memberUserService.getMemberLogin(memberDTO);
		
		if(memberDTO != null) {
			//로그인 성공하면 index페이지로 이동
			//redirect
			session.setAttribute("member", memberDTO);
			mv.setViewName("redirect:../");
		}else {
			//로그인 실패 메시지 alert
			//로그인 입력 폼으로 이동
			//forward
			mv.addObject("msg","Login Fail!");
			mv.addObject("path", "./memberLogin");
			mv.setViewName("common/result");
		}
		
		return mv;
	}
	
}
