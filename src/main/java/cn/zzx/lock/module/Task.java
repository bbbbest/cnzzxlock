package cn.zzx.lock.module;

import cn.zzx.lock.protocol.OperateType;
import cn.zzx.lock.protocol.RequestPacket;
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

            // whole packet will less than or equal to 82 bytes.
            ByteBuffer buf = ByteBuffer.allocate(RequestPacket.MAXSIZE);

            // valid connection
            if (sc.read(buf) > 0) {
                // turn to read mode
                buf.flip();
                byte[] data = new byte[buf.remaining()];
                buf.get(data);
                // create packet
                RequestPacket packet = new RequestPacket(data);
                if (packet.isValid()) {
                    RequestPacket.Body body = packet.getBody();
                    byte op = Byte.parseByte(body.getOperationType());
                    if (op == OperateType.LOCK.getValue()) {
                        // ask to lock
                        Integer card = Integer.valueOf(body.getCardNum());
                        Integer lockId = Integer.valueOf(body.getLockId());
                        Double locX = Double.valueOf(body.getLocX());
                        Double locY = Double.valueOf(body.getLocY());
                        Float energy = Float.valueOf(body.getEnergy());
                        try {
                            if (service.lock(card,lockId,locX,locY, energy)) {
                                respond(sc, OperateType.LOCK);
                            } else {
                                respond(sc, OperateType.UNLOCK);
                            }
                        } catch (Exception e) {
                            respond(sc, OperateType.UNLOCK);
                        }
                    } else {
                        // ask to unlock
                        Integer card = Integer.valueOf(body.getCardNum());
                        Integer lockId = Integer.valueOf(body.getLockId());
                        try {
                            if (service.unlock(card, lockId)) {
                                respond(sc, OperateType.UNLOCK);
                            } else {
                                respond(sc, OperateType.LOCK);
                            }
                        } catch (Exception e) {
                            respond(sc, OperateType.LOCK);
                        }
                    }
                } else {
                    respond(sc, OperateType.LOCK);
                }
            }
            sc.close();
        } catch (IOException e) {
            logger.error("Exception happened while execute real work.");
            e.printStackTrace();
        }
    }

    private void respond(SocketChannel channel, OperateType op) throws IOException {
        channel.write(ByteBuffer.wrap(new byte[]{op.getValue()}));
    }
}
