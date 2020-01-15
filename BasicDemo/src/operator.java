public class operator {
    public static void main(String[] args) {
        int i = 1;
        i += ++i;
        System.out.println(i);

        i = 7;
        int j = 6;

        int k = i < j ? 99 : 88;

        // 相当于
        if (i < j) {
            k = 99;
        } else {
            k = 88;
        }

        System.out.println(k);
    }


}
