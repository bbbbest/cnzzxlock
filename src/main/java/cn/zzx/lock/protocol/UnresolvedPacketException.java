package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/8
 */
public class UnresolvedPacketException extends Exception {
    private static final long serialVersionUID = -8677798531400807289L;

    public UnresolvedPacketException(byte[] data) {
        super("Received a invalid packet, source: " + new String(data));
    }
}
