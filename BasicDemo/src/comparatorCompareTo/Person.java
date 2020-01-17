package comparatorCompareTo;

import java.util.Arrays;

public class Person implements Comparable<Person> {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int compareTo(Person another) {
        int i = 0;
        i = name.compareTo(another.name); // ʹ���ַ����ıȽ�
        if (i == 0) {
            // �������һ��,�Ƚ�����, ���رȽ�������
            return age - another.age;
        } else {
            return i; // ���ֲ�һ��, ���رȽ����ֵĽ��.
        }
    }

    public static void main(String... a) {
        Person[] ps = new Person[3];
        ps[0] = new Person("Tom", 20);
        ps[1] = new Person("Mike", 18);
        ps[2] = new Person("Mike", 20);

        Arrays.sort(ps);
        for (Person p : ps) {
            System.out.println(p.getName() + "," + p.getAge());
        }
    }
}
