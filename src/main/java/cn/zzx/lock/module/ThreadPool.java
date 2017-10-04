package cn.zzx.lock.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

/**
 * @author fzh
 * @since 2017/10/4
 */
public class ThreadPool {
    private static Logger logger = LogManager.getLogger(ThreadPool.class);
    private ThreadPoolExecutor realExecutor;
    private int coreSize;
    private int maxSize;
    private int waitTime;
    private TimeUnit waitTimeUnit;
    protected ThreadGroup threadGroup;
    protected String groupName;
    protected ThreadFactory threadFactory;
    protected BlockingQueue<Runnable> blockingQueue;

    public ThreadPool() {
        coreSize = 5;
        maxSize = 20;
        waitTime = 60;
        waitTimeUnit = TimeUnit.SECONDS;
        groupName = "WorkerGroup";
        threadGroup = new ThreadGroup(groupName);
        threadFactory = new DefaultThreadFactory(threadGroup);
        blockingQueue = new LinkedBlockingQueue<>();
        realExecutor = new ThreadPoolExecutor(coreSize, maxSize, waitTime, waitTimeUnit, blockingQueue, threadFactory);
        init();
    }

    protected void init() {
    }

    protected void preExecute() throws Exception {
    }

    protected void afterExecute() throws Exception {
    }

    public void execute(Runnable task) {
        try {
            preExecute();
        } catch (Exception e) {
            logger.error("Error on preExecute task.", e);
        }
        realExecutor.execute(task);
        try {
            afterExecute();
        } catch (Exception e) {
            logger.error("Error on afterExecute task.", e);
        }
    }

    protected void preSubmit() throws Exception {
    }

    protected void afterSubmit() throws Exception {
    }
    public <T> Future<T> submit(Callable<T> task) {
        Future<T> future = null;
        try {
            preSubmit();
        } catch (Exception e) {
            logger.error("Error on preSubmit task.", e);
        }
        future = realExecutor.submit(task);
        try {
            afterSubmit();
        } catch (Exception e) {
            logger.error("Error on afterSubmit task.", e);
        }
        return future;
    }

    /**
     * 关闭线程池
     */
    public void close() {
        realExecutor.shutdown();
    }


    public int getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
        realExecutor.setCorePoolSize(coreSize);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        realExecutor.setMaximumPoolSize(maxSize);
    }

    public void setWaitTime(int waitTime, TimeUnit waitTimeUnit) {
        this.waitTime = waitTime;
        this.waitTimeUnit = waitTimeUnit;
        realExecutor.setKeepAliveTime(waitTime, waitTimeUnit);
    }

    public int getPoolSize() {
        return realExecutor.getPoolSize();
    }

    protected ThreadPoolExecutor getRealExecutor() {
        return realExecutor;
    }

    public boolean isShutdown(){
        return realExecutor.isTerminated();
    }

    public int getActiveCount(){
        return realExecutor.getActiveCount();
    }
}
