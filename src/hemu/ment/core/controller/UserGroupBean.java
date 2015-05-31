package hemu.ment.core.controller;

import hemu.ment.core.ejb.local.UserGroupLocal;
import hemu.ment.core.entity.UserGroup;

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

    public String update() {
        return null;
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
