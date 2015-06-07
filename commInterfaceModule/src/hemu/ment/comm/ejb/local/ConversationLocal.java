package hemu.ment.comm.ejb.local;

import hemu.ment.comm.entity.Conversation;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by muu on 2015/6/5.
 */
@Local
public interface ConversationLocal {

	public List<Conversation> list(long enterprise, long user);

}
