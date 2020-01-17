package international.main.java;

import java.util.Locale;

public class LocaleTest {

    public static void main(String[] args) {
        // ����Java��֧�ֵ�ȫ�����Һ����Ե�����

        Locale[] localeList = Locale.getAvailableLocales();

        for (Locale locale : localeList) {
            System.out.println(locale.getLanguage() + "_" + locale.getCountry());
            System.out.println(locale.getDisplayLanguage() + "_" + locale.getDisplayCountry());
        }

        System.out.println("=========================");
        Locale myLocale = Locale.getDefault();
        System.out.println(myLocale); //zh_CN
        System.out.println(Locale.CHINA); //zh_CN

        myLocale = new Locale("en", "US"); //���� ����, ǿ�ƻ���en_US
        System.out.println(myLocale); //en_US

    }
}
