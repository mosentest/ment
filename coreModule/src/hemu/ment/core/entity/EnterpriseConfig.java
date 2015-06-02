package hemu.ment.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_enterprise_config", schema = "ment_core")
public class EnterpriseConfig implements Serializable {

	private static final long serialVersionUID = 2937193425446241683L;
	
	@Id
	@Column(name = "code")
	private String code;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "description")
	private String description;

	@Column(name = "update_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date update;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	private User updatedBy;

	@Column(name = "value1")
	private String value1;

	@Column(name = "value2")
	private String value2;

	@Column(name = "value3")
	private String value3;

	@Column(name = "value4")
	private String value4;

	@Column(name = "value5")
	private String value5;

	@Column(name = "value6")
	private String value6;

	@Column(name = "value7")
	private String value7;

	@Column(name = "value8")
	private String value8;

	@Column(name = "value9")
	private String value9;

	@Column(name = "value10")
	private String value10;

	@Column(name = "value11")
	private String value11;

	@Column(name = "value12")
	private String value12;

	@Column(name = "value13")
	private String value13;

	@Column(name = "value14")
	private String value14;

	@Column(name = "value15")
	private String value15;

	@Column(name = "value16")
	private String value16;
	
	public EnterpriseConfig() {}
	
	public String stringValue(int value) {
		switch (value) {
			case 1:
				return value1;
			case 2:
				return value2;
			case 3:
				return value3;
			case 4:
				return value4;
			case 5:
				return value5;
			case 6:
				return value6;
			case 7:
				return value7;
			case 8:
				return value8;
			case 9:
				return value9;
			case 10:
				return value10;
			case 11:
				return value11;
			case 12:
				return value12;
			case 13:
				return value13;
			case 14:
				return value14;
			case 15:
				return value15;
			case 16:
				return value16;
			default:
				return null;
		}
	}
	
	public int intValue(int value) {
		switch (value) {
			case 1:
				return Integer.parseInt(value1);
			case 2:
				return Integer.parseInt(value2);
			case 3:
				return Integer.parseInt(value3);
			case 4:
				return Integer.parseInt(value4);
			case 5:
				return Integer.parseInt(value5);
			case 6:
				return Integer.parseInt(value6);
			case 7:
				return Integer.parseInt(value7);
			case 8:
				return Integer.parseInt(value8);
			case 9:
				return Integer.parseInt(value9);
			case 10:
				return Integer.parseInt(value10);
			case 11:
				return Integer.parseInt(value11);
			case 12:
				return Integer.parseInt(value12);
			case 13:
				return Integer.parseInt(value13);
			case 14:
				return Integer.parseInt(value14);
			case 15:
				return Integer.parseInt(value15);
			case 16:
				return Integer.parseInt(value16);
			default:
				return -1;
		}
	}
	
	public boolean booleanValue(int value) {
		switch (value) {
			case 1:
				return Boolean.parseBoolean(value1);
			case 2:
				return Boolean.parseBoolean(value2);
			case 3:
				return Boolean.parseBoolean(value3);
			case 4:
				return Boolean.parseBoolean(value4);
			case 5:
				return Boolean.parseBoolean(value5);
			case 6:
				return Boolean.parseBoolean(value6);
			case 7:
				return Boolean.parseBoolean(value7);
			case 8:
				return Boolean.parseBoolean(value8);
			case 9:
				return Boolean.parseBoolean(value9);
			case 10:
				return Boolean.parseBoolean(value10);
			case 11:
				return Boolean.parseBoolean(value11);
			case 12:
				return Boolean.parseBoolean(value12);
			case 13:
				return Boolean.parseBoolean(value13);
			case 14:
				return Boolean.parseBoolean(value14);
			case 15:
				return Boolean.parseBoolean(value15);
			case 16:
				return Boolean.parseBoolean(value16);
			default:
				return false;
		}
	}
	
	public double doubleValue(int value) {
		switch (value) {
			case 1:
				return Double.parseDouble(value1);
			case 2:
				return Double.parseDouble(value2);
			case 3:
				return Double.parseDouble(value3);
			case 4:
				return Double.parseDouble(value4);
			case 5:
				return Double.parseDouble(value5);
			case 6:
				return Double.parseDouble(value6);
			case 7:
				return Double.parseDouble(value7);
			case 8:
				return Double.parseDouble(value8);
			case 9:
				return Double.parseDouble(value9);
			case 10:
				return Double.parseDouble(value10);
			case 11:
				return Double.parseDouble(value11);
			case 12:
				return Double.parseDouble(value12);
			case 13:
				return Double.parseDouble(value13);
			case 14:
				return Double.parseDouble(value14);
			case 15:
				return Double.parseDouble(value15);
			case 16:
				return Double.parseDouble(value16);
			default:
				return -1;
		}
	}
	

}
