package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.CyclingRecordDao;
import cn.zzx.lock.db.po.CyclingRecord;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/6
 */
@Repository("cyclingRecordDao")
public class CyclingRecordDaoImpl extends BaseDao implements CyclingRecordDao {

    @Override
    public CyclingRecord findUnFinishedByUserIdAndBicycleId(int userId, int bicycleId) throws Exception {
        String sql = "select * from cyclingrecord where userId=? and bicycleId=?";
        RowMapper<CyclingRecord> rowMapper = new BeanPropertyRowMapper<>(CyclingRecord.class);
        return getJdbcTemplate().queryForObject(sql, rowMapper, userId, bicycleId);
    }

    @Override
    public void save(CyclingRecord record) throws Exception {
        String sql = "insert into cyclingrecord(cyclingRecordId, bicycleId, userId, startTime, endTime, startLocX," +
                " startLocY, endLocX, endLocY) values(null,?,?,now(),null,?,?,null,null)";
        getJdbcTemplate().update(sql, record.getBicycleId(), record.getUserId(),
                record.getStartLocX(), record.getStartLocY());
    }

    @Override
    public void update(CyclingRecord record) throws Exception {
        String sql = "update cyclingrecord set bicycleId=?, userId=?, endTime=now()," +
                " endLocX=?, endLocY=? where cyclingRecordId=?";
        getJdbcTemplate().update(sql, record.getBicycleId(), record.getUserId(), record.getEndLocX(), record.getEndLocY(),
                record.getCyclingRecordId());
    }
}
