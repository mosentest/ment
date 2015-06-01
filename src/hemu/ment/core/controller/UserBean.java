package hemu.ment.core.controller;

import hemu.ment.core.ejb.local.UserLocal;
import hemu.ment.core.entity.User;
import hemu.ment.core.query.Page;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.Map;

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
    private Long enterprise;

    private String sort;

    private String query;

    private int pn;

    private int lim;

    private Long id;

    private Map<String, Object> values = new HashMap<>();

    private String url;

    public void list() {
        pn = pn < 0 ? 0 : pn;
        lim = lim < 10 || lim > 50 ? lim = SIZE : lim;
        sort = sort == null ? User.DEFAULT_COLUMN : sort;
        list = userEJB.list(enterprise, User.ORDER_MAP.get(sort), pn, lim);

        url = buildUrl();
        values.put("orders", User.ORDER_MAP);
    }

    private String buildUrl() {
        StringBuilder buffer = new StringBuilder("c/settings/userlist.xhtml?");
        if (query != null) buffer.append("query=" + query + "&");
        if (lim != SIZE) buffer.append("lim=" + lim + "&");
        if (sort != null && !sort.equals(User.DEFAULT_COLUMN)) buffer.append("sort=" + sort + "&");
        return buffer.toString();
    }

    public void get() {}

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

    public Long getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Long enterprise) {
        this.enterprise = enterprise;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
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
