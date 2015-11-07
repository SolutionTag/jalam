package com.meshyog.controllerservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshyog.daoimpl.DistributorDAOImpl;
import com.meshyog.daoimpl.StocksDAOImpl;

public class ConsumerController extends HttpServlet {

	/**
	 * 
	 */
	RequestDispatcher requestDispatcher=null;
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//StringBuilder sb = new StringBuilder(); 
		//sb.append("retry: 1\n");
		//sb.append("id: {0}\n", this.Version);
		//sb.appendF("id: {0}\n\n", this.Content());
		//response.setContentType( "text/event-stream");
		
		
		String url = request.getRequestURI();
		PrintWriter printWritter=null;
		String responseUrl="";
		if(url.equals("/canwater/consumer")){
			responseUrl="jsp/consumer/distributorcrud.jsp";
			requestDispatcher=request.getRequestDispatcher(responseUrl);
			try {
				new ConsumerController().setDistibutorList(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			requestDispatcher.forward(request, response);
		}else if(url.equals("/canwater/display-water-can")){
			responseUrl="jsp/consumer/distributorcanspage.jsp";
			requestDispatcher=request.getRequestDispatcher(responseUrl);
			String distributorId=request.getParameter("distributorid");
			request.getSession().setAttribute("distributorid",distributorId);
			try {
				new ConsumerController().setStockPagesData(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			requestDispatcher.forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}
	public void setDistibutorList(HttpServletRequest request) throws Exception{
		request.getSession().setAttribute("alldistributors", new DistributorDAOImpl().findDistributorForConsumers("WCanDistributorInfo"));
	}
	public void setStockPagesData(HttpServletRequest request) throws Exception{
		String distributorId=(String)request.getSession().getAttribute("distributorid");
		request.getSession().setAttribute("stocktable", new StocksDAOImpl().findAllStockCans("WCanStockInfo",distributorId));
	}
}
