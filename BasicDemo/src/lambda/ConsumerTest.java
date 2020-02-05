package lambda;

import java.util.function.Consumer;

public class ConsumerTest {

    public static void main(String[] args) {
        String[] planets = new String[]{
                "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};


        //Consumer 有一个accept的抽象方法
        //接收一个参数，没有返回
        Consumer<String> printer = s ->
                System.out.println("Planet :" + s);

        for (String p : planets) {
            printer.accept(p);
        }

    }
}
