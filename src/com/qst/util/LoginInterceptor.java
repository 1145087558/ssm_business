package com.qst.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		if(requestURI.contains("register.form")||requestURI.contains("emailRegister.form")
				||requestURI.contains("telRegister.form")||requestURI.contains("login.form")
				||requestURI.contains("sendPhoneCode.form")||requestURI.contains("checkEmail.form")
				||requestURI.contains("checkPhone.form")||requestURI.contains("logout.form")
				||requestURI.contains("findAll.form")||requestURI.contains("opusDetail.form")
				||requestURI.contains("backstage/backstageLogin.form")
				||requestURI.contains("backstage/drawImage.form")||requestURI.contains("backstage/checkCode.form")){
			
			return true;
		}else{
			HttpSession session = request.getSession();
			if (session.getAttribute("user") != null) {
				return true;
			}else{
				if (request.getHeader("x-requested-with") != null
						&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					//如果是ajax请求响应头会有x-requested-with
	                response.setStatus(403);
				}else{
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
				
				return false;
			}
		}
		
		
		
	}

	
}
