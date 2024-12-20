public class Lucky {
    static int x = 0;
    static int count = 0;
    static final Object lock = new Object();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int currentX;

                synchronized (lock) {
                    currentX = x++;
                    if (x >= 999999) break;
                }

                if ((currentX % 10) + (currentX / 10) % 10 + (currentX / 100) % 10 ==
                        (currentX / 1000) % 10 + (currentX / 10000) % 10 + (currentX / 100000) % 10) {
                    System.out.println(currentX);

                    synchronized (lock) {
                        count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Total: " + count);
    }
}
