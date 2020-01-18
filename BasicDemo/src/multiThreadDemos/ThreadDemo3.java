package multiThreadDemos;

public class ThreadDemo3 {
    public static void main(String[] args) {
        TestThread3 t = new TestThread3();
        new Thread(t, "Thread-0").start();
        new Thread(t, "Thread-1").start();
        new Thread(t, "Thread-2").start();
        new Thread(t, "Thread-3").start();
    }
}

class TestThread3 implements Runnable {
    private volatile int tickets = 100; // 好像这里可以不用volatile?
    String str = new String("");

    public void run() {
        while (true) {
            sale();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (tickets <= 0) {
                break;
            }
        }

    }

    public synchronized void sale() { // ͬ������
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + " is saling ticket " + tickets--);
        }
    }
}
