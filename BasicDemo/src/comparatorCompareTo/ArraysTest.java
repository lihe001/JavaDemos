package comparatorCompareTo;

import java.util.Arrays;
import java.util.Random;

public class ArraysTest {
    public static void main(String[] args) {
        testSort();
        testSearch();
        testCopy();
        testFill();
        testEquality();
    }

    public static void testSort() {
        Random r = new Random();
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt();
        }
        System.out.println("===============��������================");
        System.out.println("����ǰ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
        System.out.println("�����");
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
    }

    public static void testSearch() {
        Random r = new Random();
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt();
        }
        a[a.length - 1] = 10000;
        System.out.println("===========���Բ���============");
        System.out.println("10000 ��λ����" + Arrays.binarySearch(a, 10000));
    }

    public static void testCopy() {
        Random r = new Random();
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt();
        }
        int[] b = Arrays.copyOf(a, 5);
        System.out.println("===========���Կ���ǰ���Ԫ��============");
        System.out.print("Դ���飺");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
        System.out.print("Ŀ�����飺");
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + ",");
        }
        System.out.println();
    }

    public static void testFill() {
        int[] a = new int[10];
        Arrays.fill(a, 100);
        Arrays.fill(a, 2, 8, 200);
        System.out.println("===========����������ֵ============");
        System.out.print("���鸳ֵ��");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
    }

    public static void testEquality() {
        int[] a = new int[10];
        Arrays.fill(a, 100);
        int[] b = new int[10];
        Arrays.fill(b, 100);
        System.out.println(Arrays.equals(a, b));
        b[9] = 200;
        System.out.println(Arrays.equals(a, b));
    }
}
