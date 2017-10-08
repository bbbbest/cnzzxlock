package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/8
 */
public abstract class Packet {
    protected byte[] source;
    protected int cardNum;
    protected int lockId;
    protected OperateType operationType;


    public Packet(byte[] source) {
        this.source = source;
    }

    public byte[] getSource() {
        return source;
    }

    public int getCardNum() {
        return cardNum;
    }

    public int getLockId() {
        return lockId;
    }

    public OperateType getOperationType() {
        return operationType;
    }

    public abstract boolean isValid();

    public static Packet of(byte[] source) throws UnresolvedPacketException {
        if (source.length == UnlockPacket.REQUEST_VALID_SIZE) {
            return new UnlockPacket(source);
        }
        if (source.length == LockPacket.RESPONSE_VALID_SIZE) {
            return new LockPacket(source);
        }
        throw new UnresolvedPacketException(source);
    }
}
