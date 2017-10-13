package cn.zzx.lock.service;

import cn.zzx.lock.db.po.Bicycle;
import cn.zzx.lock.db.po.CyclingRecord;
import cn.zzx.lock.db.po.User;

import java.util.Optional;

/**
 * 整个骑车过程的服务层
 *
 * @author fzh
 * @version 1.0
 * @since 2017 /10/6
 */
public interface CycleService {

    Optional<Object[]> findUserAndBicycleByCNumAndLId(int cardNum, int lockId);

    Optional<CyclingRecord> findCyclingRecord(User user, Bicycle bicycle);

    boolean lock(User user, Bicycle bicycle, CyclingRecord cyclingRecord, double locX, double locY, float energy) throws Exception;

    boolean unlock(User user, Bicycle bicycle) throws Exception;
}
