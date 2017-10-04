package cn.zzx.lock.protocol;

import java.io.Serializable;

/**
 * @author fzh
 * @since 2017/10/4
 */
public final class Packet implements Serializable{
    private static final long serialVersionUID = 1315779758371011560L;
    private Header header;
    private Body body;
    private byte[] source;
    public Packet(byte[] source) {
        this.source = source;
    }

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }

    public byte[] getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
