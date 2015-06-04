package hemu.ment.communication.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by muu on 2015/6/4.
 */
@Entity
@Table(name = "chat_message", schema = "ment_communication")
public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 7325167214419439874L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

}
