package com.meshyog.controllerservlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HttpServletBean;

public class ResourcesServlet extends HttpServletBean {
	private static final String HTTP_CONTENT_LENGTH_HEADER = "Content-Length";
	private static final String HTTP_LAST_MODIFIED_HEADER = "Last-Modified";
	private static final String HTTP_EXPIRES_HEADER = "Expires";
	private static final String HTTP_CACHE_CONTROL_HEADER = "Cache-Control";
	private static final Log log = LogFactory.getLog(ResourcesServlet.class);
	private final String protectedPath = "/?WEB-INF/.*";
	private String jarPathPrefix;
	private String springJsJarPathPrefix;
	private boolean gzipEnabled;
	private Set<String> allowedResourcePaths;
	private Map<String, String> defaultMimeTypes;
	private Set<String> compressedMimeTypes;
	private int cacheTimeout;

	public ResourcesServlet() {
		//this.protectedPath = "/?WEB-INF/.*";

		this.jarPathPrefix = "META-INF";

		this.springJsJarPathPrefix = "META-INF/web-resources";

		this.gzipEnabled = true;

		this.allowedResourcePaths = new HashSet();

		this.allowedResourcePaths.add("/**/*.css");
		this.allowedResourcePaths.add("/**/*.gif");
		this.allowedResourcePaths.add("/**/*.ico");
		this.allowedResourcePaths.add("/**/*.jpeg");
		this.allowedResourcePaths.add("/**/*.jpg");
		this.allowedResourcePaths.add("/**/*.js");
		this.allowedResourcePaths.add("/**/*.png");
		
		this.allowedResourcePaths.add("/**/*.woff2");
		this.allowedResourcePaths.add("/**/*.woff");
		this.allowedResourcePaths.add("/**/*.otf");
		this.allowedResourcePaths.add("/**/*.ttf");
		this.allowedResourcePaths.add("/**/*.svg");
		this.allowedResourcePaths.add("/**/*.eot");
		this.allowedResourcePaths.add("/**/*.sfnt");
		
		
		this.allowedResourcePaths.add("META-INF/**/*.css");
		this.allowedResourcePaths.add("META-INF/**/*.gif");
		this.allowedResourcePaths.add("META-INF/**/*.ico");
		this.allowedResourcePaths.add("META-INF/**/*.jpeg");
		this.allowedResourcePaths.add("META-INF/**/*.jpg");
		this.allowedResourcePaths.add("META-INF/**/*.js");
		this.allowedResourcePaths.add("META-INF/**/*.png");
		
		this.allowedResourcePaths.add("META-INF/**/*.woff2");
		this.allowedResourcePaths.add("META-INF/**/*.woff");
		this.allowedResourcePaths.add("META-INF/**/*.otf");
		this.allowedResourcePaths.add("META-INF/**/*.ttf");
		this.allowedResourcePaths.add("META-INF/**/*.svg");
		this.allowedResourcePaths.add("META-INF/**/*.eot");
		this.allowedResourcePaths.add("META-INF/**/*.sfnt");
		
		
		
		this.defaultMimeTypes = new HashMap();

		this.defaultMimeTypes.put(".css", "text/css");
		this.defaultMimeTypes.put(".gif", "image/gif");
		this.defaultMimeTypes.put(".ico", "image/vnd.microsoft.icon");
		this.defaultMimeTypes.put(".jpeg", "image/jpeg");
		this.defaultMimeTypes.put(".jpg", "image/jpeg");
		this.defaultMimeTypes.put(".js", "text/javascript");
		this.defaultMimeTypes.put(".png", "image/png");
		
		this.defaultMimeTypes.put(".woff2","application/font-woff2");
		this.defaultMimeTypes.put(".woff","application/font-woff");
		this.defaultMimeTypes.put(".otf","application/x-font-opentype");
		this.defaultMimeTypes.put(".ttf","application/x-font-ttf");
		this.defaultMimeTypes.put(".svg","image/svg+xml");
		this.defaultMimeTypes.put(".eot","application/vnd.ms-fontobject");
		this.defaultMimeTypes.put(".sfnt","application/font-sfnt");

		this.compressedMimeTypes = new HashSet();

		this.compressedMimeTypes.add("text/*");

		this.cacheTimeout = 31556926;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String rawResourcePath = request.getPathInfo();

		if (log.isDebugEnabled()) {
			log.debug("Attempting to GET resource: " + rawResourcePath);
		}

		URL[] resources = getRequestResourceURLs(request);

		if ((resources == null) || (resources.length == 0)) {
			if (log.isDebugEnabled()) {
				log.debug("Resource not found: " + rawResourcePath);
			}
			response.sendError(404);
			return;
		}

		prepareResponse(response, resources, rawResourcePath);

		OutputStream out = selectOutputStream(request, response);
		try {
			for (URL resource : resources) {
				URLConnection resourceConn = resource.openConnection();
				InputStream in = resourceConn.getInputStream();
				try {
					byte[] buffer = new byte[1024];
					int bytesRead = -1;
					while ((bytesRead = in.read(buffer)) != -1)
						out.write(buffer, 0, bytesRead);
				} finally {
					in.close();
				}
			}
		} finally {
			out.close();
		}
	}

	private OutputStream selectOutputStream(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String acceptEncoding = request.getHeader("Accept-Encoding");
		String mimeType = response.getContentType();

		if ((this.gzipEnabled) && (StringUtils.hasText(acceptEncoding))
				&& (acceptEncoding.indexOf("gzip") > -1)
				&& (matchesCompressedMimeTypes(mimeType))) {
			log.debug("Enabling GZIP compression for the current response.");
			return new GZIPResponseStream(response);
		}
		return response.getOutputStream();
	}

	private boolean matchesCompressedMimeTypes(String mimeType) {
		PathMatcher pathMatcher = new AntPathMatcher();
		for (String compressedMimeType : this.compressedMimeTypes) {
			if (pathMatcher.match(compressedMimeType, mimeType)) {
				return true;
			}
		}
		return false;
	}

	private void prepareResponse(HttpServletResponse response, URL[] resources,
			String rawResourcePath) throws IOException {
		long lastModified = -1L;
		int contentLength = 0;
		String mimeType = null;
		for (URL resource : resources) {
			URLConnection resourceConn = resource.openConnection();
			if (resourceConn.getLastModified() > lastModified) {
				lastModified = resourceConn.getLastModified();
			}

			String currentMimeType = getServletContext().getMimeType(
					resource.getPath());
			if (currentMimeType == null) {
				String extension = resource.getPath().substring(
						resource.getPath().lastIndexOf(46));
				currentMimeType = (String) this.defaultMimeTypes.get(extension);
			}
			if (mimeType == null)
				mimeType = currentMimeType;
			else if (!(mimeType.equals(currentMimeType))) {
				throw new MalformedURLException(
						"Combined resource path: "
								+ rawResourcePath
								+ " is invalid. All resources in a combined resource path must be of the same mime type.");
			}

			contentLength += resourceConn.getContentLength();
		}

		response.setContentType(mimeType);
		response.setHeader("Content-Length", Long.toString(contentLength));
		response.setDateHeader("Last-Modified", lastModified);
		if (this.cacheTimeout > 0)
			configureCaching(response, this.cacheTimeout);
	}

	protected long getLastModified(HttpServletRequest request) {
		if (log.isDebugEnabled())
			log.debug("Checking last modified of resource: "
					+ request.getPathInfo());
		URL[] resources;
		try {
			resources = getRequestResourceURLs(request);
		} catch (MalformedURLException e) {
			return -1L;
		}

		if ((resources == null) || (resources.length == 0)) {
			return -1L;
		}

		long lastModified = -1L;

		for (URL resource : resources) {
			URLConnection resourceConn;
			try {
				resourceConn = resource.openConnection();
			} catch (IOException e) {
				return -1L;
			}
			if (resourceConn.getLastModified() > lastModified) {
				lastModified = resourceConn.getLastModified();
			}
		}
		return lastModified;
	}

	private URL[] getRequestResourceURLs(HttpServletRequest request)
			throws MalformedURLException {
		String rawResourcePath = request.getPathInfo();
		String appendedPaths = request.getParameter("appended");
		if (StringUtils.hasText(appendedPaths)) {
			rawResourcePath = rawResourcePath + "," + appendedPaths;
		}
		String[] localResourcePaths = StringUtils.delimitedListToStringArray(
				rawResourcePath, ",");
		URL[] resources = new URL[localResourcePaths.length];
		for (int i = 0; i < localResourcePaths.length; ++i) {
			String localResourcePath = localResourcePaths[i];
			if (!(isAllowed(localResourcePath))) {
				if (log.isWarnEnabled()) {
					log.warn("An attempt to access a protected resource at "
							+ localResourcePath + " was disallowed.");
				}
				return null;
			}
			URL resource = getServletContext().getResource(localResourcePath);
			if (resource == null) {
				resource = getJarResource(this.springJsJarPathPrefix,
						localResourcePath);
			}
			if (resource == null) {
				resource = getJarResource(this.jarPathPrefix, localResourcePath);
			}
			if (resource == null) {
				if (resources.length > 1) {
					log.debug("Combined resource not found: "
							+ localResourcePath);
				}
				return null;
			}
			resources[i] = resource;
		}

		return resources;
	}

	private URL getJarResource(String jarPrefix, String resourcePath) {
		String jarResourcePath = jarPrefix + resourcePath;
		if (!(isAllowed(jarResourcePath))) {
			if (log.isWarnEnabled()) {
				log.warn("An attempt to access a protected resource at "
						+ jarResourcePath + " was disallowed.");
			}
			return null;
		}
		if (jarResourcePath.startsWith("/")) {
			jarResourcePath = jarResourcePath.substring(1);
		}
		if (log.isDebugEnabled()) {
			log.debug("Searching classpath for resource: " + jarResourcePath);
		}
		return ClassUtils.getDefaultClassLoader().getResource(jarResourcePath);
	}

	private boolean isAllowed(String resourcePath) {
		if (resourcePath.matches("/?WEB-INF/.*")) {
			return false;
		}
		PathMatcher pathMatcher = new AntPathMatcher();
		for (String pattern : this.allowedResourcePaths) {
			if (pathMatcher.match(pattern, resourcePath)) {
				return true;
			}
		}
		return false;
	}

	private void configureCaching(HttpServletResponse response, int seconds) {
		response.setDateHeader("Expires", System.currentTimeMillis() + seconds
				* 1000L);

		response.setHeader("Cache-Control", "max-age=" + seconds);
	}

	public void setGzipEnabled(boolean gzipEnabled) {
		this.gzipEnabled = gzipEnabled;
	}

	public void setAllowedResourcePaths(String allowedResourcePaths) {
		this.allowedResourcePaths = new HashSet(Arrays.asList(StringUtils
				.tokenizeToStringArray(allowedResourcePaths, ",", true, true)));
	}

	public void setCompressedMimeTypes(String compressedMimeTypes) {
		this.compressedMimeTypes = new HashSet(Arrays.asList(StringUtils
				.tokenizeToStringArray(compressedMimeTypes, ",", true, true)));
	}

	public void setJarPathPrefix(String jarPathPrefix) {
		this.jarPathPrefix = jarPathPrefix;
	}

	public void setCacheTimeout(int cacheTimeout) {
		this.cacheTimeout = cacheTimeout;
	}

	private class GZIPResponseStream extends ServletOutputStream {
		private ByteArrayOutputStream byteStream = null;

		private GZIPOutputStream gzipStream = null;

		private boolean closed = false;

		private HttpServletResponse response = null;

		private ServletOutputStream servletStream = null;

		public GZIPResponseStream(HttpServletResponse paramHttpServletResponse)
				throws IOException {
			this.closed = false;
			this.response = paramHttpServletResponse;
			this.servletStream = paramHttpServletResponse.getOutputStream();
			this.byteStream = new ByteArrayOutputStream();
			this.gzipStream = new GZIPOutputStream(this.byteStream);
		}

		public void close() throws IOException {
			if (this.closed) {
				throw new IOException(
						"This output stream has already been closed");
			}
			this.gzipStream.finish();

			byte[] bytes = this.byteStream.toByteArray();

			this.response.setContentLength(bytes.length);
			this.response.addHeader("Content-Encoding", "gzip");
			this.servletStream.write(bytes);
			this.servletStream.flush();
			this.servletStream.close();
			this.closed = true;
		}

		public void flush() throws IOException {
			if (this.closed) {
				throw new IOException("Cannot flush a closed output stream");
			}
			this.gzipStream.flush();
		}

		public void write(int b) throws IOException {
			if (this.closed) {
				throw new IOException("Cannot write to a closed output stream");
			}
			this.gzipStream.write((byte) b);
		}

		public void write(byte[] b) throws IOException {
			write(b, 0, b.length);
		}

		public void write(byte[] b, int off, int len) throws IOException {
			if (this.closed) {
				throw new IOException("Cannot write to a closed output stream");
			}
			this.gzipStream.write(b, off, len);
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setWriteListener(WriteListener arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}