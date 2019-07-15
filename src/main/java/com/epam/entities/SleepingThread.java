package com.epam.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class SleepingThread implements Runnable {
    Logger logger = LoggerFactory.getLogger(SleepingThread.class);

    @Override
    public void run() {
        try {
            logger.info("I sleep 5 seconds");
            sleep(5000) ;
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
    }
}
