package org.sandrm.integr.utils;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sandr on 23.09.2018.
 */
public class MyUtils {
    public static final Logger LOG = Logger.getLogger(MyUtils.class.getName());

    public static Integer generateID(){
//        long highbits = myuuid.getMostSignificantBits();
//        long lowbits = myuuid.getLeastSignificantBits();
//
//        LOG.log(Level.INFO,"My UUID is: " + highbits + " " + lowbits);

        Random random = new Random();
        return random.nextInt();
    }
}
