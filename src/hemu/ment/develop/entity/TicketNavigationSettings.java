package hemu.ment.develop.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.settings.SettingsEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
@Table(name = "t_ticket_navigation_settings", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "enterprise_id"}), schema = "ment_develop")
public class TicketNavigationSettings implements SettingsEntity, Serializable {
	
	public static enum TicketColumn {

		CODE("ticket.column.code"),
		SUMMARY("ticket.column.summary"),
		TYPE("ticket.column.type"),
		STATUS("ticket.column.status"),
		PRIORITY("ticket.column.priority"),
		RESOLUTION("ticket.column.resolution"),
		CREATE("ticket.column.create"),
		UPDATE("ticket.column.update"),
		REPORTER("ticket.column.reporter"),
		ASSIGNEE("ticket.column.assignee"),
		PROJECT("ticket.column.project"),
		RELEASE_VERSION("ticket.column.releaseVersion"),
		AFFECT_VERSION("ticket.column.affectVersion"),
		COMPONENT("ticket.column.component");
		
		private final String key;
		
		private TicketColumn(String key) {
			this.key = key;
		}
		
		public String getKey() {
			return key;
		}
	}
	
	private static final long serialVersionUID = 1161364870054375601L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;
	
	@Column(name = "column_string")
	private String columnString;

	@Column(name = "name")
	private String name;
	
	@Transient
	private List<TicketColumn> columns;

	@Override
	public void setDefaultSettings(Properties properties) {
		columnString = properties.getProperty("settings.ticketnavigation.default.columnString");
	}

	public List<TicketColumn> getColumns() {
		if (columns == null) {
			columns = new ArrayList<>();
			String[] split = columnString.split(",");
			for (String string : split) {
				columns.add(TicketColumn.valueOf(string.trim()));
			}
		} 
		return columns;
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

	public String getColumnString() {
		return columnString;
	}

	public void setColumnString(String columnString) {
		this.columnString = columnString;
	}

	public void setColumns(List<TicketColumn> columns) {
		this.columns = columns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
