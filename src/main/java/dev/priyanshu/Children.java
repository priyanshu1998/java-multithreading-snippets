package dev.priyanshu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.function.Supplier;

public class Children {
    public static final Logger logger = LogManager.getLogger();

    private  static final Thread throwIllegalStateExceptionThread = new Thread(()->{
        var t = Thread.currentThread();
        ThreadContext.put("threadType", "worker");
        logger.info("Name: {}", t.getName());
        logger.info("ID: {}", t.threadId());
        throw new IllegalStateException("Invalid state");
    });


    public static Supplier<Thread> throwIllegalStateExceptionThreadSupplier(){
        var thread = throwIllegalStateExceptionThread;

        thread.setName("child");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler((t,e)->{
            logger.error("A exception has occurred on tid: {}", t.threadId());
            logger.error("Message: ", e);
        });

        return ()-> thread;
    }



}
