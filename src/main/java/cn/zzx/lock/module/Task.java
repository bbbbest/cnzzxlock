package cn.zzx.lock.module;

import cn.zzx.lock.protocol.*;
import cn.zzx.lock.service.CycleService;
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
    private CycleService service;

    Task(SelectionKey sk, CycleService service) {
        this.sk = sk;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = (SocketChannel) sk.channel();

            // whole packet will less than or equal to response packet's valid size.
            ByteBuffer buf = ByteBuffer.allocate(LockPacket.RESPONSE_VALID_SIZE);

            // valid connection
            if (sc.read(buf) > 0) {
                // turn to read mode
                buf.flip();
                byte[] data = new byte[buf.remaining()];
                buf.get(data);

                // create packet
                try {
                    Packet packet = Packet.of(data);
                    // recheck packet's validity
                    if (packet.isValid()) {
                        if (packet instanceof LockPacket) {
                            handleLock(sc, (LockPacket) packet);
                        } else {
                            handleUnlock(sc, (UnlockPacket) packet);
                        }
                    } else {
                        respond(sc, Response.CLOSE);
                    }
                } catch (UnresolvedPacketException e) {
                    respond(sc, Response.CLOSE);
                    logger.error(e.getMessage());
                }
            }
            sc.close();
        } catch (IOException e) {
            logger.error("Exception happened while execute real work.");
        }
    }

    /**
     * 处理上锁的业务逻辑
     * @param sc socket
     * @param packet packet
     * @throws IOException 写回信息出错
     */
    private void handleLock(SocketChannel sc, LockPacket packet) throws IOException {
        try {
            // try to lock in database
            service.lock(packet.getCardNum(), packet.getLockId(), packet.getLatitude(), packet.getLongitude(), packet.getEnergy());
            // success
            respond(sc, Response.CLOSE);
        } catch (Exception e) {
            respond(sc, Response.OPEN);
        }
    }

    /**
     * 处理解锁的业务逻辑
     * @param sc socket
     * @param packet packet
     * @throws IOException 写回信息出错
     */
    private void handleUnlock(SocketChannel sc, UnlockPacket packet) throws IOException {
        try {
            // try to unlock in database
            if (service.unlock(packet.getCardNum(), packet.getLockId())) {
                // success
                respond(sc, Response.OPEN);
            } else {
                // 一卡多用 或 一车多用
                respond(sc, Response.CLOSE);
            }
        } catch (Exception e) {
            respond(sc, Response.CLOSE);
        }
    }

    private void respond(SocketChannel channel, Response op) throws IOException {
        channel.write(ByteBuffer.wrap(op.getValue().getBytes()));
    }
}
