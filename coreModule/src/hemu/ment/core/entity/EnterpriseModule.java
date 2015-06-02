package hemu.ment.core.entity;

import hemu.ment.core.entity.system.Module;

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

@Entity
@Table(name = "t_enterprise_module", schema = "ment_core")
public class EnterpriseModule implements Serializable {

	private static final long serialVersionUID = -8412995696531047196L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="enterprise_id", referencedColumnName="id")
	private Enterprise enterprise;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="module_id", referencedColumnName="id")
	private Module module;
	
	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "from_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date from;

	@Column(name = "to_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date to;
	
	public EnterpriseModule() {}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
	
}
