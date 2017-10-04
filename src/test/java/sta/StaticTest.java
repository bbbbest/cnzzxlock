package sta;

import org.junit.Test;

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
}
