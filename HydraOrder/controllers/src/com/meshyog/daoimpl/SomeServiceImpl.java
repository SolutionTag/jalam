package com.meshyog.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.meshyog.dao.SomeService;
@Service("someService") 
public class SomeServiceImpl implements SomeService {
	 
    private final JmsTemplate jms;
 
    @Autowired public SomeServiceImpl(@Qualifier("eventTopicTemplate") JmsTemplate eventTopicTemplate) {
        jms = eventTopicTemplate;
    }
 
    public void doProcessing(String msg) {
    	System.out.println("We are converting the messgae");
        jms.convertAndSend("We did something with your message: " + msg);
    }
 
}
