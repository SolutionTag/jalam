package com.meshyog.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidUserTestFilter implements Filter {
	
	private String contextPath;
/*	private String loginPath="/user-login";
	private String registerPath="/distributor-register";
	private String ajaxLoginValidataion="/ajax-login-user-validation";
	private String userLoginValidatiaon="/user-login-validation";*/
	private String[] validPath={"/user-login","/distributor-register","/ajax-login-user-validation","/user-login-validation","/distributor-register-form-submit"};
	
	@Override
	public void destroy() {
	
		
	}

	@Override
	public void doFilter(ServletRequest serReq, ServletResponse serResp,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest) serReq;
		HttpServletResponse response=(HttpServletResponse) serResp;
		String url = request.getRequestURI(); 
		url=url.substring(contextPath.length());
		if(Arrays.asList(validPath).indexOf(url) !=-1)
		filterChain.doFilter(request, response);
		else if(request.getRequestURI().endsWith(".css")|| request.getRequestURI().endsWith(".js") || request.getRequestURI().endsWith(".png")){
			filterChain.doFilter(request, response);
		}
		else {
			if(request.getSession().getAttribute("distributorInfo")==null)
				request.getRequestDispatcher("jsp/loginrelated/userlogin.jsp").forward(request, response);
			else 
				filterChain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		contextPath=config.getServletContext().getContextPath();
		
	}

}
