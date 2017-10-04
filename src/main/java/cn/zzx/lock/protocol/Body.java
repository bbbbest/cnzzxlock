package cn.zzx.lock.protocol;

import java.io.Serializable;

/**
 * @author fzh
 * @since 2017/10/4
 */
public final class Body implements Serializable{
    private static final long serialVersionUID = -6468506213118215376L;
    private String cardNum;
    private String locX;
    private String locY;
    private String energy;
    private String operationType;
    private String lockId;

    public Body() {
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getLocX() {
        return locX;
    }

    public void setLocX(String locX) {
        this.locX = locX;
    }

    public String getLocY() {
        return locY;
    }

    public void setLocY(String locY) {
        this.locY = locY;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    @Override
    public String toString() {
        return "Body{" +
                "cardNum='" + cardNum + '\'' +
                ", locX='" + locX + '\'' +
                ", locY='" + locY + '\'' +
                ", energy='" + energy + '\'' +
                ", operationType='" + operationType + '\'' +
                ", lockId='" + lockId + '\'' +
                '}';
    }
}
