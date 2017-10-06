package cn.zzx.lock.service;

import cn.zzx.lock.db.po.Bicycle;
import cn.zzx.lock.db.po.CyclingRecord;
import cn.zzx.lock.db.po.DealRecord;
import cn.zzx.lock.db.po.User;

/**
 * 整个骑车过程的服务层
 *
 * @author fzh
 * @since 2017 /10/6
 * @version 1.0
 */
public interface CycleService {

    /**
     * 根据用户持有的骑行卡卡号查询用户
     *
     * @param card the card
     * @return 持有该卡的用户
     * @throws Exception the exception
     */
    User findByCardNum(int card) throws Exception;

    /**
     * 根据锁编号查询自行车
     *
     * @param lockId the lock id
     * @return 锁编号为该编号的自行车
     * @throws Exception the exception
     */
    Bicycle findByLockId(int lockId) throws Exception;

    /**
     * 插入一条骑行记录（开始骑车）
     *
     * @param record the record
     * @return 是否插入成功
     * @throws Exception the exception
     */
    boolean saveCyclingRecord(CyclingRecord record) throws Exception;

    /**
     * 插入一条消费记录（骑车结束）
     *
     * @param record the record
     * @return 是否插入成功
     * @throws Exception the exception
     */
    boolean saveDealRecord(DealRecord record) throws Exception;

}
