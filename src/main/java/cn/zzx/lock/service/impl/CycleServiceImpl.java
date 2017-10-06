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
import java.util.Date;

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
    public boolean lock(int cardNum, int lockId, double locX, double locY, float energy) throws Exception {
        Date now = new Date(System.currentTimeMillis());
        User user = userDao.findByCardNum(cardNum);
        Bicycle bicycle = bicycleDao.findByLockId(lockId);
        CyclingRecord cyclingRecord = cyclingRecordDao.findUnFinishedByUserIdAndBicycleId(user.getUserId(), bicycle.getBicycleId());
        {
            bicycle.setLocationX(locX);
            bicycle.setLocationY(locY);
            bicycle.setEnergy(energy);
            if (energy < 5) bicycle.setStatus((byte) 0);
            bicycleDao.updateLocationAndEnergyByLockId(bicycle);
        }
        {
            cyclingRecord.setEndLocX(locX);
            cyclingRecord.setEndLocY(locY);
            cyclingRecord.setEndTime(now);
            cyclingRecordDao.update(cyclingRecord);
        }
        {
            float tariff = 1;   // per hour
            float hours = now.compareTo(cyclingRecord.getStartTime()) / 3600;
            double cost = tariff * hours;
            DealRecord dealRecord = new DealRecord();
            dealRecord.setActionTime(now);
            dealRecord.setActionType((byte) 0);
            dealRecord.setMoney(cost);
            dealRecordDao.save(dealRecord);
            user.setBalance(user.getBalance() - cost);
            userDao.update(user);
        }
        return true;
    }

    @Override
    public boolean unlock(int cardNum, int lockId) throws Exception {
        User user = userDao.findByCardNum(cardNum);
        if (!user.available()) return false;
        Bicycle bicycle = bicycleDao.findByLockId(lockId);
        if (!bicycle.available()) return false;
        {
            CyclingRecord cyclingRecord = new CyclingRecord();
            cyclingRecord.setUserId(user.getUserId());
            cyclingRecord.setBicycleId(bicycle.getBicycleId());
            cyclingRecord.setStartTime(new Date());
            cyclingRecord.setStartLocX(bicycle.getLocationX());
            cyclingRecord.setStartLocY(bicycle.getLocationY());
            cyclingRecordDao.save(cyclingRecord);
        }
        return true;
    }
}
