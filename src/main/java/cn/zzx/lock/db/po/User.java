package cn.zzx.lock.db.po;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private int cardNumber;
    private int score;
    private String phone;
    private byte status;
    private double balance;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", cardNumber=" + cardNumber +
                ", score=" + score +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && ((User) obj).userId == this.userId;
    }
}
