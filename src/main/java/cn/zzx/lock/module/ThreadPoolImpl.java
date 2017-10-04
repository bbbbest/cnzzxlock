package cn.zzx.lock.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author fzh
 * @since 2017/10/3
 */
@Component("threadPool")
public class ThreadPoolImpl extends ThreadPool {
    private static Logger logger = LogManager.getLogger(ThreadPoolImpl.class);
}
