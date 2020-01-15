public class Polymorphism {

    public static void main(String[] args) {
        Deer d = new Deer();
        Animal a = d;
        Vegetarian v = d;
        Object o = d;
        //父类引用可以指向子类对象
    }
}


interface Vegetarian {
    void eat();
}

class Animal {

}

class Deer extends Animal implements Vegetarian {
    public void eat() {
        System.out.println("Animal eat");
    }
}

