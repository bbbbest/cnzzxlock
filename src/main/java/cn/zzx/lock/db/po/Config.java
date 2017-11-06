package cn.zzx.lock.db.po;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author fzh
 * @since 2017/10/9
 */
@Component("config")
public class Config {

  private static final Logger logger = LogManager.getLogger(Config.class);
  /**
   * 扣费费率
   */
  private float tariff = 1.0f;
  private int calcUnit = 60;    // 分钟
  private static Config ins;

  @Resource(name = "dataSource")
  private DataSource dataSource;

  @PostConstruct
  private void init() {
    if (logger.isDebugEnabled()) {
      logger.debug("Config has been initialized");
    }
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = dataSource.getConnection();
      ps = connection.prepareStatement("SELECT value FROM config WHERE name = 'price';");
      rs = ps.executeQuery();
      if (rs.next()) {
        String t = rs.getString("value");
        setTariff(Float.parseFloat(t));
      } else {
        throw new RuntimeException("config表没有记录name[price]");
      }
    } catch (SQLException e) {
      logger.trace(e);
    } finally {
      JdbcUtils.closeResultSet(rs);
      JdbcUtils.closeStatement(ps);
      JdbcUtils.closeConnection(connection);
    }
    Config.ins = this;
    if (logger.isDebugEnabled()) {
      logger.debug(Config.ins);
    }
  }

  @PreDestroy
  private void destroy() {
    Config.ins = null;
    if (logger.isDebugEnabled()) {
      logger.debug(Config.ins);
    }
  }

  public synchronized void setTariff(float tariff) {
    this.tariff = tariff;
    logger.warn("Tariff has been modified.");
  }

  public synchronized void setCalcUnit(int calcUnit) {
    this.calcUnit = calcUnit;
    logger.warn("CalcUnit has been modified.");
  }

  public float getTariff() {
    return tariff;
  }

  public int getCalcUnit() {
    return calcUnit;
  }

  public static Config getInstance() {
    return ins;
  }
}
