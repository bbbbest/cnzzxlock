package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/11/6
 */
public class ConfigPacket extends AbstractPacket {
  public static final int CONFIG_VALID_SIZE = 9;

  private Float tariff;

  public ConfigPacket(byte[] source) {
    super(source);
    if (source.length == CONFIG_VALID_SIZE) {
      this.operationType = OperateType.of(source[0]);
      this.tariff = Float.parseFloat(new String(source, 1, 8));
    }
  }

  @Override
  public int getCardNum() {
    throw new NoSuchFieldError("配置数据包没有 卡号[CardNum] 属性");
  }

  @Override
  public int getLockId() {
    throw new NoSuchFieldError("配置数据包没有 锁编号[LockId] 属性");
  }

  public Float getTariff() {
    return tariff;
  }

  @Override
  public boolean isValid() {
    return source.length == CONFIG_VALID_SIZE;
  }
}
