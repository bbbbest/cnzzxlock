package cn.zzx.lock.protocol;

import java.io.Serializable;

/**
 * @author fzh
 * @since 2017/10/4
 */
public final class UnlockPacket extends AbstractPacket implements Serializable {
    private static final long serialVersionUID = 1315779758371011560L;
    public static final int REQUEST_VALID_SIZE = 33;

    public UnlockPacket(byte[] source) {
        super(source);
        if (source.length == REQUEST_VALID_SIZE) {
            cardNum = Integer.parseInt(new String(source, 0, 16));
            lockId = Integer.parseInt(new String(source, 16, 16));
            operationType = OperateType.of(source[32]);
        }
    }

    @Override
    public boolean isValid() {
        return source.length == REQUEST_VALID_SIZE;
    }
}