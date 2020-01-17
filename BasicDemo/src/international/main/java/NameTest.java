package international.main.java;

import java.util.Locale;
import java.util.ResourceBundle;

public class NameTest {

    public static void main(String[] args) {
        Locale myLocale = Locale.getDefault();

        System.out.println(myLocale); //zh_CN

        // ����ָ������_���һ���������Դ�ļ�
        ResourceBundle bundle = ResourceBundle.getBundle("msg", myLocale);

        // ����Դ�ļ���ȡ�õ���Ϣ
        System.out.println(bundle.getString("name"));  //������

    }

}
