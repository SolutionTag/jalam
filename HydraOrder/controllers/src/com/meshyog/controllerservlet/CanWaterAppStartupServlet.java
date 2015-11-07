package com.meshyog.controllerservlet;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class CanWaterAppStartupServlet  extends HttpServlet{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("static-access")
	public  void init(ServletConfig config) throws ServletException{
		super.init(config);
		config.getServletContext().setAttribute("path", "resources/bower_components");
		try {
			if(new DataBaseStartupServlet().connectToMongo()!= null){
				System.out.println("Data Base connected");
			}else{
				System.out.println("Data Base Not connected.");
			}
			//new ActiveMQConnection().loadActiveMqConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
