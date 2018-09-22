package org.sandrm.integr.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * Created by sandr on 22.09.2018.
 */
public class MsgProducer {

    public static final boolean IS_TRANSACTED = false;  //depends on Java SE env or Java EE container

    public MsgProducer() throws NamingException, JMSException {
        //Get a JNDI connection
        //InitialContext jndi = new InitialContext();
        //Look up connection factory (jndi) ?
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

        //Get connection from server and start
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //Crete non-transactional session, to use transactional to set first parameter "true"
        Session session = connection.createSession(IS_TRANSACTED, Session.AUTO_ACKNOWLEDGE);

        //Destination destination = (Destination) jndi.lookup("queue.myQueue");
        Queue queueDestination = session.createQueue("ext.myQueue");
        MessageProducer messageProducer = session.createProducer(queueDestination);
        TextMessage textMessage = session.createTextMessage("Test JMS message!");

        messageProducer.send(textMessage);
        System.out.print("Message was sent: " + textMessage.getText());

        connection.close();
    }
}
