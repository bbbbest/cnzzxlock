package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/9
 */
public class ConstValue {
    /**
     * 扣费费率
     */
    public static float tariff = 1.0f;
    public static int calcUnit = 60;    // 分钟
    public static float getTariff() {
        return tariff;
    }

    public static int getCalcUnit() {
        return calcUnit;
    }
}
