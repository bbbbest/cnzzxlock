package cn.zzx.lock.db.po;

import java.util.Date;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class Bicycle {
  public static final Integer DANGER_ENERGY = 5;
  public static final Byte UNAVAILABLE = 0;
  private Integer bicycleId;
  private Integer from;
  private Date time;
  private String photoUrl;
  private Integer lockId;
  private Double locationX;
  private Double locationY;
  private Byte status;
  private Float energy;

  public Integer getBicycleId() {
    return bicycleId;
  }

  public void setBicycleId(Integer bicycleId) {
    this.bicycleId = bicycleId;
  }

  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public Integer getLockId() {
    return lockId;
  }

  public void setLockId(Integer lockId) {
    this.lockId = lockId;
  }

  public Double getLocationX() {
    return locationX;
  }

  public void setLocationX(Double locationX) {
    this.locationX = locationX;
  }

  public Double getLocationY() {
    return locationY;
  }

  public void setLocationY(Double locationY) {
    this.locationY = locationY;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Float getEnergy() {
    return energy;
  }

  public void setEnergy(Float energy) {
    this.energy = energy;
  }

  @Override
  public String toString() {
    return "Bicycle{" +
        "bicycleId=" + bicycleId +
        ", from=" + from +
        ", time=" + time +
        ", photoUrl='" + photoUrl + '\'' +
        ", lockId=" + lockId +
        ", locationX=" + locationX +
        ", locationY=" + locationY +
        ", status=" + status +
        ", energy=" + energy +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Bicycle && this.bicycleId != null && this.bicycleId.equals(((Bicycle) obj).bicycleId);
  }

  /**
   * 判断该自行车是否可用，判断标准为：该自行车为未损坏状态并且电量大于 5%
   *
   * @return 可用状态
   */
  public boolean available() {
    return !UNAVAILABLE.equals(status) && energy > DANGER_ENERGY;
  }
}
