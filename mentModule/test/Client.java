import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by muu on 2015/6/2.
 */
public class Client
{
    public static void main(String[] args) throws Exception
    {
        QueueConnection cnn = null;
        QueueSender sender = null;
        QueueSession session = null;
        Properties prop = new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        prop.put(Context.PROVIDER_URL, "remote://localhost:4447");
        prop.put(Context.SECURITY_PRINCIPAL, "muu");
        prop.put(Context.SECURITY_CREDENTIALS, "Hyrac0therium_");
        prop.put("jboss.naming.client.ejb.context", true);
        InitialContext ctx = new InitialContext(prop);
        Queue queue = (Queue) ctx.lookup("queue/test");
        QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("java:jboss/exported/jms/RemoteConnectionFactory");
        cnn = factory.createQueueConnection();
        session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

        TextMessage msg = session.createTextMessage("Hello World");

        sender = session.createSender(queue);
        sender.send(msg);
        System.out.println("Message sent successfully to remote queue.");

    }
}




