package ro.msg.cm.local;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalDevFilter implements Filter {
	private final String host;

	LocalDevFilter(String host) {
		this.host = host;
	}

	@Override
	public void init(FilterConfig filterConfig) {
		//nothing to do
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			String uri = request.getRequestURI();
			String referer = request.getHeader("Referer");
			if (referer != null && !referer.startsWith(host + "/api")) {
				response.setStatus(307);
				response.setHeader("Location", "http://localhost:3000" + uri);
				return;
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		//nothing to do
	}
}
