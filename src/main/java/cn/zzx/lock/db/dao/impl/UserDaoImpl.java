package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/4
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public void print() {
        System.out.println(getJdbcTemplate());
    }
}
