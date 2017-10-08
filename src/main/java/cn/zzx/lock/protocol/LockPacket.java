package cn.zzx.lock.protocol;

import java.io.Serializable;

/**
 * @author fzh
 * @since 2017/10/8
 */
public final class LockPacket extends Packet implements Serializable {
    private static final long serialVersionUID = 1407083814451324081L;
    public static final int RESPONSE_VALID_SIZE = 75;
    private double longitude;
    private double latitude;
    private float energy;

    public LockPacket(byte[] source) {
        super(source);
        if (source.length == RESPONSE_VALID_SIZE) {
            cardNum = Integer.parseInt(new String(source, 0, 16));
            longitude = Double.parseDouble(new String(source, 24, 12));
            latitude = Double.parseDouble(new String(source, 44, 12));
            energy = Float.parseFloat(new String(source, 56,2));
            operationType = OperateType.of(source[58]);
            lockId = Integer.parseInt(new String(source, 59, 16));
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public float getEnergy() {
        return energy;
    }

    @Override
    public boolean isValid() {
        return source.length == RESPONSE_VALID_SIZE;
    }
}
