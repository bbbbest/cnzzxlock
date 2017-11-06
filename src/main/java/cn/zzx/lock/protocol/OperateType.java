package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/6
 */
public enum OperateType {
  /**
   * LOCK     用符号 ']'代表关锁
   * UNLOCK   用符号 '['代表开锁
   */
  LOCK((byte) 0x5D), UNLOCK((byte) 0x5B), CONFIG((byte) 0xFF);
  private byte value;

  OperateType(byte value) {
    this.value = value;
  }

  public byte getValue() {
    return value;
  }

  static OperateType of(byte val) {
    if (val != LOCK.value && val != UNLOCK.value) {
      throw new RuntimeException("OperationType should valid.");
    }
    return val == LOCK.value ? LOCK : UNLOCK;
  }
}
