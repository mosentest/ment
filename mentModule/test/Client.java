import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by muu on 2015/6/2.
 */
public class Client {

    private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DESTINATION = "queue/communication/chat";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:80";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456y!";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        MessageProducer producer = null;
        Session session = null;
        Properties prop = new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        prop.put(Context.PROVIDER_URL, PROVIDER_URL);
        prop.put(Context.SECURITY_PRINCIPAL, USERNAME);
        prop.put(Context.SECURITY_CREDENTIALS, PASSWORD);
        prop.put("jboss.naming.client.ejb.context", true);
        InitialContext ctx = new InitialContext(prop);

        QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
        Destination destination = (Destination) ctx.lookup(DESTINATION);
        connection = factory.createConnection(USERNAME, PASSWORD);
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(destination);
        connection.start();
        TextMessage message = session.createTextMessage("Hello world");
        producer.send(message);
        System.out.println("Message sent successfully to remote queue.");

    }
}




