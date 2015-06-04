package hemu.ment.comm.websocket;

import javax.annotation.PostConstruct;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Properties;

/**
 * Created by muu on 2015/6/3.
 */
@ServerEndpoint(value = "/comm")
public class CommEndpoint {

	private ConnectionFactory connectionFactory;

	private Destination destination;

	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DESTINATION = "queue/ment/comm";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://127.0.0.1:80";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123456y!";

	@PostConstruct
	public void postConstruct() throws Exception {
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.PROVIDER_URL, PROVIDER_URL);
		prop.put(Context.SECURITY_PRINCIPAL, USERNAME);
		prop.put(Context.SECURITY_CREDENTIALS, PASSWORD);
		prop.put("jboss.naming.client.ejb.context", true);
		Context context = new InitialContext(prop);
		connectionFactory = (ConnectionFactory) context.lookup(CONNECTION_FACTORY);
		destination = (Destination) context.lookup(DESTINATION);
	}

	@OnOpen
	public void open(final Session session) {
		System.out.println(session.getId() + " has connected.");
		System.out.println(connectionFactory);
		session.getQueryString();
	}

	@OnMessage
	public void onMessage(String message, final Session session) {
		Connection connection = null;
		javax.jms.Session jmsSession = null;
		MessageProducer producer = null;
		try {
			connection = connectionFactory.createConnection(USERNAME, PASSWORD);
			jmsSession = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			producer = jmsSession.createProducer(destination);
			connection.start();
			TextMessage textMessage = jmsSession.createTextMessage(message);
			producer.send(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				producer.close();
				jmsSession.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
