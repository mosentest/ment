package hemu.ment.develop.entity;

import hemu.ment.core.permission.ProjectPermission;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_workflow_transition", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"transition", "from_status_id", "to_status_id", "workflow_id"}), schema = "ment_develop")
public class WorkFlowTransition implements Serializable {

	private static final long serialVersionUID = 3289713503926256712L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "workflow_id")
	private WorkFlow workFlow;
	
	@Column(name = "transition")
	private String transition;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "from_status_id")
	private TicketStatus from;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "to_status_id")
	private TicketStatus to;

	@Column(name = "required_permission_value")
	private int requiredPermissionValue;
	
	@Transient
	private List<ProjectPermission> requiredPermissions;
	
	public WorkFlowTransition() {}

    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof WorkFlowTransition)) {
    		return false;
    	}
    	WorkFlowTransition o = (WorkFlowTransition) obj;
    	return o.id != null && this.id != null && o.id.equals(this.id);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	public String getTransition() {
		return transition;
	}

	public void setTransition(String transition) {
		this.transition = transition;
	}

	public TicketStatus getFrom() {
		return from;
	}

	public void setFrom(TicketStatus from) {
		this.from = from;
	}

	public TicketStatus getTo() {
		return to;
	}

	public void setTo(TicketStatus to) {
		this.to = to;
	}

	public List<ProjectPermission> getRequiredPermissions() {
		return requiredPermissions;
	}

	public void setRequiredPermissions(List<ProjectPermission> requiredPermissions) {
		this.requiredPermissions = requiredPermissions;
	}

	public int getRequiredPermissionValue() {
		return requiredPermissionValue;
	}

	public void setRequiredPermissionValue(int requiredPermissionValue) {
		this.requiredPermissionValue = requiredPermissionValue;
	}

}
