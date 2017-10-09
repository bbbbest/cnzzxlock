package cn.zzx.lock.db.dao;

import cn.zzx.lock.db.po.CyclingRecord;

/**
 * The interface Cycling record dao.
 *
 * @author fzh
 * @since 2017 /10/3
 */
public interface CyclingRecordDao {
    /**
     * 根据用户编号和自行车编号找到一条未完成的骑行记录，判断依据：结束时间是否为开始时间的前 1 min
     *
     * @param userId the user id
     * @return the cycling record
     * @throws Exception the exception
     */
    CyclingRecord findUnFinishedByUserId(int userId) throws Exception;
    CyclingRecord findUnFinishedByBicycleId(int bicycleId) throws Exception;

    /**
     * 保存骑行记录，设置：结束时间为开始时间前 1 min
     *
     * @param record the record
     * @throws Exception the exception
     */
    void save(CyclingRecord record) throws Exception;

    /**
     * 更新骑行记录信息，设置：结束时间、结束时坐标
     *
     * @param record the record
     * @throws Exception the exception
     */
    void update(CyclingRecord record) throws Exception;
}
