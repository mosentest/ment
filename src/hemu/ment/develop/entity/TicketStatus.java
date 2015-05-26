package hemu.ment.develop.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.exception.InformationException;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_ticket_status", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "enterprise_id"}), schema = "ment_develop")
public class TicketStatus implements Serializable {
	
	public static final String[] ICONS = {
		"assigned", "closed", "document", "down", "email", "generic", "information", "inprogress",
		"invisible", "needinfo", "open", "reopened", "resolved", "trash", "unassigned", "up", "visible"
	};

	private static final long serialVersionUID = -8322633157968172805L;
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

	@Column(name = "icon")
	private String icon;
	
	@Column(name = "status_order")
	private int order;

	@Column(name = "color")
	private String color;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ticketStatus")
	private List<WorkFlow> workFlows;
	
	public TicketStatus() {}
	
	public static boolean validateIcon(String icon) throws InformationException {
		for (String string : ICONS) {
			if (string.equals(icon)) {
				return true;
			}
		}
		throw new InformationException("Icon is invalid");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TicketStatus)) {
			return false;
		}
		TicketStatus o = (TicketStatus) obj;
		return o.id != null && this.id != null && o.id.equals(this.id);
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<WorkFlow> getWorkFlows() {
		return workFlows;
	}

	public void setWorkFlows(List<WorkFlow> workFlows) {
		this.workFlows = workFlows;
	}

}
