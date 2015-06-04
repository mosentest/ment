package hemu.ment.core.controller;

import hemu.ment.core.ejb.local.UserLocal;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;
import hemu.ment.core.exception.InformationException;
import hemu.ment.core.utility.FacesMessageUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "current")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -2228271739572661055L;

	private String email;
	private String password;
	private boolean rememberMe;

	private User user;
	private Enterprise enterprise;

	@EJB
	private UserLocal userEJB;

	public LoginBean() {
	}

	public String login() {
		try {
			user = userEJB.login(email, password);
			enterprise = user.getEnterprise();
			return "/c/dashboard.xhtml?faces-redirect=true";
		} catch (InformationException e) {
			FacesMessageUtil.addErrorMessage(e.getMessage(), null);
			return (email = password = null);
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	public String getFullName() {
		return user.getFullName();
	}

	public boolean isAuthenticated() {
		return user != null;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
}
