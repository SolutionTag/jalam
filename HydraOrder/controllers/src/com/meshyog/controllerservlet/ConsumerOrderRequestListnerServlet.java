package com.meshyog.controllerservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshyog.daoimpl.ConsumerOrderDAOImpl;
import com.meshyog.daoimpl.ObserverImpl;

/**
 * Servlet implementation class ConsumerOrderRequestListnerServlet
 */
@WebServlet("/ConsumerOrderRequestListnerServlet")
public class ConsumerOrderRequestListnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*if(request.getSession().getAttribute("listnerreq")==null){
			request.getSession().setAttribute("listnerreq", request);
			request.getSession().setAttribute("listnerresponse", response);
		}
		
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		AsyncContext asyncContext= request.getAsyncContext();
	   Runnable orderRequestThrd = new Runnable() {
	            @Override
	            public void run() {
	            	while(true){
	            		if( new ConsumerOrderDAOImpl().getObserver() !=null){
		            		 int index=new ConsumerOrderDAOImpl().getObservers().indexOf(new ConsumerOrderDAOImpl().getObserver()); 
			            	 ObserverImpl observer= (ObserverImpl) new ConsumerOrderDAOImpl().getObservers().get(index);
			            	 if(observer.messageList.size()==1){
			            		 String message="data";
				                    writer.write("data: " + message + "\n\n");
				                    writer.flush();
				                    observer.messageList.remove(0);
			            	 }
			        		 
		            	}
	            		try {
	                       // Thread.sleep(2000);
	                    } catch(InterruptedException iex) {
	                        iex.printStackTrace();
	                    }
	            	}
	            	
	            	
	            	
	                // echo msg 5 times
	                for (int i = 0; i < 1; i++) {
	                    if (false) { // last
	                        // SSE event field
	                        writer.write("event: close\n");
	                    }
	                    // SSE data field
	                    // last field with blank new line
	                    String message="data";
	                    writer.write("data: " + message + "\n\n");
	                    writer.flush();

	                    try {
	                        Thread.sleep(2000);
	                    } catch(InterruptedException iex) {
	                        iex.printStackTrace();
	                    }
	                }

	                // complete async
	               // System.out.println(asyncContext);
	              //  asyncContext .complete();
	            }
	        };
	        orderRequestThrd.run();*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	

	

}
