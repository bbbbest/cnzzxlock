package cn.zzx.lock.protocol;

/**
 * @author fzh
 * @since 2017/10/8
 */
public abstract class AbstractPacket {
  protected byte[] source;
  protected int cardNum;
  protected int lockId;
  protected OperateType operationType;

  public AbstractPacket(byte[] source) {
    this.source = source;
  }

  public byte[] getSource() {
    return source;
  }

  public int getCardNum() {
    return cardNum;
  }

  public int getLockId() {
    return lockId;
  }

  public OperateType getOperationType() {
    return operationType;
  }

  /**
   * 判断数据包是否有效
   *
   * @return boolean
   */
  public abstract boolean isValid();

  public static AbstractPacket of(byte[] source) throws UnresolvedPacketException {
    if (source.length == UnlockPacket.REQUEST_VALID_SIZE) {
      return new UnlockPacket(source);
    }
    if (source.length == LockPacket.RESPONSE_VALID_SIZE) {
      return new LockPacket(source);
    }
    if (source.length == ConfigPacket.CONFIG_VALID_SIZE) {
      return new ConfigPacket(source);
    }
    throw new UnresolvedPacketException(source);
  }
}
