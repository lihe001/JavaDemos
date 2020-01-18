package multiThreadDemos.product;

/**
 * �ֿ�
 */
class Storage {
    // �ֿ�����Ϊ10
    private Product[] products = new Product[10];
    private int top = 0;

    // ���������ֿ��з����Ʒ
    public synchronized void push(Product product) {
        while (top == products.length) {
            try {
                System.out.println("producer wait");
                wait();//�ֿ��������ȴ�
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //�Ѳ�Ʒ����ֿ�
        products[top++] = product;
        System.out.println(Thread.currentThread().getName() + " 生产一个产品 "
                + product);
        System.out.println("producer notifyAll");
        notifyAll();//���ѵȴ��߳�


    }

    // �����ߴӲֿ���ȡ����Ʒ
    public synchronized Product pop() {
        while (top == 0) {
            try {
                System.out.println("consumer wait");
                wait();//�ֿ�գ��ȴ�
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        //�Ӳֿ���ȡ��Ʒ
        --top;
        Product p = new Product(products[top].getId(), products[top].getName());
        products[top] = null;
        System.out.println(Thread.currentThread().getName() + " 消费一个产品 " + p);
        System.out.println("comsumer notifyAll");
        notifyAll();//���ѵȴ��߳�
        return p;
    }
}
