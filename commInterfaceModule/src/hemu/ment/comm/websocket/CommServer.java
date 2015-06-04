package hemu.ment.comm.websocket;

import javax.ejb.Singleton;
import javax.jms.Session;
import java.util.Set;

/**
 * Created by muu on 2015/6/4.
 */
@Singleton
public class CommServer {

    private Set<Session> sessions;

    public CommServer() {

    }

}
