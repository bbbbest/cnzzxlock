package sta;

import cn.zzx.lock.protocol.OperateType;
import org.junit.Test;

import java.util.Date;

/**
 * @author fzh
 * @since 2017/10/3
 */
public class StaticTest {
    static {
        System.out.println("static clock");
    }
    public static void main(String[] args) {
        StaticTest test = new StaticTest();
    }

    @Test
    public void thread() throws Exception {
        Thread thread = new Thread(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) break;
                System.out.println(" in isAlive:" + Thread.currentThread().isAlive());
                System.out.println(" in isInterrupted:" + Thread.currentThread().isInterrupted());
            }
            System.out.println(" in interrupted!");
        });
        System.out.println(" pre start isAlive:" + thread.isAlive());
        System.out.println(" pre start isInterrupted:" + thread.isInterrupted());
        thread.start();
        System.out.println(" after start isAlive:" + thread.isAlive());
        System.out.println(" after start isInterrupted:" + thread.isInterrupted());
        thread.interrupt();
        System.out.println(" after interrupt isAlive:" + thread.isAlive());
        System.out.println(" after interrupt isInterrupted:" + thread.isInterrupted());

    }

    @Test
    public void byteTest() throws Exception {
        Byte status = 1;
        System.out.println(status);
        System.out.println(status << 1);
        System.out.println(status << 2);
        System.out.println(status << 3);
    }

    @Test
    public void nestedClass() throws Exception {
        String msg = "qwerqwfrqwgrqwhr";
        int end = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println(msg.substring(end, (end += 4)));
        }
    }

    @Test
    public void testNumber() throws Exception {
        double x = 123.666666;
        System.out.println(x);
    }

    @Test
    public void actionType() throws Exception {
        OperateType operateType = OperateType.LOCK;
        System.out.println(operateType);
        System.out.println(operateType.ordinal());
        System.out.println(operateType.getValue());
        Date now = new Date();
        Thread.sleep(1000);
        Date after = new Date();
        System.out.println(after.compareTo(now));
    }

    @Test
    public void number() throws Exception {
        String cardNum = "0000001212345678";
        Integer cn = Integer.valueOf(cardNum);
        System.out.println(cn);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

    @Test
    public void testOffsetString() throws Exception {
        String str = "123456";
        byte[] bytes = str.getBytes("UTF-8");
        System.out.println(new String(bytes, 0,2));
        System.out.println(new String(bytes, 2,2));
        System.out.println(new String(bytes, 4,2));
    }

    @Test
    public void testRound() throws Exception {
        float cost = 12654643.5031f;
        System.out.println(Math.round(cost));
    }

    @Test
    public void dateTest() throws Exception {
        long x = 1080000 / 3600;
        System.out.println(x);
        float cost = (float) (x * 0.5);
        System.out.println(cost);
        System.out.println((float) 1/2);
    }
}
