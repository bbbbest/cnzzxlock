package cn.zzx.lock.module;

import cn.zzx.lock.protocol.Packet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class Task implements Runnable {
    private static Logger logger = LogManager.getLogger(Task.class);
    private SocketChannel client;

    public Task(SocketChannel client) {
        this.client = client;
    }

    @Override
    public void run() {
        // whole packet will less than or equal to 82 bytes.
        ByteBuffer buf = ByteBuffer.allocate(82);
        try {
            client.read(buf);
            Packet packet = new Packet(buf.array());
            if (packet.isValid()) {
                // do some work
                // TODO
            }
        } catch (IOException e) {
            logger.error("Exception happened while execute real work.");
            e.printStackTrace();
        }
    }
}
