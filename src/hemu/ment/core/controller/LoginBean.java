package hemu.ment.core.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -2228271739572661055L;
	
	public static final String COOKIE_NAME = "remember_me";
	public static final int COOKIE_AGE = 604800;
	
	private String email;
	private String password;

	private boolean rememberMe;
	
	public LoginBean() {}
	
	public String login() {
		return "/dashboard.xhtml?faces-redirect=true";
	}
	
	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
