package cn.zzx;

import cn.zzx.lock.db.dao.UserDao;
import cn.zzx.lock.db.dao.impl.UserDaoImpl;
import cn.zzx.lock.module.PortListener;
import cn.zzx.lock.module.ThreadPool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fzh
 * @since 2017/10/3
 */
public final class Application {
    // Spring Context
    private static ApplicationContext context;
    private static PortListener portListener;
    private static ThreadPool threadPool;
    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        portListener = (PortListener) context.getBean("portListener");
//        threadPool = (ThreadPool) context.getBean("threadPool");
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.print();
    }
}
