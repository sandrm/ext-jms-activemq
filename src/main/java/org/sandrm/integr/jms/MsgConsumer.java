package org.sandrm.integr.jms;

//import java.util.logging.Logger;
import org.apache.log4j.Logger;

/**
 * Created by sandr on 22.09.2018.
 */
public class MsgConsumer {
    public static final Logger LOG = Logger.getLogger(MsgProducer.class.getName());

    public MsgConsumer() {
        LOG.info("MsgConsumer created. ");
        LOG.info("This is branch Feature_2 ");
    }
}
