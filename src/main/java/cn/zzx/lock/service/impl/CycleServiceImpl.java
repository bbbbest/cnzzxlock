package cn.zzx.lock.service.impl;

import cn.zzx.lock.db.dao.BicycleDao;
import cn.zzx.lock.db.dao.CyclingRecordDao;
import cn.zzx.lock.db.dao.DealRecordDao;
import cn.zzx.lock.db.dao.UserDao;
import cn.zzx.lock.db.po.Bicycle;
import cn.zzx.lock.db.po.CyclingRecord;
import cn.zzx.lock.db.po.DealRecord;
import cn.zzx.lock.db.po.User;
import cn.zzx.lock.protocol.ConstValue;
import cn.zzx.lock.service.CycleService;
import org.junit.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

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
    public Optional<Object[]> findUserAndBicycleByCNumAndLId(int cardNum, int lockId) {
        Object[] objects = new Object[2];
        try {
            objects[0] = userDao.findByCardNum(cardNum);
            objects[1] = bicycleDao.findByLockId(lockId);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(objects);
    }

    @Override
    public Optional<CyclingRecord> findCyclingRecord(User user, Bicycle bicycle) {
        CyclingRecord cyclingRecord;
        try {
            // 当前用户正在骑行
            cyclingRecord = cyclingRecordDao.findUnFinishedByUserIdOrBicycleId(user.getUserId(), bicycle.getBicycleId());
        } catch (Exception ignored) {
            return Optional.empty();
        }
        return Optional.of(cyclingRecord);
    }

    @Override
    public boolean lock(User user, Bicycle bicycle, CyclingRecord cyclingRecord, double locX, double locY, float energy) throws Exception {
        Date now = new Date(System.currentTimeMillis());
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
            float cost = PriceCalculator.calc(cyclingRecord.getStartTime(), now);
            DealRecord dealRecord = new DealRecord();
            dealRecord.setDealRecordId(String.valueOf(user.getUserId()) + 0 + now.getTime());
            dealRecord.setUserId(user.getUserId());
            dealRecord.setActionTime(now);
            dealRecord.setActionType((byte) 0 /*消费*/);
            dealRecord.setMoney(cost);
            dealRecordDao.save(dealRecord);
            user.setBalance(user.getBalance() - cost);
            user.setScore(user.getScore() + Math.round(cost));
            userDao.update(user);
        }
        return true;
    }

    @Override
    public boolean unlock(User user, Bicycle bicycle) throws Exception {
        if (!user.available()) return false;
        if (!bicycle.available()) return false;
        {
            CyclingRecord cyclingRecord = new CyclingRecord();
            Date now = new Date();
            cyclingRecord.setCyclingRecordId(String.valueOf(user.getUserId()) + now.getTime());
            cyclingRecord.setUserId(user.getUserId());
            cyclingRecord.setBicycleId(bicycle.getBicycleId());
            cyclingRecord.setStartTime(now);
            cyclingRecord.setStartLocX(bicycle.getLocationX());
            cyclingRecord.setStartLocY(bicycle.getLocationY());
            cyclingRecordDao.save(cyclingRecord);
        }
        return true;
    }

    private static class PriceCalculator {

        static float calc(Date last, Date now) {
            Assert.assertTrue(now.after(last));
            float tariff = ConstValue.getTariff();
            long start = last.getTime();
            long end = now.getTime();
            return tariff * unit((int) ((end - start) / 1000));
        }

        private static int unit(int mills) {
            // 计费单元，向上取整
            int calcUnit = ConstValue.getCalcUnit();
            int s = mills / (calcUnit * 60/*one minute*/);
            int y = mills % calcUnit;
            return y > 0 ? s + 1 : s;
        }
    }
}
