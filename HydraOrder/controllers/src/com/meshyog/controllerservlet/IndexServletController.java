package com.meshyog.controllerservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServletController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		System.out.println("request  from following url"+	request.getPathTranslated());	
		System.out.println(request.getContextPath());
	
		}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		System.out.println("request  from following url"+request.getPathInfo());
	}

}
