package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/6
 */
public enum ActionType {
    LOCK((byte) 'f'), UNLOCK((byte) 'o');
    private byte value;

    ActionType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
