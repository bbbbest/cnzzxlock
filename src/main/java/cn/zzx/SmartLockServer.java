package cn.zzx;

import cn.zzx.lock.module.PortListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author fzh
 * @since 2017/10/3
 */
public final class SmartLockServer {
    private static Logger logger = LogManager.getLogger(SmartLockServer.class);
    private static ClassPathXmlApplicationContext context;
    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PortListener portListener = (PortListener) context.getBean("portListener");
        portListener.start();
        context.registerShutdownHook();
        registerExitHandler();
        logger.info("Server started.");
    }

    private static void registerExitHandler(){
        SignalHandler sh = signal -> {
            if (context.isRunning()) {
                System.out.println("closed in SignalHandler.");
                context.close();
                System.exit(0);
            }
        };
        Signal.handle(new Signal("INT"), sh);
        Signal.handle(new Signal("TERM"), sh);
    }
}
