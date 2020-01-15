package SuperKeyWordDemos;

/**
 * super可以调用父类的属性，方法，构造器，尤其是在重名的情况下（比如方法重写）
 * super声明父类构造器，要在首行（在构造器中 super 与 this 不能共存）
 * 构造器默认调用父类空参构造器, 最终会调用object构造器
 * 建议：设计类时尽量留一个空参构造器
 */
public class Test {
    public static void main(String[] args) {
        Student s = new Student();
        Person p = new Person();
        s.show();

        Student s2 = new Student("testSchool");
        s2.show();
    }
}
