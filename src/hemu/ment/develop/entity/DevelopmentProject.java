package hemu.ment.develop.entity;

import hemu.ment.core.entity.Project;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_development_project", schema = "ment_develop")
public class DevelopmentProject extends Project {

	private static final long serialVersionUID = 767713291652818823L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "projectpermissionscheme_id")
	private ProjectPermissionScheme projectPermissionScheme;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "tickettypescheme_id")
	private TicketTypeScheme ticketTypeScheme;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "workflow_id")
	private WorkFlow workFlow;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	private List<Ticket> tickets;

	public DevelopmentProject() {}

	public ProjectPermissionScheme getProjectPermissionScheme() {
		return projectPermissionScheme;
	}

	public void setProjectPermissionScheme(
			ProjectPermissionScheme projectPermissionScheme) {
		this.projectPermissionScheme = projectPermissionScheme;
	}

	public TicketTypeScheme getTicketTypeScheme() {
		return ticketTypeScheme;
	}

	public void setTicketTypeScheme(TicketTypeScheme ticketTypeScheme) {
		this.ticketTypeScheme = ticketTypeScheme;
	}

	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
