package org.sandrm.integr.jms;

//import java.util.logging.Logger;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

/**
 * Created by sandr on 22.09.2018.
 */
public class MsgConsumer {
    public static final Logger LOG = Logger.getLogger(MsgConsumer.class.getName());

    private static String URL_LOCAL_JMS_SERVER = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String CONSUME_QUEUE = "ext.myQueue";

    public MsgConsumer() throws JMSException {
        LOG.info("MsgConsumer created.");

        ConnectionFactory connectionFactory =  new ActiveMQConnectionFactory(URL_LOCAL_JMS_SERVER);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //Get from this queue
        Destination destination = session.createQueue(CONSUME_QUEUE);
        MessageConsumer consumer = session.createConsumer(destination);

        //Receive message
        //By default this call is blocking, which means it will wait
        //for a message to arrive on the queue.
        Message message = consumer.receive();
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            LOG.info("There was received message '" + textMessage.getText() + "'");
        }

        connection.close();
    }
}
