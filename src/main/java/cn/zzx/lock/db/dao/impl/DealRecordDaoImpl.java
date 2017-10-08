package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.DealRecordDao;
import cn.zzx.lock.db.po.DealRecord;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/6
 */
@Repository("dealRecordDao")
public class DealRecordDaoImpl extends BaseDao implements DealRecordDao {
    @Override
    public void save(DealRecord record) throws Exception {
        String sql = "insert into dealrecord(dealRecordId, userId, actionType, money, actionTime) " +
                "values(null,?,?,?,now())";
        getJdbcTemplate().update(sql, record.getUserId(), record.getActionType(), record.getMoney());
    }
}
