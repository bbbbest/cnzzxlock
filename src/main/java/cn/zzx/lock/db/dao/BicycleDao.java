package cn.zzx.lock.db.dao;

import cn.zzx.lock.db.po.Bicycle;

/**
 * 自行车信息的DAO
 *
 * @author fzh
 * @since 2017 /10/3
 */
public interface BicycleDao {
    /**
     * 根据锁编号查询自行车
     *
     * @param lockId the lock id
     * @return the bicycle
     * @throws Exception the exception
     */
    Bicycle findByLockId(long lockId) throws Exception;

    /**
     * 根据自行车信息，设置：自行车当前位置、电量信息
     *
     * @param bicycle the bicycle
     * @throws Exception the exception
     */
    void updateLocationAndEnergyByLockId(Bicycle bicycle) throws Exception;
}
