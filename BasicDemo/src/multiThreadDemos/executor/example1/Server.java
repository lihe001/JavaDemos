package multiThreadDemos.executor.example1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ִ�з�����
 */
public class Server {

    //�̳߳�
    private ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
    }

    //���̳߳��ύ����
    public void submitTask(Task task) {
        System.out.printf("Server: A new task has arrived\n");
        executor.execute(task); //ִ��  �޷���ֵ

        System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
    }

    public void endServer() {
        executor.shutdown();
    }
}
