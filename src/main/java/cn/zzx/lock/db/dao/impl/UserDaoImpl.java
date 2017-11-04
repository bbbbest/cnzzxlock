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
    String sql = "SELECT\n" +
        "  userId,\n" +
        "  userName AS username,\n" +
        "  `name`,\n" +
        "  cardNumber,\n" +
        "  score,\n" +
        "  phone,\n" +
        "  status,\n" +
        "  balance,\n" +
        "  userInfoId\n" +
        "FROM user WHERE cardNumber=?";
    RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
    return getJdbcTemplate().queryForObject(sql, rowMapper, card);
  }

  @Override
  public void update(User user) throws Exception {
    String sql = "UPDATE user SET score=?, balance=? WHERE userId=?";
    getJdbcTemplate().update(sql, user.getScore(), user.getBalance(), user.getUserId());
  }
}
