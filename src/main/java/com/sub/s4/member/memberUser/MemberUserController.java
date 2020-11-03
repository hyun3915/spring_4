package com.sub.s4.member.memberUser;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sub.s4.board.memo.MemoDTO;
import com.sub.s4.member.MemberDTO;
import com.sub.s4.member.memberFile.MemberFileDTO;

@Controller
@RequestMapping("/member/**") //member로 시작하는건 다 여기로 오게하기
public class MemberUserController {
	
	@Autowired
	private MemberUserService memberUserService;
	
	@GetMapping("memberIdCheck")
	public ModelAndView getMemberIdCheck(MemberDTO memberDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberDTO = memberUserService.getMemberIdCheck(memberDTO);
		int result =1; //중복
		if(memberDTO==null) {
			result=0;
		}
		
		mv.addObject("msg", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}
	
	@GetMapping("memberJoin") 
	public ModelAndView setMemberJoin()throws Exception{ 
 		ModelAndView mv = new ModelAndView(); 
 		mv.setViewName("member/memberJoin"); 
		return mv; 
	 	} 
		 
	@PostMapping("memberJoin") 
	public ModelAndView setMemberJoin(MemberDTO memberDTO, MultipartFile photo, HttpSession session)throws Exception{ 
		ModelAndView mv = new ModelAndView(); 
	 	System.out.println(photo.getOriginalFilename());
	 	System.out.println(photo.getName());
	 	System.out.println(photo.getSize());
	 	System.out.println(photo.getContentType());
	 	
	 	int result = memberUserService.setMemberJoin(memberDTO, photo, session);

 		mv.setViewName("redirect:../"); 
		 
		return mv; 
	} 

	
	
	@GetMapping(value="memberDelete")
	public ModelAndView setMemberDelete(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		int result = memberUserService.setMemberDelete(memberDTO);
			session.invalidate(); //유지시간을 강제로 0으로 바꿔서 로그아웃

		
		mv.setViewName("redirect:../");
		return mv;
		
	}
	
	@PostMapping(value = "memberUpdate")
	public ModelAndView setMemberUpdate(MemberDTO memberDTO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberDTO s = (MemberDTO)session.getAttribute("member"); //여기 세션에 id꺼내옴
		memberDTO.setId(s.getId());
		
		int result = memberUserService.setMemberUpdate(memberDTO);
		
		if(result>0) {
			s.setName(memberDTO.getName());
			s.setEmail(memberDTO.getEmail());
			session.setAttribute("member", s); //멤버라는 이름으로 s로 덮어씌움
			
		}
		
		mv.setViewName("redirect:./memberPage");
		
		return mv;
	}

	
	@GetMapping(value = "memberUpdate")
	public ModelAndView setMemberUpdate() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberUpdate");
		
		return mv;
	}
	
	@GetMapping(value = "memberPage")
	public ModelAndView getMemberPage() throws Exception{
		ModelAndView mv = new ModelAndView();
//		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
//		MemberFileDTO memberFileDTO = memberUserService.getOne(memberDTO);
//		mv.addObject("file", memberFileDTO);
		mv.setViewName("member/memberPage");
		
		return mv; //memberPage.jsp로 이동만
	}
	
	@GetMapping(value = "memberLogout")
	public ModelAndView getMemberLogout(HttpSession session) throws Exception{
		//웹브라우저 종료
		//일정시간 경과되면 종료(로그인 후에 요청이 발생하면 시간이 연장)
		//MemberDTO를 Null로 대체
		//유지시간을 강제로 0으로 변경
		//웹브라우저별로 session이 적용
		session.invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
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
		
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPw());
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
