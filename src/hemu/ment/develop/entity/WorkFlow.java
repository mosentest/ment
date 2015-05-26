package hemu.ment.develop.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_workflow", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "enterprise_id"}), schema = "ment_develop")
public class WorkFlow implements Serializable {
	
	private static final long serialVersionUID = -1574987429237936201L;

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

	@Column(name = "steps")
	private int steps;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id")
	private User creator;
	
	@Column(name = "modified_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date modified;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "default_status_id")
	private TicketStatus defaultStatus;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "resolved_status_id")
	private TicketStatus resolvedStatus;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "closed_status_id")
	private TicketStatus closedStatus;

	@Column(name = "valid")
	private boolean valid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workFlow", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("from ASC")
	private List<WorkFlowTransition> transitions;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(schema = "ment_develop", name = "t_workflow_status",
    	uniqueConstraints = @UniqueConstraint(columnNames = {"workflow_id", "status_id"}),
        joinColumns = @JoinColumn(name = "workflow_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "status_id", referencedColumnName = "id"))
	private List<TicketStatus> ticketStatus;
    
    public WorkFlow() {}
    
    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof WorkFlow)) {
    		return false;
    	}
    	WorkFlow o = (WorkFlow) obj;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public TicketStatus getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(TicketStatus defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	public List<WorkFlowTransition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<WorkFlowTransition> transitions) {
		this.transitions = transitions;
	}

	public List<TicketStatus> getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(List<TicketStatus> ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public TicketStatus getResolvedStatus() {
		return resolvedStatus;
	}

	public void setResolvedStatus(TicketStatus resolvedStatus) {
		this.resolvedStatus = resolvedStatus;
	}

	public TicketStatus getClosedStatus() {
		return closedStatus;
	}

	public void setClosedStatus(TicketStatus closedStatus) {
		this.closedStatus = closedStatus;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
