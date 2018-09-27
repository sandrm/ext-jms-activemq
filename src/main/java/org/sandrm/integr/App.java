package org.sandrm.integr;

import org.apache.log4j.BasicConfigurator;
import org.sandrm.integr.jms.*;

import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class App {
    public static void main(String[] args) {

        System.out.println("Test JMS App! Start!");

        try {
            //BasicConfigurator.configure();
/*
            new MsgObjectProducer();

            Thread.sleep(5000);

            Thread threadConsumer = new Thread(new MsgObjectConsumer());
            threadConsumer.start();

*/
            ////new MsgPublisher();

            new MsgSubscriber("Client A", "SUB1234");

            new MsgSubscriber("Client B", "SUB56789");

        } catch (JMSException e) {
            e.printStackTrace();
///        } catch (NamingException e) {
///            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        }

        System.out.println("Test JMS App! Done!");
    }
}
