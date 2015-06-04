package hemu.ment.core.entity.system;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by muu on 2015/5/25.
 */
@Entity
@Table(name = "t_role", schema = "ment_core")
public class Role implements Serializable {

	private static final long serialVersionUID = 1509140799978096631L;

	@Id
	private String name;

	public Role() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

