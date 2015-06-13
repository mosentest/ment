package hemu.ment.core.filter;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.constant.C;
import hemu.ment.core.entity.User;
import hemu.ment.core.enums.RoleConstant;
import hemu.ment.core.utility.LinkedProperties;
import hemu.ment.core.utility.ContextUtil;
import sun.misc.Cache;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

/**
 * Created by muu on 2015/5/28.
 */
public class SecurityFilter implements Filter {

	private LinkedHashSet<FilterPattern> filterPatterns;

	@Inject
	private CacheConsole cacheConsole;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterPatterns = new LinkedHashSet<>();
		String path = filterConfig.getInitParameter("SECURITY_CONFIG_LOCATION");
		LinkedProperties properties = new LinkedProperties();
		try {
			filterConfig.getServletContext();
			InputStream stream = filterConfig.getServletContext().getResourceAsStream(path);
			properties.load(stream);
			for (String key : properties.propertyKeys()) {
				String pattern = key.replace("*", ".*?");
				FilterPattern filterPattern = new FilterPattern();
				filterPattern.setPattern(Pattern.compile(pattern));
				filterPattern.setRole(properties.getProperty(key));
				filterPatterns.add(filterPattern);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	                     FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		FilterPattern filterPattern = getFilterPattern(req.getRequestURI());
		boolean authorized = false;
		if (filterPattern == null) {
			authorized = true;
		} else {
			if (filterPattern.permitAll()) {
				authorized = true;
			} else if (filterPattern.isAuthenticated()) {//is always true cuz filter is called after login filter
				authorized = true;
			} else {
				User user = cacheConsole.getUser(ContextUtil.getAuthToken());
				for (RoleConstant role : user.getRoles()) {
					if (role.code.equals(filterPattern.getRole())) {
						authorized = true;
						break;
					}
				}
			}
		}
		if (authorized) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/unauthorized.xhtml");
		}

	}

	private FilterPattern getFilterPattern(String url) {
		for (FilterPattern filterPattern : filterPatterns) {
			if (filterPattern.matches(url)) {
				return filterPattern;
			}
		}
		return null;
	}


	@Override
	public void destroy() {

	}

	private class FilterPattern {
		private Pattern pattern;
		private String role;

		public boolean matches(String url) {
			return pattern.matcher(url).matches();
		}

		public boolean permitAll() {
			return role.equals("permitAll");
		}

		public boolean isAuthenticated() {
			return role.equals("isAuthenticated");
		}

		public Pattern getPattern() {
			return pattern;
		}

		public void setPattern(Pattern pattern) {
			this.pattern = pattern;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}

}
