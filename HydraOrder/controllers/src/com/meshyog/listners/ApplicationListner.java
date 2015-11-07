package com.meshyog.listners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.meshyog.controllerservlet.DataBaseStartupServlet;

public class ApplicationListner  implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	System.out.println("application shutdown......");
	 new DataBaseStartupServlet().closeConnection();
	 System.out.println("Data base connection closed..");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("application starting......");
		
	}

}
