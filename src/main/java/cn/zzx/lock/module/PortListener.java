package cn.zzx.lock.module;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author fzh
 * @since 2017/10/3
 */
@Component("portListener")
public class PortListener extends Thread {
    private ServerSocketChannel channel;
    private Selector selector;
    private SelectionKey sk;
    private String ip;
    private Integer port;

    private PortListener() {

    }

    public PortListener(String ip, Integer port) throws IOException {
        this.ip = ip;
        this.port = port;
    }

    public void initChannel() {
        // TODO
    }

    public void listen() {
        // TODO
    }

    @Override
    public void run() {
        // TODO
        listen();
        // TODO
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
