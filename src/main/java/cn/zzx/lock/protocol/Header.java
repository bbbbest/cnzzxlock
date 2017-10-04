package cn.zzx.lock.protocol;

import java.io.Serializable;

/**
 * @author fzh
 * @since 2017/10/4
 */
public final class Header implements Serializable {
    private static final long serialVersionUID = -1126503974408055614L;
    private byte card;
    private byte locX;
    private byte locY;
    private byte energy;
    private byte operationType;
    private byte lockId;

    public Header(byte card, byte locX, byte locY, byte energy, byte operationType, byte lockId) {
        this.card =             card;
        this.locX =             locX;
        this.locY =             locY;
        this.energy =           energy;
        this.operationType =    operationType;
        this.lockId =           lockId;
    }

    public Header(byte[] info) {
        if (info.length <6) throw new IllegalArgumentException("info's length must greater than or equal to 6.");
        this.card =             info[0];
        this.locX =             info[1];
        this.locY =             info[2];
        this.energy =           info[3];
        this.operationType =    info[4];
        this.lockId =           info[5];
    }

    public Header() {
    }

    public byte getCard() {
        return card;
    }

    public void setCard(byte card) {
        this.card = card;
    }

    public byte getLocX() {
        return locX;
    }

    public void setLocX(byte locX) {
        this.locX = locX;
    }

    public byte getLocY() {
        return locY;
    }

    public void setLocY(byte locY) {
        this.locY = locY;
    }

    public byte getEnergy() {
        return energy;
    }

    public void setEnergy(byte energy) {
        this.energy = energy;
    }

    public byte getOperationType() {
        return operationType;
    }

    public void setOperationType(byte operationType) {
        this.operationType = operationType;
    }

    public byte getLockId() {
        return lockId;
    }

    public void setLockId(byte lockId) {
        this.lockId = lockId;
    }

    @Override
    public String toString() {
        return "Header{" +
                "card length=" + card +
                ", locX length=" + locX +
                ", locY length=" + locY +
                ", energy length=" + energy +
                ", operationType length=" + operationType +
                ", lockId length=" + lockId +
                '}';
    }
}
