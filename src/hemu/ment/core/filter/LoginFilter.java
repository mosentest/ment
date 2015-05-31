package hemu.ment.core.filter;

import hemu.ment.core.controller.UserBean;
import hemu.ment.core.utility.ContextUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		UserBean userBean = ContextUtil.getUserBean(req);
        boolean isLoggedIn = userBean != null && userBean.isAuthenticated();
        if (isLoginPage(req.getRequestURI())) {
            if (isLoggedIn) {
                res.sendRedirect(req.getContextPath() + "/c/dashboard.xhtml");
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
