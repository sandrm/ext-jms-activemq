package org.sandrm.integr.jms;

//import java.util.logging.Logger;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.io.IOException;


/**
 * Created by sandr on 22.09.2018.
 */
public class MsgSubscriber {    //implements Runnable {
    public static final Logger LOG = Logger.getLogger(MsgSubscriber.class.getName());

    private static String URL_LOCAL_JMS_SERVER = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final String TOPIC_NAME = "ext.myTopic";

    MessageConsumer consumer;
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL_LOCAL_JMS_SERVER);
    Connection connection = connectionFactory.createConnection();

    public MsgSubscriber(final String clientID, final String subscriber) throws JMSException {
        LOG.info("MsgConsumer created.");
        connection.setClientID(clientID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);
        consumer = session.createDurableSubscriber(topic, subscriber);    //TopicSubscriber

        MessageListener listener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;

                        LOG.info("Client: " + clientID + " Subcscriber: " + subscriber
                                + " RECEIVED MESSAGE FROM TOPIC:"  + textMessage.getText());
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };

        consumer.setMessageListener(listener);


//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        connection.close();
    }

    /*
    @Override
    public void run() {
        try {
            while (true) {

                //Receive message
                //By default this call is blocking, which means it will wait
                //for a message to arrive on the queue.
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;

                    LOG.info("There was received message '" + textMessage.getText() + "'");
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
    */
}
