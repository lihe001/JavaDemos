package spring.mySpring;


import spring.core.CommandLineRunner;
import spring.core.MySpringApplication;
import spring.core.annotations.Autowired;
import spring.core.annotations.Component;
import spring.mySpring.di.GreetingService;

//��ʶ��ǰ��������������������������
@Component
public class MySpring implements CommandLineRunner {
    //��ʶ��ǰ��Ա�����������Զ�װ����ʶ���
    @Autowired
    private GreetingService greetingService;


    public static void main(String[] args) {
        MySpringApplication.ENABLE_LOG = false;
        MySpringApplication.run(MySpring.class);
    }


    @Override
    public void run() {
        System.out.println("Now the application is running");
        System.out.println("This is my spring");
        greetingService.greet();
    }

}
