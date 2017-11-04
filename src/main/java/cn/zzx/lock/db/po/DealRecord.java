package cn.zzx.lock.db.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class DealRecord {
  /**
   * 消费类型的订单记录
   */
  public static final Byte CONSUMPTION = 0;
  public static final Byte LOCK_RECORD = 0;
  private String dealRecordId;
  private Integer userId;
  private Byte actionType;
  private BigDecimal money;
  private Date actionTime;
  private BigDecimal curBalance;
  private Byte status;

  public Date getActionTime() {
    return actionTime;
  }

  public void setActionTime(Date actionTime) {
    this.actionTime = actionTime;
  }

  public String getDealRecordId() {
    return dealRecordId;
  }

  public void setDealRecordId(String dealRecordId) {
    this.dealRecordId = dealRecordId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Byte getActionType() {
    return actionType;
  }

  public void setActionType(Byte actionType) {
    this.actionType = actionType;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public BigDecimal getCurBalance() {
    return curBalance;
  }

  public void setCurBalance(BigDecimal curBalance) {
    this.curBalance = curBalance;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "DealRecord{" +
        "dealRecordId='" + dealRecordId + '\'' +
        ", userId=" + userId +
        ", actionType=" + actionType +
        ", money=" + money +
        ", actionTime=" + actionTime +
        ", curBalance=" + curBalance +
        ", status=" + status +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof DealRecord && this.dealRecordId != null && this.dealRecordId.equals(((DealRecord) obj).dealRecordId);
  }
}
