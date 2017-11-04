package cn.zzx.lock.db.po;

import java.math.BigDecimal;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class User {
  public static final Byte NORMAL_STATUS = 1;
  private Integer userId;
  private String username;
  private String password;
  private String name;
  private Integer cardNumber;
  private Integer score;
  private String phone;
  private Byte status;
  private BigDecimal balance;
  private Integer userInfoId;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Integer getUserInfoId() {
    return userInfoId;
  }

  public void setUserInfoId(Integer userInfoId) {
    this.userInfoId = userInfoId;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        ", cardNumber=" + cardNumber +
        ", score=" + score +
        ", phone='" + phone + '\'' +
        ", status=" + status +
        ", balance=" + balance +
        ", userInfoId=" + userInfoId +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof User && this.userId != null && this.userId.equals(((User) obj).userId);
  }

  /**
   * 判断该用户是否可以骑行，判断标准为：该用户为正常状态且余额大于0
   *
   * @return 该用户是否可骑行
   */
  public boolean available() {
    return NORMAL_STATUS.equals(status) && balance.intValue() > 0;
  }
}
