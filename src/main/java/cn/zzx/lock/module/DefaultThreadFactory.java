package cn.zzx.lock.module;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class DefaultThreadFactory implements ThreadFactory {
    private String threadPrefix = "Worker-";
    private AtomicInteger threadId;
    private ThreadGroup group;

    public DefaultThreadFactory(ThreadGroup group, String threadPrefix) {
        this.group = group;
        this.threadPrefix = threadPrefix;
    }

    public DefaultThreadFactory(ThreadGroup group) {
        this.group = group;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, threadPrefix + threadId.incrementAndGet());
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}
