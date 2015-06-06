package hemu.ment.comm.entity;

import hemu.ment.core.entity.Enterprise;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by muu on 2015/6/4.
 */
@Entity
@Table(name = "t_message", schema = "ment_comm")
public class Message implements Serializable {

	private static final long serialVersionUID = 7325167214419439874L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

}
