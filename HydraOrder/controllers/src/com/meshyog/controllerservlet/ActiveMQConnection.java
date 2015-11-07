package com.meshyog.controllerservlet;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class ActiveMQConnection {

	
	private static Session session = null;
	public static Session loadActiveMqConnection() throws JMSException,
			NamingException {
		if (session == null) {
		     ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("file:E:\\workspace1\\HydraOrder\\WEB-INF\\context.xml");;
				ConnectionFactory connectionFactory= (ActiveMQConnectionFactory) ctx.getBean("connectionFactory");
				Connection 	connection = connectionFactory.createConnection();
				connection.start();
				 session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
		}
		return session;
	}

}
