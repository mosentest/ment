package hemu.ment.comm.websocket;

import javax.ejb.Singleton;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by muu on 2015/6/4.
 */
@Singleton
public class CommServer {

	private Map<Long, Session> sessionMap;

	public CommServer() {
		sessionMap = new ConcurrentHashMap<>();
	}

	public void connect(Session session, long id) {
		sessionMap.put(id, session);
	}

	public boolean isConnected(long id) {
		return sessionMap.containsKey(id);
	}

	public Session getSession(long id) {
		return sessionMap.get(id);
	}

	public void disconnect(Session session, long id) {
		sessionMap.remove(id);
	}

	public void shutdown() {

	}

}
