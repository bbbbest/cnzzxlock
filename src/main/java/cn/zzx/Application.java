package cn.zzx;

import cn.zzx.lock.module.PortListener;
import cn.zzx.lock.module.ThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fzh
 * @since 2017/10/3
 */
public final class Application {
    // Spring Context
    private static final Logger logger = LogManager.getLogger(Application.class);
    private static ApplicationContext context;
    private static PortListener portListener;
    private static ThreadPool threadPool;
    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        portListener = (PortListener) context.getBean("portListener");
        threadPool = (ThreadPool) context.getBean("threadPool");
        portListener.start();
        logger.info("Server started.");
    }
}
