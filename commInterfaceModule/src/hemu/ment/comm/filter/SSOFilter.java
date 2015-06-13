package hemu.ment.comm.filter;

import hemu.ment.comm.utility.ContextUtil;
import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.cache.SessionObject;
import hemu.ment.core.constant.C;

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
			if (req.getSession().getAttribute(C.AUTH_TOKEN) != null) {
				res.sendRedirect(req.getContextPath() + "/c/index.xhtml");
				return;
			}
		} catch (Exception e) {
			//unauthorized
		}
		try {
			String authToken = req.getParameter(C.AUTH_TOKEN);
			if (authToken.equals(cacheConsole.getAuthToken(ContextUtil.getClientAddress(req)))) {
				req.getSession(true);
				req.getSession().setAttribute(C.AUTH_TOKEN, authToken);
				SessionObject session = cacheConsole.getSession(authToken);
				session.authenticate(true);
				cacheConsole.cacheSession(authToken, session);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("http://www.entconsole.com/index.xhtml");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
