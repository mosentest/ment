package hemu.ment.core.filter;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.cache.SessionObject;
import hemu.ment.core.constant.C;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

	@Inject
	private CacheConsole cacheConsole;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	                     FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String authToken = null;
		SessionObject session = null;
		try {
			authToken = (String) req.getSession().getAttribute(C.AUTH_TOKEN);
			session = cacheConsole.getSession(authToken);
		} catch (Exception e) {
			res.sendRedirect(req.getContextPath() + "/index.xhtml");
			return;
		}
		if (isLoginPage(req.getRequestURI())) {
			if (session != null) {
				res.sendRedirect(req.getContextPath() + "/c/dashboard.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if (session != null) {
				if (session.isAuthenticated()) {
					chain.doFilter(request, response);
				} else if (isAuthPage(req.getRequestURI())){
					chain.doFilter(request, response);
				} else {
					res.sendRedirect(req.getContextPath() + "/c/sso.xhtml");
				}
			} else {
				res.sendRedirect(req.getContextPath() + "/index.xhtml");
			}
		}
	}

	private boolean isAuthPage(String url) {
		return url.startsWith("/c/sso.html") || url.startsWith("/c/sso.xhtml") || url.startsWith("/c/sso.jsf");
	}

	private boolean isLoginPage(String url) {
		return url.endsWith("/c/index.html") || url.endsWith("/c/index.xhtml") || url.endsWith("/c/index.jsf");
	}

	@Override
	public void destroy() {

	}

}
