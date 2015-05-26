package hemu.ment.develop.entity;

import hemu.ment.core.entity.Enterprise;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_issue_type_scheme", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "enterprise_id"}), schema = "ment_develop")
public class TicketTypeScheme implements Serializable {

	private static final long serialVersionUID = -5849149551116388296L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(schema = "ment_develop", name = "ticket_type_scheme_types",
			uniqueConstraints = @UniqueConstraint(columnNames = {"ticket_type_scheme_id", "type_id"}),
			joinColumns = @JoinColumn(name = "ticket_type_scheme_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id"))
	@OrderBy("name ASC")
	private List<TicketType> ticketTypes;

	public TicketTypeScheme() {}

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

	public List<TicketType> getTicketTypes() {
		return ticketTypes;
	}

	public void setTicketTypes(List<TicketType> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
