package hemu.ment.core.controller;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.cache.SessionObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * Created by muu on 2015/6/13.
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean {

	@Inject
	private CacheConsole cacheConsole;

	private static int i = 0;

	public SessionObject session(String authToken) {
		SessionObject session = cacheConsole.getSession(authToken);
		i++;
		System.out.println(i);
		return session;
	}

}
