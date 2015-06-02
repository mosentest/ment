package hemu.ment.core.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by muu on 2015/6/2.
 */
@MessageDriven(name = "MessageMDBSample", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TestMDB implements MessageListener {

    public void onMessage(Message message) {

        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("Received message " + tm.getText());
        } catch (JMSException e) {

            e.printStackTrace();
        }


    }
}