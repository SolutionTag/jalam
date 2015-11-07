package com.meshyog.controllerservlet;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener  {
	private final JmsTemplate jms;
	 @Autowired public SimpleMessageListener(@Qualifier("simpleMessageListener") JmsTemplate eventTopicTemplate) {
	        jms = eventTopicTemplate;
	    }

	
	public void processMessage(Message message) {
		try {   
			
			System.out.println("i am here "+message.getJMSCorrelationIDAsBytes());
            int messageCount = message.getIntProperty("f");
            
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage)message;
                String msg = tm.getText();
                
                
                //logger.info("Processed message '{}'.  value={}", msg, messageCount);
                
                //counter.incrementAndGet();
            }
        } catch (JMSException e) {
           //gger.error(e.getMessage(), e);
        }
		
	}

}
