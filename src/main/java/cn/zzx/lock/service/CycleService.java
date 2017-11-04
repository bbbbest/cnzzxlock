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

    /**
     * Find user and bicycle by c num and l id optional.
     *
     * @param cardNum the card num
     * @param lockId  the lock id
     * @return the optional
     */
    Optional<Object[]> findUserAndBicycleByCNumAndLId(int cardNum, int lockId);

    /**
     * Find cycling record by all optional.
     *
     * @param user    the user
     * @param bicycle the bicycle
     * @return the optional
     */
    Optional<CyclingRecord> findCyclingRecordByAll(User user, Bicycle bicycle);

    /**
     * Find cycling record by one optional.
     *
     * @param user    the user
     * @param bicycle the bicycle
     * @return the optional
     */
    Optional<CyclingRecord> findCyclingRecordByOne(User user, Bicycle bicycle);

    /**
     * Lock boolean.
     *
     * @param user          the user
     * @param bicycle       the bicycle
     * @param cyclingRecord the cycling record
     * @param locX          the loc x
     * @param locY          the loc y
     * @param energy        the energy
     * @return the boolean
     * @throws Exception the exception
     */
    boolean lock(User user, Bicycle bicycle, CyclingRecord cyclingRecord, double locX, double locY, float energy) throws Exception;

    /**
     * Unlock boolean.
     *
     * @param user    the user
     * @param bicycle the bicycle
     * @return the boolean
     * @throws Exception the exception
     */
    boolean unlock(User user, Bicycle bicycle) throws Exception;
}
