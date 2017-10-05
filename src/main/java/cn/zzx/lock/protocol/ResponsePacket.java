package cn.zzx.lock.protocol;

import java.io.Serializable;

/**
 * @author fzh
 * @since 2017/10/5
 */
public final class ResponsePacket implements Serializable{
    private static final long serialVersionUID = 7586016407558544744L;
    public static final byte ON = (byte) 'o';
    public static final byte OFF = (byte) 'f';
}
