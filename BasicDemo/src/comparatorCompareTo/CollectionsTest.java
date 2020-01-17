package comparatorCompareTo;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionsTest {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(12);
        list.add(2);
        list.add(19);

        // ����
        Collections.sort(list);
        // ����
        System.out.println("Ԫ�����ڵ�����ֵ�ǣ�" + Collections.binarySearch(list, 12));
        //�����С
        System.out.println("���ֵ��" + Collections.max(list));
        System.out.println("��Сֵ��" + Collections.min(list));
        Collections.reverse(list); //��ת����Ҫ�õ�����

        Collections.fill(list, 100); //ȫ����ֵΪ100
    }
}
