package hemu.ment.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class LoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
//		Employee user = SessionUtil.getEmployee(req);
//		boolean loginPage = req.getRequestURI().endsWith("/index.jsf");
//		boolean login = false;
//
//		if (user == null) {
//			String uuid = CookieUtil.getCookie(request, LoginBean.COOKIE_NAME);
//			if (uuid != null) {
//				LoginCookie cookie = loginService.getLoginCookie(uuid);
//				if (cookie != null) {
//					SessionUtil.setObject(request, "currentEmployee", cookie.getEmployee());
//					SessionUtil.setObject(request, "unreadMessage", messageService.getUnreadCount(user));
//					CookieUtil.addCookie(response, LoginBean.COOKIE_NAME, uuid, LoginBean.COOKIE_AGE);
//					login = true;
//				}
//			}
//		} else {
//			login = true;
//		}
//		if (!login && !loginPage) {
//			res.sendRedirect(req.getContextPath() + "/index.jsf");
//		} else if (login && loginPage) {//login page and already logged in
//			res.sendRedirect(req.getContextPath() + "/dashboard.jsf");
//		} else {
//			chain.doFilter(request, response);
//		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
