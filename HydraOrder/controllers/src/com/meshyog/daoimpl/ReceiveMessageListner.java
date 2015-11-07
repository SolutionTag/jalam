package com.meshyog.daoimpl;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ReceiveMessageListner implements MessageListener {

	public void onMessage(final Message message) {
		System.out.println("Loving you varadharajan ");
		if (message instanceof MapMessage) {
			final MapMessage mapMessage = (MapMessage) message;
			// do something
		}
	}

}
