package hemu.ment.comm.websocket;

import hemu.ment.comm.message.MessageDecoder;
import hemu.ment.comm.message.MessageEncoder;
import hemu.ment.comm.message.TransferMessage;
import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Properties;

/**
 * Created by muu on 2015/6/3.
 */
@ServerEndpoint(value = "/comm", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class CommEndpoint implements MessageListener {

	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DESTINATION = "queue/comm/in";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://127.0.0.1:80";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123456y!";

	private ConnectionFactory connectionFactory;

	private Destination destination;

	private MessageConsumer consumer;

	@Inject
	private CommServer commServer;

	private EndpointConfig endpointConfig;

	@Inject
	private CacheConsole cacheConsole;

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

	@PreDestroy
	public void preDestroy() {
		try {
			consumer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		commServer.shutdown();
	}

	@OnOpen
	public void open(@PathParam("authToken") String authToken, Session session, EndpointConfig endpointConfig) {
		this.endpointConfig = endpointConfig;
		commServer.connect(session, cacheConsole.uid(authToken));
	}

	@OnClose
	public void close(Session session) {

	}

	@OnMessage
	public void onMessage(TransferMessage message, Session session) {
		Connection connection = null;
		javax.jms.Session jmsSession = null;
		MessageProducer producer = null;
		try {
			connection = connectionFactory.createConnection(USERNAME, PASSWORD);
			jmsSession = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			producer = jmsSession.createProducer(destination);
			connection.start();
			ObjectMessage objectMessage = jmsSession.createObjectMessage(message);
			producer.send(objectMessage);
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
		if (consumer == null) {
			try {
			consumer = jmsSession.createConsumer(destination);
			consumer.setMessageListener(this);
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void onMessage(Message message) {
		System.out.println("received: " + message);
	}

}
