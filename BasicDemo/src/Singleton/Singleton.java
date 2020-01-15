package Singleton;

public class Singleton {
    private static Singleton obj = new Singleton(); //����ͬһ������
    private String content;

    private Singleton()  //ȷ��ֻ�������ڲ����ù��캯��
    {
        this.content = "abc";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Singleton getInstance() {
        //��̬����ʹ�þ�̬����
        //�������ʹ�÷����ڵ���ʱ���������ǲ������÷Ǿ�̬�ĳ�Ա����
        return obj;
    }


    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        System.out.println(obj1.getContent());  //abc

        Singleton obj2 = Singleton.getInstance();
        System.out.println(obj2.getContent());  //abc

        obj2.setContent("def");
        System.out.println(obj1.getContent());
        System.out.println(obj2.getContent());

        System.out.println(obj1 == obj2); //true, obj1��obj2ָ��ͬһ������
    }

}
