package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            logger.info("I am the new Thread: "+Thread.currentThread().getName());
            logger.info("Has priority: "+Thread.currentThread().getPriority());
        });


        thread.setName("newWorkerThread");
        thread.setPriority(Thread.MAX_PRIORITY);

        logger.info("Running before thread start");
        thread.start();
        logger.info("Running after thread start");

        Thread.sleep(10000);
        logger.info("Will exit now");
    }
}