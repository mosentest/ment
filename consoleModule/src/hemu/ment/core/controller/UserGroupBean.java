package hemu.ment.core.controller;

import hemu.ment.core.ejb.local.UserGroupLocal;
import hemu.ment.core.entity.UserGroup;
import hemu.ment.core.utility.FacesMessageUtil;
import hemu.ment.core.utility.StringUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by muu on 2015/5/30.
 */
@ManagedBean(name = "userGroupBean")
@RequestScoped
public class UserGroupBean {

	@EJB
	private UserGroupLocal userGroupEJB;

	@ManagedProperty(value = "#{current.enterprise.id}")
	private Long enterprise;

	@ManagedProperty(value = "#{param.role}")
	private String role;

	private List<UserGroup> list;

	private UserGroup userGroup;

	public void list() {
		list = userGroupEJB.getList(enterprise);
	}

	public void get() {
		userGroup = userGroupEJB.getUserGroup(enterprise, role);
	}

	private void validateUserGroup() {
		if (userGroup == null) {
			FacesMessageUtil.addErrorMessage("Error occurred");
		}
		if (StringUtil.empty(userGroup.getName())) {
			FacesMessageUtil.validationError("form", "name", "Group name can not be null");
		}
		//check for duplicated name

	}

	public String update() {
		validateUserGroup();
		if (FacesMessageUtil.containsValidationError()) {
			return "/c/settings/usergroupform.xhtml?role=" + role;
		}
		FacesMessageUtil.addInfoMessage("User group has been updated");
		return "/c/settings/usergrouplist.xhtml";
	}

	public Long getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Long enterprise) {
		this.enterprise = enterprise;
	}

	public void setList(List<UserGroup> list) {
		this.list = list;
	}

	public List<UserGroup> getList() {
		return list;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
