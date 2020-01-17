package international.main.java;

import java.util.Locale;
import java.util.ResourceBundle;

public class NewHelloWorld {

    public static void main(String[] args) {
        // ȡ��ϵͳĬ�ϵĹ���/���Ի���
        Locale myLocale = Locale.getDefault();

        System.out.println(myLocale); //zh_CN

        // ����ָ������_���һ���������Դ�ļ�
        ResourceBundle bundle = ResourceBundle.getBundle("message", myLocale);

        // ����Դ�ļ���ȡ�õ���Ϣ
        System.out.println(bundle.getString("hello"));  //���, ����

        myLocale = new Locale("en", "US"); //���� ����, ǿ�ƻ���en_US
        bundle = ResourceBundle.getBundle("message", myLocale);
        System.out.println(bundle.getString("hello"));  //Hello World

    }
}
