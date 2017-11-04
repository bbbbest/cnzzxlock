package cn.zzx.lock.module;

import cn.zzx.lock.service.CycleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * 这个类的作用是为了监听端口并分发端口进来的数据包给线程池除了
 *
 * @author fzh
 * @since 2017/10/3
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
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

  @Resource(name = "threadPool")
  private ThreadPool pool;

  @Resource(name = "cycleService")
  private CycleService service;

  private void init() {
    try {
      server = ServerSocketChannel.open();
      server.configureBlocking(false);
      server.bind(new InetSocketAddress(port), maxConnections);
      selector = SelectorProvider.provider().openSelector();
      server.register(selector, SelectionKey.OP_ACCEPT);
      status = READY;
      listener = new Thread(this, "PortListener");
      listener.setUncaughtExceptionHandler((t, e) -> {
        logger.error("Uncaught Exception happened in PortListener", e);
        status = DESTROYED;
      });
      logger.info("PortListener has completed initialization. port: " + port);
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
        selector.select();
        Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
        SelectionKey sk;
        while (iter.hasNext()) {
          sk = iter.next();
          iter.remove();
          if (sk.isValid()) {
            if (sk.isAcceptable()) {
              doAccept(sk);
            }
            if (sk.isReadable()) {
              doRead(sk);
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    logger.info("PortListener stop to listen.");
    if (selector != null) {
      try {
        selector.close();
        server.close();
        status = DESTROYED;
      } catch (IOException e) {
        logger.error("In PortListener: exception happened while selector closed.");
        e.printStackTrace();
      }
    }
  }

  private void doAccept(SelectionKey sk) {
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

  private void doRead(SelectionKey sk) {
    // cancel next read event
    sk.cancel();
    // delegate to thread pool to execute
    pool.execute(new Task(sk, service));
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
