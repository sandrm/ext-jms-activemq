package org.sandrm.integr.jms.sync;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.qpid.proton.engine.Sender;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by sandr on 22.09.2018.
 */
public class MsgSyncProducer {
    public static final Logger LOG = Logger.getLogger(MsgSyncProducer.class.getName());

    public static final boolean IS_TRANSACTED = false;  //depends on Java SE env or Java EE container
    public static final String QUEUE_NAME = "ext.myQueue";

    public MsgSyncProducer() throws NamingException, JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(IS_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
        Queue queueDestination = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queueDestination);
        TextMessage textMessage = session.createTextMessage("Test JMS message ! " + new Date().toString());

        for (int i = 0; i < 10; i++) {
            textMessage.setText("This is message " + (i + 1) + " from producer");
            System.out.println("Sending message: " + textMessage.getText());
            messageProducer.send(textMessage);
        }
        //Sends an empty control message to indicate the end of the message stream:
        messageProducer.send(session.createMessage());
        LOG.log(Level.INFO, "Message was sent: " + textMessage.getText());

        connection.close();
        LOG.log(Level.INFO, "Producer finished.");


    }
}
