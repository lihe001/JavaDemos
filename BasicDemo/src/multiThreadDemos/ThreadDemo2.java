package multiThreadDemos;

public class ThreadDemo2 {
    public static void main(String args[]) throws Exception {
        TestThread2 t = new TestThread2();
        t.start();
        Thread.sleep(2000);
        t.flag = false;
        System.out.println("main thread is exiting");
    }
}

class TestThread2 extends Thread {
    //boolean flag = true;   //���̲߳���ֹͣ
    volatile boolean flag = true;  //用volatile修饰，保证不同线程对共享变量操作的可见性

    public void run() {
        int i = 0;
        while (flag) {
            i++;
            //System.out.println(i);
        }
        System.out.println("test thread3 is exiting");
    }
}
