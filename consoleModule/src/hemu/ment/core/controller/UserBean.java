package hemu.ment.core.controller;

import hemu.ment.core.ejb.local.UserLocal;
import hemu.ment.core.entity.User;
import hemu.ment.core.query.Page;
import hemu.ment.core.utility.FacesMessageUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by muu on 2015/6/1.
 */
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean {

    private static final int SIZE = 30;

    @EJB
    private UserLocal userEJB;

    private Page<User> list;

    private User user;

    @ManagedProperty(value = "#{current.enterprise.id}")
    private long enterprise;

    private String query;

    private int pn;

    private int lim;

    private long id;

    private String url;

    public void list() {
        pn = pn < 0 ? 0 : pn;
        lim = lim < 10 || lim > 50 ? lim = SIZE : lim;
        list = userEJB.list(enterprise, query, pn, lim);

    }

    private String buildUrl() {
        StringBuilder buffer = new StringBuilder("c/settings/userlist.xhtml?");
        if (query != null) buffer.append("query=" + query + "&");
        if (lim != SIZE) buffer.append("lim=" + lim + "&");
        return buffer.toString();
    }

    public void get() {
        if (id != 0) {
            try {
                user = userEJB.get(enterprise, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String create() {
        return null;
    }

    public String update() {
        return null;
    }

    public UserLocal getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UserLocal userEJB) {
        this.userEJB = userEJB;
    }

    public Page<User> getList() {
        return list;
    }

    public void setList(Page<User> list) {
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(long enterprise) {
        this.enterprise = enterprise;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public String getUrl() { return url; }

    public int getLim() {
        return lim;
    }

    public void setLim(int lim) {
        this.lim = lim;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
