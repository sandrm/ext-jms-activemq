package org.sandrm.integr.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.sandrm.integr.model.SomeObject;
import org.sandrm.integr.utils.MyUtils;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by sandr on 22.09.2018.
 */
public class MsgObjectProducer {
    public static final Logger LOG = Logger.getLogger(MsgObjectProducer.class.getName());

    public static final boolean IS_TRANSACTED = false;  //depends on Java SE env or Java EE container
    public static final String QUEUE_NAME = "ext.myQueue";

    public MsgObjectProducer() throws NamingException, JMSException {
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
        Queue queueDestination = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queueDestination);

        SomeObject object = new SomeObject();
        object.setId(MyUtils.generateID());
        object.setDescription("This is Object message to transfer by JMS" + new Date().toString());
        ObjectMessage objectMessage = session.createObjectMessage(object);

        messageProducer.send(objectMessage);
        LOG.log(Level.INFO, "Message was sent: " + object.toString());

        connection.close();
        LOG.log(Level.INFO, "Producer finished.");


    }
}
