package cn.zzx.lock.service.impl;

import cn.zzx.lock.db.dao.BicycleDao;
import cn.zzx.lock.db.dao.CyclingRecordDao;
import cn.zzx.lock.db.dao.DealRecordDao;
import cn.zzx.lock.db.dao.UserDao;
import cn.zzx.lock.db.po.Bicycle;
import cn.zzx.lock.db.po.CyclingRecord;
import cn.zzx.lock.db.po.DealRecord;
import cn.zzx.lock.db.po.User;
import cn.zzx.lock.service.CycleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fzh
 * @since 2017/10/6
 */
@Service("cycleService")
public class CycleServiceImpl implements CycleService {

    @Resource(name = "bicycleDao")
    private BicycleDao bicycleDao;

    @Resource(name = "cyclingRecordDao")
    private CyclingRecordDao cyclingRecordDao;

    @Resource(name = "dealRecordDao")
    private DealRecordDao dealRecordDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public User findByCardNum(int card) throws Exception {
        return null;
    }

    @Override
    public Bicycle findByLockId(int lockId) throws Exception {
        return null;
    }

    @Override
    public boolean saveCyclingRecord(CyclingRecord record) throws Exception {
        return false;
    }

    @Override
    public boolean saveDealRecord(DealRecord record) throws Exception {
        return false;
    }
}
