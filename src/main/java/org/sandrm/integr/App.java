package org.sandrm.integr;

import org.apache.log4j.BasicConfigurator;
import org.sandrm.integr.jms.MsgConsumer;
import org.sandrm.integr.jms.MsgObjectConsumer;
import org.sandrm.integr.jms.MsgObjectProducer;
import org.sandrm.integr.jms.MsgProducer;

import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class App {
    public static void main(String[] args) {

        System.out.println("Test JMS App! Start!");

        try {
            //BasicConfigurator.configure();
            new MsgObjectProducer();

            Thread.sleep(5000);

            Thread threadConsumer = new Thread(new MsgObjectConsumer());
            threadConsumer.start();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Test JMS App! Done!");
    }
}
