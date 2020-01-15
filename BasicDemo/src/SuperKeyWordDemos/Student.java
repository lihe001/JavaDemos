package SuperKeyWordDemos;


public class Student extends Person {
    private String schoolName;
    int id = 1002;

    public Student() {
        super();
    }

    public Student(String schoolName) {
        super("liLei", 23);
        this.schoolName = schoolName;
    }

    public void show() {
        System.out.println(id);
        System.out.println(super.getName());
        System.out.println(super.id);
        System.out.println(schoolName);
    }
}
