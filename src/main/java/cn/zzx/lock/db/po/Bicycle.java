package cn.zzx.lock.db.po;

import java.util.Date;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class Bicycle {
    private int bicycleId;
    private int from;
    private Date time;
    private String photoUrl;
    private int lockId;
    private double locationX;
    private double locationY;
    private byte status;
    private float energy;

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
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

    public int getLockId() {
        return lockId;
    }

    public void setLockId(int lockId) {
        this.lockId = lockId;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
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
        return obj instanceof Bicycle && ((Bicycle) obj).bicycleId == this.bicycleId;
    }

    /**
     * 判断该自行车是否可用，判断标准为：该自行车为未损坏状态并且电量大于 5%
     * @return 可用状态
     */
    public boolean available(){return status == 1 && energy > 5;}
}
