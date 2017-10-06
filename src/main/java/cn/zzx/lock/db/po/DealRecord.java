package cn.zzx.lock.db.po;

import java.util.Date;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class DealRecord {
    private String dealRecordId;
    private int userId;
    private byte actionType;
    private double money;
    private Date actionTime;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte getActionType() {
        return actionType;
    }

    public void setActionType(byte actionType) {
        this.actionType = actionType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DealRecord{" +
                "dealRecordId=" + dealRecordId +
                ", userId=" + userId +
                ", actionType=" + actionType +
                ", money=" + money +
                ", actionTime=" + actionTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DealRecord && ((DealRecord) obj).dealRecordId == this.dealRecordId;
    }
}
