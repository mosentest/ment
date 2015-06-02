package hemu.ment.core.entity;

import hemu.ment.core.enums.RoleConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "t_user", schema = "ment_core")
@NamedQueries({
		@NamedQuery(name = "User.Accessible", query = "SELECT COUNT(u) > 0 FROM User u WHERE u.id = :user AND u.enterprise.id = :enterprise"),
		@NamedQuery(name = "User.GetByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.GetEnterprise", query = "SELECT u.enterprise FROM User u WHERE u.id = :user"),
		@NamedQuery(name = "User.List", query = "SELECT u FROM User u WHERE u.enterprise.id = :enterprise"),
		@NamedQuery(name = "User.Size", query = "SELECT COUNT(u) FROM User u WHERE u.enterprise.id = :enterprise")})
public class User implements Serializable {

	private static final long serialVersionUID = 7445898962052022294L;

	public static final Comparator<User> COMPARATOR_NAME = new Comparator<User>() {
		@Override
		public int compare(User o1, User o2) {
			int result = 0;
			result = o1.firstName.compareToIgnoreCase(o2.firstName);
			if (result != 0) {
				return result;
			}
			result = o1.firstName.compareToIgnoreCase(o2.firstName);
			if (result != 0) {
				return result;
			}
			return o1.id.compareTo(o2.id);
		}
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password_text")
	private String password;

	@Column(name = "password_hash")
	private String hash;

	@Column(name = "create_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date create;

	@Column(name = "login_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date login;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "country")
	private String country;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "enable_personal_settings")
	private boolean enablePersonalSettings;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<UserGroup> userGroups;

	@Transient
	private Set<RoleConstant> roles = new HashSet<>();

	public User() {}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User)) {
			return false;
		}
		User e = (User) o;
		return e.id != null && this.id != null && e.id.equals(this.id);
	}

	@Override
	public int hashCode() {
		return getFullName().hashCode();
	}

	public List<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public String getFullName() {
		return lastName + " " + firstName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isEnablePersonalSettings() {
		return enablePersonalSettings;
	}

	public void setEnablePersonalSettings(boolean enablePersonalSettings) {
		this.enablePersonalSettings = enablePersonalSettings;
	}

	public Set<RoleConstant> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleConstant> roles) {
		this.roles = roles;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
