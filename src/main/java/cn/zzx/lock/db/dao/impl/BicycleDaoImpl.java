package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.BicycleDao;
import cn.zzx.lock.db.po.Bicycle;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/6
 */
@Repository("bicycleDao")
public class BicycleDaoImpl extends BaseDao implements BicycleDao {
    @Override
    public Bicycle findByLockId(long lockId) throws Exception {
        String sql = "select * from bicycle where id=?";
        RowMapper<Bicycle> rowMapper = new BeanPropertyRowMapper<>(Bicycle.class);
        return getJdbcTemplate().queryForObject(sql, rowMapper, lockId);
    }

    @Override
    public void updateLocationAndEnergyByLockId(Bicycle bicycle) throws Exception {
        String sql = "update bicycle set locationX=?, locationY=?, energy=? where lockId=?";
        getJdbcTemplate().update(sql, bicycle.getLocationX(), bicycle.getLocationY(), bicycle.getLockId());
    }
}
