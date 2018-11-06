package org.sandrm.integr.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by sandr on 22.09.2018.
 */
public class MsgPublisher {
    public static final Logger LOG = Logger.getLogger(MsgPublisher.class.getName());

    private static String URL_BROKER = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final String TOPIC_NAME = "ext.myTopic";
    public static final boolean IS_TRANSACTED = false;  //depends on Java SE env or Java EE container

    Session session;
    MessageProducer messageProducer;

    public MsgPublisher() throws NamingException, JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL_BROKER);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //Crete non-transactional session, to use transactional to set first parameter "true"
        session = connection.createSession(IS_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        messageProducer = session.createProducer(topic);
//        TextMessage textMessage = session.createTextMessage("Test JMS Topic message! " + new Date().toString());
//
//        messageProducer.send(textMessage);
//        LOG.log(Level.INFO, "Message was sent to TOPIC: " + textMessage.getText());
//
//        connection.close();
//        LOG.log(Level.INFO, "Publisher Producer finished.");
    }


    public void input(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            String inputMessage = scanner.nextLine();
            TextMessage textMessage = null;
            try {
                textMessage = session.createTextMessage("NEW MESSAGE: " + inputMessage + " " + new Date().toString());
                messageProducer.send(textMessage);
                LOG.log(Level.INFO, "Message was sent to TOPIC: " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
