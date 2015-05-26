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
@Table(name = "t_ticket_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "enterprise_id"}), schema = "ment_develop")
public class TicketType implements Serializable {

	private static final long serialVersionUID = -8322633157968172805L;
	
	public static final String[] ICONS = {
		"all_unassigned", "blank", "bug", "defect", "delete", "documentation",
		"epic", "exclamation", "generic", "health", "improvement", "newfeature",
		"removefeature", "requirement", "sales", "story", "subtask", "task", "taskagile", "undefined"
	};
	
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

	@Column(name = "is_standard")
	private boolean standard;

	@Column(name = "icon")
	private String icon;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ticketTypes")
	private List<TicketTypeScheme> ticketTypeSchemes;
	
	public TicketType() {}
	
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
		if (!(obj instanceof TicketType)) {
			return false;
		}
		TicketType t = (TicketType) obj;
		return t.id != null && this.id != null && t.id.equals(this.id);
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

	public boolean isStandard() {
		return standard;
	}

	public void setStandard(boolean standard) {
		this.standard = standard;
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

	public List<TicketTypeScheme> getTicketTypeSchemes() {
		return ticketTypeSchemes;
	}

	public void setTicketTypeSchemes(List<TicketTypeScheme> ticketTypeSchemes) {
		this.ticketTypeSchemes = ticketTypeSchemes;
	}

}
