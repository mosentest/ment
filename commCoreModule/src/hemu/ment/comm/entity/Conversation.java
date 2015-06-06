package hemu.ment.comm.entity;

import hemu.ment.core.entity.Enterprise;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by muu on 2015/6/5.
 */
@Entity
@Table(name = "t_conversation", schema = "ment_comm")
public class Conversation implements Serializable {

	private static final long serialVersionUID = 1947773009672809761L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

}