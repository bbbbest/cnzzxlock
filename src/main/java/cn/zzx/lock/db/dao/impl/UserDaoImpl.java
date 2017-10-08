package cn.zzx.lock.db.dao.impl;

import cn.zzx.lock.db.dao.BaseDao;
import cn.zzx.lock.db.dao.UserDao;
import cn.zzx.lock.db.po.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author fzh
 * @since 2017/10/4
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User findByCardNum(long card) throws Exception {
        String sql = "select * from user where cardNumber=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return getJdbcTemplate().queryForObject(sql, rowMapper, card);
    }

    @Override
    public void update(User user) throws Exception {
        String sql = "update user set userName=?, password=?, name=?, cardNumber=?, score=?, phone=?, stauts=?, " +
                "balance=? where userId=?";
        getJdbcTemplate().update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getCardNumber(),
                user.getScore(), user.getPhone(), user.getStatus(), user.getBalance(), user.getUserId());
    }
}
