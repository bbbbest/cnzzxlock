package cn.zzx.lock.db.dao;

import cn.zzx.lock.db.po.User;

/**
 * The interface User dao.
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
    User findByCardNum(int card) throws Exception;

    /**
     * 更新用户余额
     *
     */
    void update(User user) throws Exception;
}
