package com.meshyog.controllerservlet;



import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;



public class LoadActiveMQ extends HttpServlet {

/**
* serialVersionUID for serialization
*/
private static final long serialVersionUID = -8289081958495740549L;





@Override
public void init(){
	try{
	InitialContext initCtx = new InitialContext();
    Context envContext = (Context) initCtx.lookup("java:comp/env");
    ConnectionFactory connectionFactory = (ActiveMQConnectionFactory) envContext.lookup("jms/ConnectionFactory");
    Connection connection = connectionFactory.createConnection();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createQueue("jms/queue/Queue");
    MessageProducer producer = session.createProducer(destination);
    TextMessage msg = session.createTextMessage();
    msg.setText("Message sent");
    producer.send(msg);
	}catch(Exception ex){
		ex.printStackTrace();
	}
}


@Override
public void service(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	
}

@Override
public void destroy() {
try {



} catch (Exception e) {
e.printStackTrace();

}
}
}
