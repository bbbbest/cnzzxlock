package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.UserDao;
import cn.zzx.lock.db.po.User;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/4
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User findByCardNum(int card) throws Exception {
        return null;
    }

    @Override
    public void update(User user) throws Exception {

    }
}
