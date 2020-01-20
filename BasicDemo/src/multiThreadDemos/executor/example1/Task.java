package multiThreadDemos.executor.example1;

import java.util.Date;

/**
 * Task ������
 *
 * @author Tom
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void run() {
        try {
            Long duration = (long) (Math.random() * 1000);
            System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), name, duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
    }

}
