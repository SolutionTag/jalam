package com.meshyog.controllerservlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.web.MessageListenerServlet;

public class AjaxServlet extends MessageListenerServlet {
	
	private static final long serialVersionUID = -3875280764356406114L;
	private Map<String, byte[]> jsCache;
	private long jsLastModified;

	public AjaxServlet() {
		this.jsCache = new HashMap();
		this.jsLastModified = (1000L * System.currentTimeMillis() / 1000L);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if ((request.getPathInfo() != null)
				&& (request.getPathInfo().endsWith(".js")))
			doJavaScript(request, response);
		else
			super.doGet(request, response);
	}

	protected void doJavaScript(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String js = request.getServletPath() + request.getPathInfo();
		URL url = getServletContext().getResource(js);
		if (url != null) {
			getServletContext().getNamedDispatcher("default").forward(request,
					response);
			return;
		}

		String resource = "org/apache/activemq/web" + request.getPathInfo();
		synchronized (this.jsCache) {
			byte[] data = (byte[]) this.jsCache.get(resource);
			if (data == null) {
				InputStream in = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(resource);
				if (in != null) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] buf = new byte[4096];
					int len = in.read(buf);
					while (len >= 0) {
						out.write(buf, 0, len);
						len = in.read(buf);
					}
					in.close();
					out.close();
					data = out.toByteArray();
					this.jsCache.put(resource, data);
				}
			}

			if (data != null) {
				long ifModified = request.getDateHeader("If-Modified-Since");

				if (ifModified == this.jsLastModified) {
					response.sendError(304);
				} else {
					response.setContentType("application/x-javascript");
					response.setContentLength(data.length);
					response.setDateHeader("Last-Modified", this.jsLastModified);
					response.getOutputStream().write(data);
				}
			} else {
				response.sendError(404);
			}
		}
	}
}