package dev.priyanshu;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class Main {

    public static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        var parent = Thread.currentThread();
        ThreadContext.put("threadType", "main");
        logger.info("Name: {}", parent.getName());
        logger.info("ID: {}", parent.threadId());


        var child = Children.throwIllegalStateExceptionThreadSupplier().get();

        try {
            logger.info("Running before thread start");
            child.start();
            child.join();
            logger.info("Running after thread start");
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted", e);
        }

        logger.info("Exiting");
        ThreadContext.clearMap();
    }
}