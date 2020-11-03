package com.sub.s4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class QnaMemberInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("QnaMemberInterceptor");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("member"); //로그인한 사람 정보 가져오기
		
		boolean check = false;
		
		if(obj!=null) {
			check= true;
		}else {
		response.sendRedirect("../member/memberLogin");
		}
		
		return check;
	}

}
