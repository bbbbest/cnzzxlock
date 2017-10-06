package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.BicycleDao;
import cn.zzx.lock.db.po.Bicycle;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/6
 */
@Repository("bicycleDao")
public class BicycleDaoImpl extends BaseDao implements BicycleDao {
    @Override
    public Bicycle findByLockId(int lockId) throws Exception {
        return null;
    }

    @Override
    public void updateLocationAndEnergyByLockId(Bicycle bicycle) throws Exception {

    }
}
