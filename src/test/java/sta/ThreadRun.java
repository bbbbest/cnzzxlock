package sta;

/**
 * @author fzh
 * @since 2017/10/4
 */
public class ThreadRun {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupted!");
                        break;
                    }
                    System.out.println("run " + Thread.currentThread().getName() + " " +Thread.currentThread().isInterrupted() +" " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("ctrl+c");
            thread.interrupt();
        }));
        thread.join();
    }
}
