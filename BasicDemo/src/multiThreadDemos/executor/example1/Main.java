package multiThreadDemos.executor.example1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // ����һ��ִ�з�����
        Server server = new Server();

        // ����100�����񣬲�����ִ�������ȴ����
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            Thread.sleep(10);
            server.submitTask(task);
        }
        server.endServer();
    }
}
