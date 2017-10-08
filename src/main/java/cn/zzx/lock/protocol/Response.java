package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/8
 */
public enum Response {
    OPEN("open"), CLOSE("close");

    private String val;

    Response(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }

}
