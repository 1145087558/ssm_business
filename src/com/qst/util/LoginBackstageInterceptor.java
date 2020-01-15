package com.qst.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginBackstageInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/*String requestURI = request.getRequestURI();
		if(requestURI.contains("backstageLogin.form")||requestURI.contains("drawImage.form")
				||requestURI.contains("checkCode.form")){
			return true;
		}
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return true;
		}else{
			response.sendRedirect(request.getContextPath() + "/backstage/login.jsp");
			return false;
		}*/
		return true;
	}

}
