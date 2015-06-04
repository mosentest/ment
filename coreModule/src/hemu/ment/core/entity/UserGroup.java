package hemu.ment.core.entity;

import hemu.ment.core.enums.RoleConstant;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "t_usergroup", schema = "ment_core")
@NamedQueries({
		@NamedQuery(name = "UserGroup.GetList", query = "SELECT ug FROM UserGroup ug WHERE ug.enterprise.id = :enterprise"),
		@NamedQuery(name = "UserGroup.Get", query = "SELECT ug FROM UserGroup ug WHERE ug.enterprise.id = :enterprise AND ug.role = :role")
})
public class UserGroup implements Serializable {

	private static final long serialVersionUID = -1838588996310300202L;

	private static final String[] SORTABLE_COLUMNS = {
			"name", "description", "userCount"
	};

	private static final String DEFAULT_COLUMN = "name";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "default_group")
	private boolean defaultGroup;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "role_name")
	private String role;

	@Transient
	private RoleConstant roleConstant;

	@ManyToMany(fetch = FetchType.LAZY)
	@OrderBy("firstName, lastName")
	@JoinTable(schema = "ment_core", name = "t_usergroup_user",
			uniqueConstraints = @UniqueConstraint(columnNames = {"usergroup_id", "user_id"}),
			joinColumns = @JoinColumn(name = "usergroup_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private List<User> users;

	public UserGroup() {
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserGroup)) {
			return false;
		}
		UserGroup u = (UserGroup) obj;
		return u.id != null && this.id != null && u.id.equals(this.id);
	}

	public static String getSortColumn(String column) {
		for (String c : SORTABLE_COLUMNS) {
			if (c.equals(column)) {
				return c;
			}
		}
		return DEFAULT_COLUMN;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(boolean defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public RoleConstant getRoleConstant() {
		return roleConstant;
	}

	public void setRoleConstant(RoleConstant roleConstant) {
		this.roleConstant = roleConstant;
	}
}
