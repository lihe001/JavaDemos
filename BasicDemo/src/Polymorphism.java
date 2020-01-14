public class Polymorphism {

    public static void main(String [] args) {
        Deer d = new Deer();
        Animal a = d;
        Vegetarian v = d;
        Object o = d;
    }
}


interface Vegetarian{}
class Animal{}
class Deer extends Animal implements Vegetarian{}

