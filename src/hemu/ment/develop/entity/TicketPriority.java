package hemu.ment.develop.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.exception.InformationException;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_ticket_priority", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "enterprise_id"}), schema = "ment_develop")
public class TicketPriority implements Serializable {
	
	public static final String[] ICONS = {
		"blocker", "critical", "major", "minor", "trivial" 
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
	
	@Column(name = "priority_order")
	private int order;

	@Column(name = "color")
	private String color;
	
	public TicketPriority() {}

	public static boolean validateIcon(String icon) throws InformationException {
		for (String string : ICONS) {
			if (string.equals(icon)) {
				return true;
			}
		}
		throw new InformationException("Icon is invalid");
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
