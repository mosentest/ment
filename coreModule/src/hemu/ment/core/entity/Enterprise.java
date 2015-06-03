package hemu.ment.core.entity;

import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**

 　　　　　　　　┏┓　　　┏┓
 　　　　　　　┏┛┻━━━┛┻┓
 　　　　　　　┃　　　　　　　┃ 　
 　　　　　　　┃　　　━　　　┃
 　　　　　　　┃　＞　　　＜　┃
 　　　　　　　┃　　　　　　　┃
 　　　　　　　┃...　⌒　...　┃
 　　　　　　　┃　　　　　　　┃
 　　　　　　　┗━┓　　　┏━┛
 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting　　　　　　　　　　
 　　　　　　　　　┃　　　┃ 神兽保佑,代码无bug
 　　　　　　　　　┃　　　┃　　　　　　　　　　　
 　　　　　　　　　┃　　　┃ 　　　　　　
 　　　　　　　　　┃　　　┃
 　　　　　　　　　┃　　　┃　　　　　　　　　　　
 　　　　　　　　　┃　　　┗━━━┓
 　　　　　　　　　┃　　　　　　　┣┓
 　　　　　　　　　┃　　　　　　　┏┛
 　　　　　　　　　┗┓┓┏━┳┓┏┛
 　　　　　　　　　　┃┫┫　┃┫┫
 　　　　　　　　　　┗┻┛　┗┻┛ */
@Entity
@Table(name = "t_enterprise", schema = "ment_core")
@NamedQueries(@NamedQuery(name = "Enterprise.GetAllActive", query = "SELECT e FROM Enterprise e WHERE e.enabled = true"))
public class Enterprise implements Serializable {

	private static final long serialVersionUID = 782680675742454535L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 5)
	private String code;

    @Column(name = "name", nullable = false, unique = true)
	private String name;

    @Column(name = "description")
	private String description;	

	@Column(name = "create_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date create;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_user_id")
    private User defaultUser;

	@Column(name = "enabled")
	private boolean enabled;

    @Column(name = "confirm_key")
    private String confirmKey;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "email_settings_id")
    private EmailSettings emailSettings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "global_settings_id")
    private GlobalSettings globalSettings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "i18n_settings_id")
    private InternationalizationSettings internationalizationSettings;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enterprise")
    private List<ProjectCategory> projectCategories;

    @OneToMany(mappedBy = "enterprise")
    private List<EnterpriseModule> modules;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enterprise")
    private List<EnterpriseConfig> configs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enterprise")
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enterprise")
    private List<UserGroup> userGroups;

    public boolean isMaster() {
        return id == 1l;
    }
    
    public Enterprise() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public User getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(User defaultUser) {
        this.defaultUser = defaultUser;
    }

    public String getConfirmKey() {
        return confirmKey;
    }

    public void setConfirmKey(String confirmKey) {
        this.confirmKey = confirmKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailSettings getEmailSettings() {
        return emailSettings;
    }

    public void setEmailSettings(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    public GlobalSettings getGlobalSettings() {
        return globalSettings;
    }

    public void setGlobalSettings(GlobalSettings globalSettings) {
        this.globalSettings = globalSettings;
    }

    public InternationalizationSettings getInternationalizationSettings() {
        return internationalizationSettings;
    }

    public void setInternationalizationSettings(InternationalizationSettings internationalizationSettings) {
        this.internationalizationSettings = internationalizationSettings;
    }

    public List<EnterpriseModule> getModules() {
        return modules;
    }

    public void setModules(List<EnterpriseModule> modules) {
        this.modules = modules;
    }

    public List<EnterpriseConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<EnterpriseConfig> configs) {
        this.configs = configs;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
