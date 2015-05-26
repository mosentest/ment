package hemu.ment.develop.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_ticket", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "enterprise_id"}), schema = "ment_develop")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 410952237049468161L;
	
	private static final String[] SORTABLE_COLUMNS =  {
		"code", "summary", "create", "update", "close", "project"
	};
	
	private static final String DEFAULT_COLUMN = "code";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;
	
	@Column(name = "code")
	private String code;

	@Column(name = "summary")
	private String summary;

	@Column(name = "description")
	private String description;

	@Column(name = "create_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date create;

	@Column(name = "update_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date update;

	@Column(name = "close_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date close;
	
	@Column(name = "in_progress")
	private boolean inProgress;

	@Column(name = "resolved")
	private boolean resolved;

	@Column(name = "closed")
	private boolean closed;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private DevelopmentProject project;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private TicketType type;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "resolution_id")
	private TicketResolution resolution;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private TicketStatus status;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "priority_id")
	private TicketPriority priority;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ticket_reporter_id")
	private User reporter;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ticket_assignee_id")
	private User assignee;
	
	@Column(name = "removed")
	private boolean removed;
	
	public Ticket() {}
	
	public static String getSortColumn(String column) {
		for (String c : SORTABLE_COLUMNS) {
			if (c.equals(column)) {
				return c;
			}
		}
		return DEFAULT_COLUMN;
	}
	
	public String getCaseHeader() {
		return String.format("[%s] - %s", code, summary);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public Date getClose() {
		return close;
	}

	public void setClose(Date close) {
		this.close = close;
	}

	public DevelopmentProject getProject() {
		return project;
	}

	public void setProject(DevelopmentProject project) {
		this.project = project;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TicketResolution getResolution() {
		return resolution;
	}

	public void setResolution(TicketResolution resolution) {
		this.resolution = resolution;
	}
	
}
