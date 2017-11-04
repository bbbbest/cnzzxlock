package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/8
 */
public enum Response {
    /**
     * 给锁的回复结果 open代表可以代开，close代表可以关闭，error代表数据包或操作不合法或者操作失败。
     */
    OPEN("open"), CLOSE("close"), ERROR("error");

    private String val;

    Response(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }

}
