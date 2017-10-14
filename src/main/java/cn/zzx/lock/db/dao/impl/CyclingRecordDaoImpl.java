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
        String sql = "SELECT * FROM cyclingrecord WHERE date_sub(startTime, INTERVAL 10 MINUTE) = endTime AND (userId = ? AND bicycleId = ?)";
        RowMapper<CyclingRecord> rowMapper = new BeanPropertyRowMapper<>(CyclingRecord.class);
        return getJdbcTemplate().queryForObject(sql, rowMapper, userId, bicycleId);
    }

    @Override
    public CyclingRecord findUnFinishedByUserIdOrBicycleId(int userId, int bicycleId) throws Exception {
        String sql = "SELECT * FROM cyclingrecord WHERE date_sub(startTime, INTERVAL 10 MINUTE) = endTime AND (userId = ? OR bicycleId = ?)";
        RowMapper<CyclingRecord> rowMapper = new BeanPropertyRowMapper<>(CyclingRecord.class);
        return getJdbcTemplate().queryForObject(sql, rowMapper, userId, bicycleId);
    }

    @Override
    public void save(CyclingRecord record) throws Exception {
        String sql = "INSERT INTO cyclingrecord (cyclingRecordId, bicycleId, userId, startTime, endTime, startLocX," +
                " startLocY, endLocX, endLocY) VALUES(?,?,?,now(),date_sub(now(), INTERVAL 10 MINUTE),?,?,-1.1,-1.1)";
        getJdbcTemplate().update(sql, record.getCyclingRecordId(), record.getBicycleId(), record.getUserId(),
                record.getStartLocX(), record.getStartLocY());
    }

    @Override
    public void update(CyclingRecord record) throws Exception {
        String sql = "UPDATE cyclingrecord SET endTime=?, endLocX=?, endLocY=? WHERE cyclingRecordId=?";
        getJdbcTemplate().update(sql, record.getEndTime(), record.getEndLocX(), record.getEndLocY(),
                record.getCyclingRecordId());
    }
}
