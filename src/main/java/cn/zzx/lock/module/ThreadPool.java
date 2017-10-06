package cn.zzx.lock.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    private ThreadGroup threadGroup;
    private ThreadFactory threadFactory;

    public ThreadPool() {
        coreSize = 5;
        maxSize = 10;
        waitTime = 60;
    }

    private void init() {
        waitTimeUnit = TimeUnit.SECONDS;
        String groupName = "WorkerGroup";
        threadGroup = new ThreadGroup(groupName);
        threadFactory = new DefaultThreadFactory(threadGroup);
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        realExecutor = new ThreadPoolExecutor(coreSize, maxSize, waitTime, waitTimeUnit, blockingQueue, threadFactory);
        logger.info("ThreadPool has completed initialization.");
    }

    protected void preExecute() throws Exception {
    }

    protected void afterExecute() throws Exception {
    }

    public void execute(Task task) {
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

    public void shutdown() {
        realExecutor.shutdown();
        logger.info("ThreadPool has been shutdown.");
    }

    public int getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
        if (realExecutor != null) realExecutor.setCorePoolSize(coreSize);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        if (realExecutor != null) realExecutor.setMaximumPoolSize(maxSize);
    }

    public void setWaitTime(int waitTime, TimeUnit waitTimeUnit) {
        this.waitTime = waitTime;
        this.waitTimeUnit = waitTimeUnit;
        realExecutor.setKeepAliveTime(waitTime, waitTimeUnit);
    }

    public int getPoolSize() {
        return realExecutor.getPoolSize();
    }

    public boolean isShutdown() {
        return realExecutor.isTerminated();
    }

    public int getActiveCount() {
        return realExecutor.getActiveCount();
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    private class DefaultThreadFactory implements ThreadFactory {
        private String threadPrefix;
        private AtomicInteger threadId;
        private ThreadGroup group;

        DefaultThreadFactory(ThreadGroup group, String threadPrefix) {
            this.group = group;
            this.threadPrefix = threadPrefix;
            this.threadId = new AtomicInteger(0);
        }

        DefaultThreadFactory(ThreadGroup group) {
            this(group, "Worker-");
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, threadPrefix + threadId.incrementAndGet());
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }
}
