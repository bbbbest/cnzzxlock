package cn.zzx.lock.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class PortListener implements Runnable {
    private static Logger logger = LogManager.getLogger(PortListener.class);
    public static final Byte READY = 1;
    public static final Byte RUNNING = 1 << 1;
    public static final Byte DESTROYED = 1 << 2;
    private Thread listener;
    private Byte status;
    private Integer port;
    private Integer maxConnections;
    private volatile boolean stop;
    private ServerSocketChannel server;
    private Selector selector;

    @Autowired
    private ThreadPool pool;

    private void init() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port), maxConnections);
            selector = SelectorProvider.provider().openSelector();
            server.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("PortListener has completed initialization.");
            status = READY;
            listener = new Thread(this, "PortListener");
            listener.setUncaughtExceptionHandler((t, e) -> {
                logger.error("Uncaught Exception happened in PortListener", e);
                status = DESTROYED;
            });
        } catch (IOException e) {
            logger.error("PortListener has not completed initialization. Because of: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void stop() {
        this.stop = true;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isStop() {
        return stop;
    }

    public void start() {
        listener.start();
    }

    @Override
    public void run() {
        status = RUNNING;
        while (!stop) {
            try {
                selector.select(1000);
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey sk = iter.next();
                    iter.remove();
                    if (sk.isValid()) {
                        if (sk.isAcceptable()) {
                            doAccept(sk);
                        }
                        if (sk.isReadable()) {
                            // delegate to thread pool to execute
                            pool.execute(new Task((SocketChannel) sk.channel()));
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
                status = DESTROYED;
            }
            catch (IOException e) {
                logger.error("In PortListener: exception happened while selector closed.");
                e.printStackTrace();
            }
        }
    }

    private void doAccept(SelectionKey sk){
        ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
        SocketChannel sc;
        try {
            sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            logger.error("In PortListener: exception happened while server wait to accept requests.");
            sk.cancel();
            e.printStackTrace();
        }
    }

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
    }

    public Byte getStatus() {
        return status;
    }
}
