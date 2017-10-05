package cn.zzx.lock.module;

import cn.zzx.lock.protocol.RequestPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class Task implements Runnable {
    private static Logger logger = LogManager.getLogger(Task.class);
    private SelectionKey sk;

    public Task(SelectionKey sk) {
        this.sk = sk;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = (SocketChannel) sk.channel();

            // whole packet will less than or equal to 82 bytes.
            ByteBuffer buf = ByteBuffer.allocate(RequestPacket.MAXSIZE);

            // valid connection
            if (sc.read(buf) > 0) {
                // turn to read mode
                buf.flip();
                byte[] data = new byte[buf.remaining()];
                buf.get(data);

                // create packet

                // do some query and get result

                // write reply data

            }
            sc.close();
        } catch (IOException e) {
            logger.error("Exception happened while execute real work.");
            e.printStackTrace();
        }
    }

    public boolean valid(){return true;}
}
