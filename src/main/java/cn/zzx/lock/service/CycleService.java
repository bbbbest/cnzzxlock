package cn.zzx.lock.service;

/**
 * 整个骑车过程的服务层
 *
 * @author fzh
 * @version 1.0
 * @since 2017 /10/6
 */
public interface CycleService {

    boolean lock(int cardNum, int lockId, double locX, double locY, float energy) throws Exception;

    boolean unlock(int cardNum, int lockId) throws Exception;
}
