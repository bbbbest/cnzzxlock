package cn.zzx;

import cn.zzx.lock.module.PortListener;
import cn.zzx.lock.module.ThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fzh
 * @since 2017/10/3
 */
public final class Application {
    // Spring Context
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PortListener portListener = (PortListener) context.getBean("portListener");
        ThreadPool threadPool = (ThreadPool) context.getBean("threadPool");
        portListener.start();
        context.registerShutdownHook();
        logger.info("Server started.");
    }
}
