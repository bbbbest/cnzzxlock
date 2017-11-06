package cn.zzx.lock.module;

import cn.zzx.lock.db.po.Bicycle;
import cn.zzx.lock.db.po.Config;
import cn.zzx.lock.db.po.CyclingRecord;
import cn.zzx.lock.db.po.User;
import cn.zzx.lock.protocol.*;
import cn.zzx.lock.service.CycleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Optional;

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
          AbstractPacket packet = AbstractPacket.of(data);
          // recheck packet's validity
          if (packet.isValid()) {
            if (packet instanceof LockPacket) {
              handleLock(sc, (LockPacket) packet);
            } else if (packet instanceof UnlockPacket) {
              handleUnlock(sc, (UnlockPacket) packet);
            } else {
              updateConfig((ConfigPacket) packet);
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
   *
   * @param sc     socket
   * @param packet packet
   * @throws IOException 写回信息出错
   */
  private void handleLock(SocketChannel sc, LockPacket packet) throws IOException {
    Response response;
    try {
      Optional<Object[]> uab = service.findUserAndBicycleByCNumAndLId(packet.getCardNum(), packet.getLockId());
      if (uab.isPresent()) {
        // user and bicycle exist
        Object[] ub = uab.get();
        User u = (User) ub[0];
        Bicycle b = (Bicycle) ub[1];
        Optional<CyclingRecord> cr = service.findCyclingRecordByAll(u, b);
        if (cr.isPresent()) {
          // running
          service.lock(u, b, cr.get(), packet.getLongitude(), packet.getLatitude(), packet.getEnergy());
          response = Response.CLOSE;
        } else {
          // stopped
          response = Response.ERROR;
        }
      } else {
        // user or bicycle not exist
        response = Response.ERROR;
      }
    } catch (Exception e) {
      // lock failed
      response = Response.OPEN;
    }
    respond(sc, response);
  }

  /**
   * 处理解锁的业务逻辑
   *
   * @param sc     socket
   * @param packet packet
   * @throws IOException 写回信息出错
   */
  private void handleUnlock(SocketChannel sc, UnlockPacket packet) throws IOException {
    Response response;
    try {
      Optional<Object[]> uab = service.findUserAndBicycleByCNumAndLId(packet.getCardNum(), packet.getLockId());
      if (uab.isPresent()) {
        // user and bicycle exist
        Object[] ub = uab.get();
        User u = (User) ub[0];
        Bicycle b = (Bicycle) ub[1];
        Optional<CyclingRecord> cr = service.findCyclingRecordByOne(u, b);
        if (!cr.isPresent()) {
          // stopped
          if (service.unlock(u, b)) {
            // unlock success
            response = Response.OPEN;
          } else {
            response = Response.CLOSE;
          }
        } else {
          CyclingRecord record = cr.get();
          // 当前用户已在骑行状态，此时又刷了别的自行车
          if (record.getBicycleId().equals(b.getBicycleId())) {
            response = Response.OPEN;
          } else {
            response = Response.ERROR;
          }
        }
      } else {
        // user or bicycle not exist
        response = Response.ERROR;
      }
    } catch (Exception e) {
      // unlock failed
      response = Response.CLOSE;
    }
    respond(sc, response);
  }

  private void updateConfig(ConfigPacket packet){
    Config.getInstance().setTariff(packet.getTariff());
  }

  private void respond(SocketChannel channel, Response op) throws IOException {
    channel.write(ByteBuffer.wrap(op.getValue().getBytes()));
  }
}
