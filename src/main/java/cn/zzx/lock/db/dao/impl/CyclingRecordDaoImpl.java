package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.CyclingRecordDao;
import cn.zzx.lock.db.po.CyclingRecord;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/6
 */
@Repository("cyclingRecordDao")
public class CyclingRecordDaoImpl extends BaseDao implements CyclingRecordDao {

    @Override
    public CyclingRecord findUnFinishedByUserIdAndBicycleId(int userId, int bicycleId) throws Exception {
        return null;
    }

    @Override
    public void save(CyclingRecord record) throws Exception {

    }

    @Override
    public void update(CyclingRecord record) throws Exception {

    }
}
