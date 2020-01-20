package multiThreadDemos.executor.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class SumTest {

    public static void main(String[] args) {

        // ִ创建executor, 线程数量上限为4
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

        List<Future<Integer>> resultList = new ArrayList<>();

        //把从1加到1000的大任务，每100个数分为一组
        for (int i = 0; i < 10; i++) {
            SumTask calculator = new SumTask(i * 100 + 1, (i + 1) * 100);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        // 每50毫秒查询所有分任务是否都完成
        do {
            System.out.printf("Main: 完成任务数量: %d\n", executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d: %s\n", i, result.isDone());
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getCompletedTaskCount() < resultList.size());

        // 所有分任务结束后，汇总结果
        int total = 0;
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer sum = null;
            try {
                sum = result.get();
                total = total + sum;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("1-1000总和:" + total);

        // 结束excecutor
        executor.shutdown();
    }
}
