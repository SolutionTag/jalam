package com.meshyog.controllerservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.util.FactoryFinder;
import org.apache.activemq.util.IntrospectionSupport;
import org.apache.activemq.web.NoSuchViewStyleException;
import org.apache.activemq.web.WebClient;
import org.apache.activemq.web.view.MessageRenderer;
import org.apache.activemq.web.view.XmlMessageRenderer;

public class QueueBrowseServlet extends HttpServlet {
	private static FactoryFinder factoryFinder = new FactoryFinder(
			"META-INF/services/org/apache/activemq/web/view/");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			WebClient client = WebClient.getWebClient(request);
			Session session = client.getSession();
			Queue queue = getQueue(request, session);
			if (queue == null) {
				throw new ServletException("No queue URI specified");
			}

			String msgId = request.getParameter("msgId");
			if (msgId == null) {
				MessageRenderer renderer = getMessageRenderer(request);
				configureRenderer(request, renderer);

				String selector = getSelector(request);
				QueueBrowser browser = session.createBrowser(queue, selector);
				renderer.renderMessages(request, response, browser);
			} else {
				XmlMessageRenderer renderer = new XmlMessageRenderer();
				QueueBrowser browser = session.createBrowser(queue,
						"JMSMessageID='" + msgId + "'");
				if (!(browser.getEnumeration().hasMoreElements())) {
					response.sendError(404);
					return;
				}
				Message message = (Message) browser.getEnumeration()
						.nextElement();

				PrintWriter writer = response.getWriter();
				renderer.renderMessage(writer, request, response, browser,
						message);
				writer.flush();
			}
		} catch (JMSException e) {
			throw new ServletException(e);
		}
	}

	protected MessageRenderer getMessageRenderer(HttpServletRequest request)
			throws IOException, ServletException {
		String style = request.getParameter("view");
		if (style == null)
			style = "simple";
		try {
			return ((MessageRenderer) factoryFinder.newInstance(style));
		} catch (IllegalAccessException e) {
			throw new NoSuchViewStyleException(style, e);
		} catch (InstantiationException e) {
			throw new NoSuchViewStyleException(style, e);
		} catch (ClassNotFoundException e) {
			throw new NoSuchViewStyleException(style, e);
		}
	}

	protected void configureRenderer(HttpServletRequest request,
			MessageRenderer renderer) {
		Map properties = new HashMap();
		for (Enumeration iter = request.getParameterNames(); iter
				.hasMoreElements();) {
			String name = (String) iter.nextElement();
			properties.put(name, request.getParameter(name));
		}
		IntrospectionSupport.setProperties(renderer, properties);
	}

	protected String getSelector(HttpServletRequest request) {
		return request.getParameter("selector");
	}

	protected Queue getQueue(HttpServletRequest request, Session session)
			throws JMSException {
		String uri = request.getPathInfo();
		if (uri == null) {
			return null;
		}

		if (uri.startsWith("/")) {
			uri = uri.substring(1);
			if (uri.length() == 0) {
				return null;
			}
		}
		uri = uri.replace('/', '.');

		return session.createQueue(uri);
	}
}