package cn.zzx.lock.db.dao;

import cn.zzx.lock.db.po.User;

/**
 * 用户信息的DAO
 *
 * @author fzh
 * @since 2017 /10/3
 */
public interface UserDao {
    /**
     * 根据卡号查用户
     *
     * @param card the card
     * @return the user
     * @throws Exception the exception
     */
    User findByCardNum(long card) throws Exception;

    /**
     * 更新用户余额
     *
     * @param user the user
     * @throws Exception the exception
     */
    void update(User user) throws Exception;
}
