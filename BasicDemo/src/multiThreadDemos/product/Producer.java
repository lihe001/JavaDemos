package multiThreadDemos.product;

import java.util.Random;

/**
 * ������
 */
class Producer implements Runnable {
    private Storage storage;
    private volatile static Integer id = 0;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int i = 0;
        Random r = new Random();
        while (i < 10) {
            i++;
            //在producer层面管理id似乎并不是一个好方法
            synchronized (id) {
                Product product = new Product(id, "产品" + r.nextInt(100));
                id++;
                storage.push(product);
            }

        }
    }
}
