package hemu.ment.comm.filter;

import hemu.ment.comm.utility.ContextUtil;
import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.utility.EncryptionUtil;

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

/**
 * Created by muu on 2015/6/12.
 */
public class SSOFilter implements Filter {

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
		try {
			String authToken = (String) req.getAttribute("authToken");
			if (authToken.equals(cacheConsole.getAuthToken(ContextUtil.getClientAddress(req)))) {
				req.getSession(false).invalidate();
				req.getSession(true);
				req.getSession().setAttribute("authToken", authToken);
				res.sendRedirect(req.getContextPath() + "/c/index.xhtml");
			} else {
				res.sendRedirect("entconsole.com/index.xhtml");
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void destroy() {

	}

}
