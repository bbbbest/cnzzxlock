package cn.zzx.lock.protocol;

import java.io.Serializable;

/**
 * @author fzh
 * @since 2017/10/4
 */
public final class RequestPacket implements Serializable {
    private static final long serialVersionUID = 1315779758371011560L;
    public static final int MAXSIZE = 82;
    private Header header;
    private Body body;
    private byte[] source;
    private boolean valid;

    public RequestPacket(byte[] source) {
        this.source = source;
        header = new Header(source);
        body = new Body(header, new String(source, 6, source.length - 6));
        valid = header.getBodyLength() == body.getLength();
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
        return "RequestPacket{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    public boolean isValid() {
        return valid;
    }

    public static final class Header implements Serializable {

        private static final long serialVersionUID = -1126503974408055614L;
        private byte card;
        private byte locX;
        private byte locY;
        private byte energy;
        private byte operationType;
        private byte lockId;
        private int length;

        public Header(byte card, byte locX, byte locY, byte energy, byte operationType, byte lockId) {
            this.card = card;
            this.locX = locX;
            this.locY = locY;
            this.energy = energy;
            this.operationType = operationType;
            this.lockId = lockId;
            this.length = card + locX + locY + energy + operationType + lockId;
        }

        Header(byte[] info) {
            if (info.length < 6) throw new IllegalArgumentException("info's length must greater than or equal to 6.");
            this.card = info[0];
            this.locX = info[1];
            this.locY = info[2];
            this.energy = info[3];
            this.operationType = info[4];
            this.lockId = info[5];
        }

        public byte getCard() {
            return card;
        }

        public void setCard(byte card) {
            this.card = card;
        }

        public byte getLocX() {
            return locX;
        }

        public void setLocX(byte locX) {
            this.locX = locX;
        }

        public byte getLocY() {
            return locY;
        }

        public void setLocY(byte locY) {
            this.locY = locY;
        }

        public byte getEnergy() {
            return energy;
        }

        public void setEnergy(byte energy) {
            this.energy = energy;
        }

        public byte getOperationType() {
            return operationType;
        }

        public void setOperationType(byte operationType) {
            this.operationType = operationType;
        }

        public byte getLockId() {
            return lockId;
        }

        public void setLockId(byte lockId) {
            this.lockId = lockId;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "card length=" + card +
                    ", locX length=" + locX +
                    ", locY length=" + locY +
                    ", energy length=" + energy +
                    ", operationType length=" + operationType +
                    ", lockId length=" + lockId +
                    '}';
        }

        public int getBodyLength() {
            return length;
        }
    }

    public static final class Body implements Serializable {
        private static final long serialVersionUID = -6468506213118215376L;
        private String cardNum;
        private String locX;
        private String locY;
        private String energy;
        private String operationType;
        private String lockId;
        private int length = -1;

        Body(Header header, String src) {
            if (header.getBodyLength() == (length = src.length())) {
                int end = 0;
                cardNum = src.substring(end, (end += header.card));
                locX = src.substring(end, (end += header.locX));
                locY = src.substring(end, (end += header.locY));
                energy = src.substring(end, (end += header.energy));
                operationType = src.substring(end, (end += header.operationType));
                lockId = src.substring(end, end + header.lockId);
            }
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getLocX() {
            return locX;
        }

        public void setLocX(String locX) {
            this.locX = locX;
        }

        public String getLocY() {
            return locY;
        }

        public void setLocY(String locY) {
            this.locY = locY;
        }

        public String getEnergy() {
            return energy;
        }

        public void setEnergy(String energy) {
            this.energy = energy;
        }

        public String getOperationType() {
            return operationType;
        }

        public void setOperationType(String operationType) {
            this.operationType = operationType;
        }

        public String getLockId() {
            return lockId;
        }

        public void setLockId(String lockId) {
            this.lockId = lockId;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "cardNum='" + cardNum + '\'' +
                    ", locX='" + locX + '\'' +
                    ", locY='" + locY + '\'' +
                    ", energy='" + energy + '\'' +
                    ", operationType='" + operationType + '\'' +
                    ", lockId='" + lockId + '\'' +
                    '}';
        }

        public int getLength() {
            return length;
        }
    }
}