package cn.zzx.lock.db.po;

import java.util.Date;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class CyclingRecord {
    private String cyclingRecordId;
    private Integer bicycleId;
    private Integer userId;
    private Date startTime;
    private Date endTime;
    private Double startLocX;
    private Double startLocY;
    private Double endLocX;
    private Double endLocY;

    public String getCyclingRecordId() {
        return cyclingRecordId;
    }

    public void setCyclingRecordId(String cyclingRecordId) {
        this.cyclingRecordId = cyclingRecordId;
    }

    public Integer getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(Integer bicycleId) {
        this.bicycleId = bicycleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Double getStartLocX() {
        return startLocX;
    }

    public void setStartLocX(Double startLocX) {
        this.startLocX = startLocX;
    }

    public Double getStartLocY() {
        return startLocY;
    }

    public void setStartLocY(Double startLocY) {
        this.startLocY = startLocY;
    }

    public Double getEndLocX() {
        return endLocX;
    }

    public void setEndLocX(Double endLocX) {
        this.endLocX = endLocX;
    }

    public Double getEndLocY() {
        return endLocY;
    }

    public void setEndLocY(Double endLocY) {
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
        return obj instanceof CyclingRecord && this.cyclingRecordId != null && this.cyclingRecordId.equals(((CyclingRecord) obj).cyclingRecordId);
    }
}
