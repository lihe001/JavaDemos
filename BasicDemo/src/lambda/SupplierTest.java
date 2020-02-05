package lambda;

import java.util.function.Supplier;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class SupplierTest {

    public static void main(String[] args) {
        String[] planets = new String[]{
                "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};

        // Supplier 有一个get的抽象方法
        // 无输入参数，返回一个数据
        Supplier<String> planetFactory = () ->
                planets[(int) floor(random() * 8)];

        for (int i = 0; i < 5; i++) {
            System.out.println(planetFactory.get());
        }
    }
}
