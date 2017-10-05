package cn.zzx.lock.db.po;

import java.util.Date;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class CyclingRecord {
    private int cyclingRecordId;
    private int bicycleId;
    private int userId;
    private Date startTime;
    private Date endTime;
    private double startLocX;
    private double startLocY;
    private double endLocX;
    private double endLocY;

    public int getCyclingRecordId() {
        return cyclingRecordId;
    }

    public void setCyclingRecordId(int cyclingRecordId) {
        this.cyclingRecordId = cyclingRecordId;
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getStartLocX() {
        return startLocX;
    }

    public void setStartLocX(double startLocX) {
        this.startLocX = startLocX;
    }

    public double getStartLocY() {
        return startLocY;
    }

    public void setStartLocY(double startLocY) {
        this.startLocY = startLocY;
    }

    public double getEndLocX() {
        return endLocX;
    }

    public void setEndLocX(double endLocX) {
        this.endLocX = endLocX;
    }

    public double getEndLocY() {
        return endLocY;
    }

    public void setEndLocY(double endLocY) {
        this.endLocY = endLocY;
    }

    @Override
    public String toString() {
        return "CyclingRecord{" +
                "cyclingRecordId=" + cyclingRecordId +
                ", bicycleId=" + bicycleId +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startLocX=" + startLocX +
                ", startLocY=" + startLocY +
                ", endLocX=" + endLocX +
                ", endLocY=" + endLocY +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CyclingRecord && this.cyclingRecordId == ((CyclingRecord) obj).cyclingRecordId;
    }
}
