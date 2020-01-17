package comparatorCompareTo;

import java.util.Arrays;
import java.util.Comparator;

public class Person2Comparator implements Comparator<Person2> {
    public int compare(Person2 one, Person2 another) {
        int i = 0;
        i = one.getName().compareTo(another.getName());
        if (i == 0) {
            // �������һ��,�Ƚ�����,���رȽ�������
            return one.getAge() - another.getAge();
        } else {
            return i; // ���ֲ�һ��, ���رȽ����ֵĽ��.
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Person2[] ps = new Person2[3];
        ps[0] = new Person2("Tom", 20);
        ps[1] = new Person2("Mike", 18);
        ps[2] = new Person2("Mike", 20);

        Arrays.sort(ps, new Person2Comparator());
        for (Person2 p : ps) {
            System.out.println(p.getName() + "," + p.getAge());
        }
    }
}
