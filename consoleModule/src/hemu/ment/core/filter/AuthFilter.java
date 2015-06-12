package hemu.ment.core.filter;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.controller.LoginBean;
import hemu.ment.core.utility.ContextUtil;
import sun.misc.Cache;

import javax.inject.Inject;
import javax.servlet.*;
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
		LoginBean loginBean = ContextUtil.getUserBean(req);
		boolean isLoggedIn = loginBean != null && loginBean.isAuthenticated();

		if (isLoginPage(req.getRequestURI())) {
			if (isLoggedIn) {
				res.sendRedirect(req.getContextPath() + "/sso.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if (isLoggedIn) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/index.xhtml");
			}
		}
	}

	private boolean isLoginPage(String url) {
		return url.endsWith("index.html") || url.endsWith("index.xhtml") || url.endsWith("index.jsf");
	}

	@Override
	public void destroy() {

	}

}
