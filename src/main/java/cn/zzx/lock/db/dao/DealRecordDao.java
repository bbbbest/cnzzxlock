package cn.zzx.lock.db.dao;

import cn.zzx.lock.db.po.DealRecord;

/**
 * The interface Deal record dao.
 *
 * @author fzh
 * @since 2017 /10/3
 */
public interface DealRecordDao {
    /**
     * 保存一条消费记录
     *
     * @param record the record
     * @throws Exception the exception
     */
    void save(DealRecord record) throws Exception;
}
